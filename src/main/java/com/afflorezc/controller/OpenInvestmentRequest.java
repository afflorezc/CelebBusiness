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
import com.afflorezc.model.Portfolio;
import com.afflorezc.dao.PersonDAO;
import com.afflorezc.dao.BankAccountDAO;
import com.afflorezc.dao.PortfolioDAO;

@WebServlet("/open_investment_request")
public class OpenInvestmentRequest extends HttpServlet {

    private PersonDAO personDAO;
    private BankAccountDAO bankAccountDAO;
    private PortfolioDAO portfolioDAO;

    public OpenInvestmentRequest(){
        this.personDAO = new PersonDAO();
        this.bankAccountDAO = new BankAccountDAO();
        this.portfolioDAO = new PortfolioDAO();
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
            if(accounts.size()==0){
                request.setAttribute("message", "You don't have enough money for openning an inversion: Fund your accounts first");
                request.setAttribute("deposit", "Deposit");
                request.getRequestDispatcher("bank-accounts.jsp").forward(request, response);
            }

            List<Portfolio> portfolios = portfolioDAO.selectAllPortfolios();
           
            List<Double> minInversion = portfolioDAO.minRequiredInvestment(portfolios);
            double requiredInvestment = portfolioDAO.maxRequiredInvestment(portfolios);

            boolean availableBalance = true;
            for(BankAccount account:accounts){
                
                if(account.getBalance() < requiredInvestment){
                    availableBalance = false;
                    break;
                }
            }

            if(!availableBalance){
                response.sendRedirect("bank-accounts.jsp");
            } else{
                request.setAttribute("accounts", accounts);
                request.setAttribute("portfolios-min-inversion", minInversion);
                response.sendRedirect("open-investment.jsp");
            }
            
        }

    }

}
