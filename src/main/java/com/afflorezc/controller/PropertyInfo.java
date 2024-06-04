package com.afflorezc.controller;

import java.io.IOException;

import com.afflorezc.dao.PersonDAO;
import com.afflorezc.dao.PropertyDAO;
import com.afflorezc.model.Person;
import com.afflorezc.model.Property;
import com.afflorezc.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/property")
public class PropertyInfo extends HttpServlet {
    PropertyDAO propertyDAO;
    PersonDAO personDAO;

    public PropertyInfo(){
        this.propertyDAO = new PropertyDAO();
        this.personDAO = new PersonDAO(); 
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException{
        int propertyID = Integer.parseInt(request.getParameter("propertyID"));

        Property property = propertyDAO.selectPropertyByID(propertyID); // obteniendo la propiedad

        Person person = personDAO.selectPersonByID(property.getOwner()); // obteniendo la informacion del dueño para obtener el nombre
        String name = person.getFirstName()+" "+person.getLastName1()+" "+person.getLastName2(); // concatenando el nombre

        request.setAttribute("name", name);
        request.setAttribute("property", property);
        
        request.getRequestDispatcher("property.jsp").forward(request, response);
    }
}
