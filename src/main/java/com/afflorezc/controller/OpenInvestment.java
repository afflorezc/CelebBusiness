package com.afflorezc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.io.IOException;

import com.afflorezc.model.InversionAccount;
import com.afflorezc.model.BankOperations;
import com.afflorezc.model.BankAccount;
import com.afflorezc.model.BankTransfer;
import com.afflorezc.model.User;
import com.afflorezc.dao.BankAccountDAO;
import com.afflorezc.dao.InversionAccountDAO;
import com.afflorezc.dao.BankTransferDAO;
import com.afflorezc.dao.PortfolioDAO;
import com.afflorezc.dao.PersonDAO;

@WebServlet("/open_investment")
public class OpenInvestment extends HttpServlet {

    private InversionAccountDAO inversionAccountDAO; 
    private PersonDAO personDAO;
    private BankOperations bankOperations;
    private PortfolioDAO portfolioDAO;
    private BankAccountDAO bankAccountDAO;
    private BankTransferDAO bankTransferDAO;

    public OpenInvestment(){
        this.inversionAccountDAO = new InversionAccountDAO();
        this.personDAO = new PersonDAO();
        this.bankOperations = new BankOperations();
        this.portfolioDAO = new PortfolioDAO();
        this.bankAccountDAO = new BankAccountDAO();
        this.bankTransferDAO = new BankTransferDAO();
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
                request.getRequestDispatcher("open-investment.jsp").forward(request, response);
            }

            String investmentType = request.getParameter("investment-type");
            int portfolioID = 0;

            if(investmentType.equals("Investment Fund")){

                String portfolioName = request.getParameter("portfolios");
                portfolioID = portfolioDAO.getPortfolioID(portfolioName);

                if(portfolioID <0){
                    request.setAttribute("message", "Wrong portfolio Selection");
                    request.getRequestDispatcher("open-investment.jsp").forward(request, response);
                }

                List<String> userPortfolios = inversionAccountDAO.selectPortfoliosForUser(personID);
                for(String portfolio:userPortfolios){
                    if(portfolio.equals(portfolioName)){
                        request.setAttribute("message", "You already have an investment in the selected Portfolio");
                        request.getRequestDispatcher("open-investment.jsp").forward(request, response);
                    }
                }
            }

            double inversionValue = Double.parseDouble(request.getParameter("investment"));
            InversionAccount inversionAccount = bankOperations.createNewInvestment(investmentType,personID,
                                                                            portfolioID,0);
            
            inversionAccountDAO.insertInversion(inversionAccount);
            // Creación de la transferencia de apertura del fondo
            BankTransfer bankTransfer = bankOperations.createBankTransfer(bankAccount, inversionAccount, inversionValue);
            
            if(bankTransfer == null){
                inversionAccountDAO.deleteAccount(inversionAccount.getInversionNumber());
                request.setAttribute("message", "An unexpected error occurs, try again");
                request.getRequestDispatcher("open-investment.jsp").forward(request, response);
            }
            // Actualizacion de los datos de las cuentas
            bankAccountDAO.updateBalance(bankAccountNumber, bankAccount.getBalance());
            inversionAccountDAO.updateBalance(inversionAccount.getInversionNumber(), inversionAccount);
            bankTransferDAO.insertBankTransfer(bankTransfer);
            // Redireccionamiento y respuesta del resultado
            request.setAttribute("message", "Investment opened sucessfully");
            request.getRequestDispatcher("open-investment.jsp").forward(request, response);
        }

    }

}