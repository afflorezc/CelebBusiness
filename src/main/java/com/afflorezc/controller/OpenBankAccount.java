package com.afflorezc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.afflorezc.model.BankAccount;
import com.afflorezc.model.BankOperations;
import com.afflorezc.model.User;
import com.afflorezc.dao.BankAccountDAO;
import com.afflorezc.dao.PersonDAO;

@WebServlet("/open_bank_account")
public class OpenBankAccount extends HttpServlet {

    private BankAccountDAO bankAccountDAO; 
    private PersonDAO personDAO;
    private BankOperations bankOperations;

    public OpenBankAccount(){
        this.bankAccountDAO = new BankAccountDAO();
        this.personDAO = new PersonDAO();
        this.bankOperations = new BankOperations();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

        HttpSession session =  request.getSession();
        User user = (User) session.getAttribute("user_session");

        if(user == null){
            request.setAttribute("message", "Unexpected error, please log in again");
            session.removeAttribute("user_session");
            request.getRequestDispatcher("user_login.jsp").forward(request, response);
        }else{
            
            int personID = user.getPersonID();
            if(!personDAO.validPerson(personID)){
                request.setAttribute("message", "Unexpected error, please log in again");
                session.removeAttribute("user_session");
                request.getRequestDispatcher("user_login.jsp").forward(request, response);
            }
            
            String accountType = request.getParameter("account-type");
            
            if(bankAccountDAO.userHasAccount(personID, accountType)){
                request.setAttribute("message", "Users can have only one account of each type");
                request.getRequestDispatcher("bank.jsp").forward(request, response);
            }

            BankAccount bankAccount = bankOperations.createNewAccount(personID, accountType);
            if(bankAccount != null){
                bankAccountDAO.insertAccount(bankAccount);
            }
            response.sendRedirect("bank.jsp");
            
        }

    }

}

