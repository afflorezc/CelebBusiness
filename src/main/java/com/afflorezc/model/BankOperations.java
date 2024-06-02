package com.afflorezc.model;

import java.sql.Date;
import java.time.LocalDate;

public class BankOperations {

    private static final String BANK_CODE = "42";
    private static final String SAVINGS_ACCOUNT_CODE = "01";
    private static final String CHECKING_ACCOUNT_COD = "02";
    private static final String CDT_INVESTMENT_COD = "03";
    private static final String INVESTMENT_FUND_COD = "04";
    private static int clientsCapacity = 8;
    private static int savingClientNumber = 0;
    private static int checkingClientNumber = 0;
    private static int cdtClientNumber = 0;
    private static int investmentOneClientNumber = 0;
    private static int investmentTwoClientNumber = 0;
    private static int investmentThreeClientNumber = 0;
    private static int investmentFourClientNumber = 0;
    private static int investmentFiveClientNumber = 0;

    public BankAccount createNewAccount(int personID, String accountType){
        
        int clientNumber;
        String accountNumber = BANK_CODE;
        if(accountType.equals("Saving")){
            accountNumber += SAVINGS_ACCOUNT_CODE;
            savingClientNumber++;
            clientNumber = savingClientNumber;
        } else if(accountType.equals("Checking")){
            accountNumber += CHECKING_ACCOUNT_COD;
            checkingClientNumber++;
            clientNumber = checkingClientNumber;
        } else {
            return null;
        }

        String currentNumber = Integer.toString(clientNumber);
        int digitsToAdd = clientsCapacity - currentNumber.length();
        for(int i=0;i<digitsToAdd;i++){
            accountNumber += Integer.toString(0);
        }
        accountNumber += currentNumber;
        
        BankAccount bankAccount = new BankAccount();
        LocalDate today = LocalDate.now();
        Date openDate = Date.valueOf(today);

        bankAccount.setPersonID(personID);
        bankAccount.setAccountNumber(Integer.parseInt(accountNumber));
        bankAccount.setAnnualInterest(setInterestRate(accountType));
        bankAccount.setAccountType(accountType);
        bankAccount.setOpenDate(openDate);

        return bankAccount;
    }

    public double setInterestRate(String accountType){
        
        if(accountType.equals("Saving")){
            return 0.08;
        } else if(accountType.equals("Checking")){
            return 0.04;
        } else {
            return 0.0;
        }
    }

    public InversionAccount createNewInvestment(String investmentType, int personID, int portfolioID,
                                                    double initialAmount){

        if(initialAmount <=0){
            return null;
        }
        int clientNumber;
        String accountNumber = BANK_CODE;
        if(investmentType.equals("CDT")){
            accountNumber += CDT_INVESTMENT_COD;
            cdtClientNumber++;
            clientNumber = cdtClientNumber;
        } else if(investmentType.equals("Investment Fund")){
            accountNumber += INVESTMENT_FUND_COD;
            clientNumber = getInvestmentFundCurrentClient(portfolioID);
            if(clientNumber == 0){
                return null;
            }
        } else{
            return null;
        }

        String currentNumber = Integer.toString(clientNumber);
        int digitsToAdd = clientsCapacity - currentNumber.length();
        for(int i=0;i<digitsToAdd;i++){
            accountNumber += Integer.toString(0);
        }
        accountNumber += currentNumber;

        InversionAccount investment = new InversionAccount();
        LocalDate today = LocalDate.now();
        Date openDate = Date.valueOf(today);

        investment.setInversionNumber(Integer.parseInt(accountNumber));
        investment.setInversionType(investmentType);
        investment.setBalance(initialAmount);
        investment.setOpenDate(openDate);
        investment.setPortfolioID(portfolioID);
        investment.setPersonID(personID);

        return investment;
    }

    public int getInvestmentFundCurrentClient(int portfolioID){
        
        switch(portfolioID){
            case 1:
                return ++investmentOneClientNumber;
            case 2:
                return ++investmentTwoClientNumber;
            case 3:
                return ++investmentThreeClientNumber;
            case 4:
                return ++investmentFourClientNumber;
            case 5: 
                return ++investmentFiveClientNumber;
        }
        return 0;
    }

    public double calculateInterest(int elapsedDays, double interestRate, double balance){

        if(elapsedDays <=0){
            return 0;
        }

        double dailyRate = interestRate/365;
        double interest = balance*Math.pow(1+dailyRate,365*elapsedDays);
        interest -= balance;
        return interest;
    }

    public void updateBalanceByInterest(BankAccount bankAccount){

        if(!bankAccount.isActive()){
            return;
        }
        double balance = bankAccount.getBalance();
        double interestRate = bankAccount.getAnnualInterest();
        LocalDate today = LocalDate.now();
        Date openDate = bankAccount.getOpenDate();
        LocalDate open = openDate.toLocalDate();
        int elapsedDays = today.getDayOfYear() - open.getDayOfYear();

        double interestValue = calculateInterest(elapsedDays, interestRate, balance);
        bankAccount.setBalance(balance+interestValue);
    }

}
