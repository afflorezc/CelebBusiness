package com.afflorezc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.afflorezc.dao.PropertyDAO;
import com.afflorezc.model.Property;

@WebServlet("/search_propeties")
public class SearchPropeties extends HttpServlet{
    PropertyDAO propertyDAO;

    public SearchPropeties() {
        propertyDAO = new PropertyDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

            List<Property> properties = new ArrayList<Property>();

            properties = propertyDAO.selectAllProperties();
            request.setAttribute("properties", properties);
            request.getRequestDispatcher("search_properties.jsp").forward(request, response);
            
       }
}
