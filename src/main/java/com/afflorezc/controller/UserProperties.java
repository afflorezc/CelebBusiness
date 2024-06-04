package com.afflorezc.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.afflorezc.dao.PropertyDAO;
import com.afflorezc.model.Property;
import com.afflorezc.model.User;

@WebServlet("/user_properties")

public class UserProperties extends HttpServlet{
    PropertyDAO propertyDAO;

    public UserProperties() {
        propertyDAO = new PropertyDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

        List<Property> properties = new ArrayList<Property>();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_session");

        if (user == null) {
            response.sendRedirect("user_login.jsp");
        }else{
            int id = user.getPersonID();

            properties = propertyDAO.selectPropertyByOwner(id);
            request.setAttribute("properties", properties);
            request.getRequestDispatcher("user_properties.jsp").forward(request, response);
        }


    }
}

