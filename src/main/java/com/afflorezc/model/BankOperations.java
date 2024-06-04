package com.afflorezc.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BankOperations {

    private static final String BANK_CODE = "19";
    private static final String SAVINGS_ACCOUNT_CODE = "1";
    private static final String CHECKING_ACCOUNT_COD = "2";
    private static final String CDT_INVESTMENT_COD = "3";
    private static final String INVESTMENT_FUND_COD = "4";
    private static int clientsCapacity = 7;
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
        if(portfolioID > 0){
            investment.setPortfolioID(portfolioID);
        }
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

    public void depositToBankAccount(BankAccount bankAccount, double deposit){
        double balance = bankAccount.getBalance() + deposit;
        bankAccount.setBalance(balance);
    }

    public void depositToInversionAccount(InversionAccount inversionAccount, double deposit){
        double balance = inversionAccount.getBalance() + deposit;
        inversionAccount.setBalance(balance);
    }

    public boolean withDrawalFromBankAccount(BankAccount bankAccount, double amount){
        
        if(bankAccount.getBalance() < amount){
            return false;
        }
        double balance = bankAccount.getBalance() - amount;
        bankAccount.setBalance(balance);
        return true;
    }

    public boolean withDrawalFromInversion(InversionAccount inversionAccount, double amount){
        
        if(inversionAccount.getBalance() < amount){
            return false;
        }
        double balance = inversionAccount.getBalance() - amount;
        inversionAccount.setBalance(balance);
        return true;
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

    public BankTransfer createBankTransfer(BankAccount bankAccount, InversionAccount inversionAccount,
                                                double transferValue){
        
        BankTransfer bankTransfer = new BankTransfer();
        bankTransfer.setEmiterInitialBalance(bankAccount.getBalance());
        if(!withDrawalFromBankAccount(bankAccount, transferValue)){
            return null;
        }

        bankTransfer.setEmiterAccount(bankAccount.getAccountNumber());
        bankTransfer.setReceptorAccount(inversionAccount.getInversionNumber());
        bankTransfer.setTransferAmount(transferValue);
        bankTransfer.setReceptorInitialBalance(inversionAccount.getBalance());
        bankTransfer.setEmiterFinalBalance(bankAccount.getBalance());

        depositToInversionAccount(inversionAccount, transferValue);
        bankTransfer.setReceptorFinalBalance(inversionAccount.getBalance());

        LocalDateTime today = LocalDateTime.now();
        Timestamp transferDate = Timestamp.valueOf(today);
        bankTransfer.setTransferDate(transferDate);
        
        return bankTransfer;
    }

    public void transferToBankAccount(BankAccount emiterAccount, BankAccount receptorAccount){

    }

    public void transferToInvestment(BankAccount emiterAccount, InversionAccount inversionAccount){

    }

}
