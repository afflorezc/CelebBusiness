package com.afflorezc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.afflorezc.model.User;


@WebServlet("/logout")
public class Logout extends HttpServlet {


    public Logout(){
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user_session");

            if(user != null){
                session.removeAttribute("user_session");
            }
            response.sendRedirect("index.jsp");
        }

}

