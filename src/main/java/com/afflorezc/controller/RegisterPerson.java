package com.afflorezc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.afflorezc.model.Person;
import com.afflorezc.utils.ValidateInputs;
import com.afflorezc.dao.PersonDAO;

@WebServlet("/register_person")
public class RegisterPerson extends HttpServlet {

    private PersonDAO personDAO;

    public RegisterPerson(){
        this.personDAO = new PersonDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

        if(ValidateInputs.validateNoVoidFields(request)){

            Person newPerson = (Person) request.getSession().getAttribute("registering");
            int personID = newPerson.getPersonID();
            
            newPerson.setDocument(request.getParameter("document"));
            newPerson.setFirstName(request.getParameter("name"));
            newPerson.setSecondName(request.getParameter("second-name"));
            newPerson.setLastName1(request.getParameter("lastname1"));
            newPerson.setLastName2(request.getParameter("lastname2"));
            newPerson.setBirthPlace(request.getParameter("birthplace"));
            newPerson.setHometown(request.getParameter("hometown"));
            newPerson.setAddress(request.getParameter("address"));
            newPerson.setCellPhoneNumber(request.getParameter("cell-phone"));
            newPerson.setEmail(request.getParameter("email"));

            personDAO.updatePerson(personID, newPerson);
            
            // Redireccionamos a la página de registro exitoso
            response.sendRedirect("user_login.jsp");
    
        } else {
            // No se completaron los campos
            request.setAttribute("message", "You must fill all the fields");
            request.getRequestDispatcher("create_account.jsp").forward(request, response);
        }
    }

}


