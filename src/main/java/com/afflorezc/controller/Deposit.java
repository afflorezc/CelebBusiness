package com.afflorezc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.afflorezc.model.BankOperations;
import com.afflorezc.model.BankAccount;
import com.afflorezc.model.Transaction;
import com.afflorezc.model.User;
import com.afflorezc.dao.BankAccountDAO;
import com.afflorezc.dao.PersonDAO;
import com.afflorezc.dao.TransactionDAO;

@WebServlet("/deposit")
public class Deposit extends HttpServlet {

    private PersonDAO personDAO;
    private BankOperations bankOperations;
    private BankAccountDAO bankAccountDAO;
    private TransactionDAO transactionDAO;

    public Deposit(){

        this.personDAO = new PersonDAO();
        this.bankOperations = new BankOperations();
        this.bankAccountDAO = new BankAccountDAO();
        this.transactionDAO = new TransactionDAO();
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
            // Creación de variable de sesion para uso
            int personID = user.getPersonID();
            if(!personDAO.validPerson(personID)){
                request.setAttribute("message", "Unexpected error, please log in again");
                session.removeAttribute("user_session");
                request.getRequestDispatcher("user_login.jsp").forward(request, response);
            }
            
            int bankAccountNumber = Integer.parseInt(request.getParameter("user-accounts"));
            BankAccount bankAccount = bankAccountDAO.selectAccountByNumber(bankAccountNumber);
            if(bankAccount == null){
                request.setAttribute("message", "Wrong account selection");
                request.getRequestDispatcher("deposit.jsp").forward(request, response);
            }

            double depositValue = Double.parseDouble(request.getParameter("deposit-value"));
            
            Transaction transaction = bankOperations.createBankTransaction(bankAccount, "Deposit", depositValue);
            
            if(transaction == null){
                request.setAttribute("message", "An unexpected error occurs, try again");
                request.getRequestDispatcher("deposit.jsp").forward(request, response);
            }
            // Actualizacion de los datos de las cuentas
            bankAccountDAO.updateBalance(bankAccountNumber, bankAccount.getBalance()+depositValue);
            transactionDAO.insertTransaction(transaction);
            // Redireccionamiento y respuesta del resultado
            request.setAttribute("message", "Investment opened sucessfully");
            request.getRequestDispatcher("deposit.jsp").forward(request, response);
        }

    }

}
