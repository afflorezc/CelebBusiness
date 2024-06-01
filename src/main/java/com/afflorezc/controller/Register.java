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
import com.afflorezc.dao.UserDAO;
import com.afflorezc.dao.PersonDAO;

@WebServlet("/register")
public class Register extends HttpServlet {

    private PersonDAO personDAO;
    private UserDAO userDAO;

    public Register(){
        this.personDAO = new PersonDAO();
        this.userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

        String username = request.getParameter("username");
        String document = request.getParameter("document");
        if(username !=null && !username.equals("")){

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
                System.out.println("personID: "+personID);

                String password = request.getParameter("password");
                String isCelebrity = request.getParameter("celebrity");
                String encryptedPassword = Encryption.encryptWord(password);
                User newUser = new User();
                newUser.setUserName(username);
                newUser.setPassword(encryptedPassword);
                newUser.setHasBankAccount(false);
                newUser.setPersonID(personID);

                System.out.println("PersonID seteado al objeto user: "+newUser.getPersonID());

                if(isCelebrity.equals("1")){
                    newUser.setUserType("celebrity");
                    newUser.setCelebrity(true);
                } else {
                    newUser.setUserType("user");
                    newUser.setCelebrity(false);
                }
                
                userDAO.insertUser(newUser);
                HttpSession session = request.getSession();
                session.setAttribute("registering", newPerson);
                // Hacer que se devuelva al registro pero se cambie el form para que ingrese
                // datos personales. O manejarlo desde otra pagina
                response.sendRedirect("register.jsp");
            }
        } else if(!document.equals("")){
            // El usuario ya existe en la base de datos, igualmente el registro en tabla persona con
            // valores inicializados
            Person newPerson = (Person) request.getSession().getAttribute("registering");
            int personID = newPerson.getPersonID();
            System.out.println("PersonID: " +personID);
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
        }
 
    }


}
