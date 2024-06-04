package com.afflorezc.controller;

import java.io.IOException;

import org.eclipse.tags.shaded.org.apache.xpath.functions.FuncFalse;

import com.afflorezc.dao.PersonDAO;
import com.afflorezc.dao.PropertyDAO;
import com.afflorezc.dao.UserDAO;
import com.afflorezc.model.Property;
import com.afflorezc.model.User;
import com.afflorezc.model.Person;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.afflorezc.utils.ValidateInputs;

@WebServlet("/register_property")
public class RegisterProperty extends HttpServlet{
    PropertyDAO propertyDAO;
    UserDAO userDAO;
    PersonDAO personDAO;

    public RegisterProperty() {
        this.propertyDAO = new PropertyDAO();
        this.personDAO = new PersonDAO();
        this.userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        User user = (User) sesion.getAttribute("user_session");

        String countryLocation = request.getParameter("countryLocation");
        String stateLocation = request.getParameter("stateLocation");
        String cityLocation = request.getParameter("cityLocation");
        String address = request.getParameter("address");
        String propertyStatus = "On Sale";
        double valuation = Double.parseDouble(request.getParameter("valuation"));
        String description = request.getParameter("description");

        System.out.println(countryLocation+" en el servlet");

        if (user == null) { // validando que la sesion este iniciada
            response.sendRedirect("user_login.jsp");
        }else{
            if (ValidateInputs.validateNoVoidFields(request) == false) { // validando que ningun campo quedo vacio
                request.setAttribute("error", "You must fill all the fields");
                request.getRequestDispatcher("register_property.jsp").forward(request, response);
            }else{
                System.out.println("entro!!!");
                int owener = user.getPersonID();

                Property property = new Property(countryLocation, stateLocation, cityLocation, address, owener, propertyStatus, valuation, description);
                
                propertyDAO.insertProperty(property);

                response.sendRedirect("user_session.jsp");
            }
        }
    }
    
}
