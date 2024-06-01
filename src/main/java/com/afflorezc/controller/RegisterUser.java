package com.afflorezc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.afflorezc.model.Person;
import com.afflorezc.model.User;
import com.afflorezc.utils.Encryption;
import com.afflorezc.dao.PersonDAO;
import com.afflorezc.dao.UserDAO;

@WebServlet("/register_user")
public class RegisterUser extends HttpServlet {

    private UserDAO userDAO;
    private PersonDAO personDAO;

    public RegisterUser(){
        this.userDAO = new UserDAO();
        this.personDAO = new PersonDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username !=null && !username.equals("") && password != null && !password.equals("")){

            if(userDAO.userNameInUse(username)){
                request.setAttribute("message", "The selected username is in used, please try other one");
                request.getRequestDispatcher("create_account.jsp").forward(request, response);
            } else{
                // Se crea la persona con nombres y documento base "No document"
                LocalDate today = LocalDate.now();
                Date registrationDate = Date.valueOf(today);
                Person newPerson = new Person(registrationDate);
                
                personDAO.insertPerson(newPerson);
                int personID = -1;
                while (personID == -1){
                    personID = personDAO.getPersonIDByDocument(newPerson.getDocument());
                }
                
                newPerson.setPersonID(personID);
                String isCelebrity = request.getParameter("celebrity");
                //password = Encryption.encryptWord(password);
                User newUser = new User();
                newUser.setUserName(username);
                newUser.setPassword(password);
                newUser.setHasBankAccount(false);
                newUser.setPersonID(personID);

                if(isCelebrity.equals("1")){
                    newUser.setUserType("celebrity");
                    newUser.setCelebrity(true);
                } else {
                    newUser.setUserType("user");
                    newUser.setCelebrity(false);
                }
                
                userDAO.insertUser(newUser);
                HttpSession session = request.getSession(true);
                session.setAttribute("registering", newPerson);
                // Hacer que se devuelva al registro pero se cambie el form para que ingrese
                // datos personales. O manejarlo desde otra pagina
                response.sendRedirect("register.jsp");
            }
        } else {
            // No se completaron los campos
            request.setAttribute("message", "You must write an username and a password");
            request.getRequestDispatcher("create_account.jsp").forward(request, response);
        }
    }

}

