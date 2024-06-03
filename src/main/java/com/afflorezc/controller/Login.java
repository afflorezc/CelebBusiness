package com.afflorezc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.afflorezc.utils.Encryption;
import com.afflorezc.model.User;
import com.afflorezc.dao.UserDAO;

@WebServlet("/login")
public class Login extends HttpServlet {

    private UserDAO userDAO; 

    public Login(){
        this.userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Se encripta la contraseña utilizando la libreria 'jBCrypt'
        //password = Encryption.encryptWord(password);

        User user = userDAO.selectUserByUserName(username);
        
        System.out.println(username);

        if(user == null){
            request.setAttribute("message", "Wrong username");
            request.getRequestDispatcher("user_login.jsp").forward(request, response);
        } else if(!Encryption.checkPassword(password, user.getPassword())){
            request.setAttribute("message", "Wrong password");
            System.out.println("Contraseña incorrecta");
            request.getRequestDispatcher("user_login.jsp").forward(request, response);
        }else{
            // Creación de variable de sesion para uso
            HttpSession session = request.getSession(true);
            session.setAttribute("user_session", user);
            //Redireccionamiento
            if(user.getUserType().equals("admin")){
                response.sendRedirect("admin_session.jsp");
            } else if(user.getUserType().equals("celebrity")){
                response.sendRedirect("celeb_session.jsp");
            } else{
                response.sendRedirect("user_session.jsp");
            }
            
        }

    }

}
