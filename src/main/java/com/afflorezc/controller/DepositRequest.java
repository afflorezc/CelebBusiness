package com.afflorezc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.afflorezc.model.BankAccount;
import com.afflorezc.model.User;
import com.afflorezc.dao.PersonDAO;
import com.afflorezc.dao.BankAccountDAO;

@WebServlet("/deposit-request")
public class DepositRequest extends HttpServlet {

    private PersonDAO personDAO;
    private BankAccountDAO bankAccountDAO;

    public DepositRequest(){
        this.personDAO = new PersonDAO();
        this.bankAccountDAO = new BankAccountDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

        HttpSession session =  request.getSession();
        User user = (User) session.getAttribute("user_session");

        if(user == null){
            request.setAttribute("message", "Unexpected error, please log in again");
            session.removeAttribute("user_session");
            request.getRequestDispatcher("user_login.jsp").forward(request, response);
        }else{
            // Creación de variable de sesion para uso
            int personID = user.getPersonID();
            if(!personDAO.validPerson(personID)){
                request.setAttribute("message", "Unexpected error, please log in again");
                session.removeAttribute("user_session");
                request.getRequestDispatcher("user_login.jsp").forward(request, response);
            }

            List<BankAccount> accounts = bankAccountDAO.selectAccountByPersonID(personID);
            request.setAttribute("bank-accounts", accounts);
            request.getRequestDispatcher("deposit.jsp").forward(request, response);
        }

    }

}
