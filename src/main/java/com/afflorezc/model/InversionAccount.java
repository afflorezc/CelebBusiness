package com.afflorezc.model;

import java.sql.Date;

public class InversionAccount {

    private int inversionNumber;
    private int personID;
    private String inversionType;
    private double balance;
    private Date openDate;
    private Date dueDate;
    private double accumulatedProfit;
    private double chargedProfitOnTaxes;
    private boolean isEmbargoed;
    private double embargoedValue;
    private int portfolioID;

    public InversionAccount() {
        this.accumulatedProfit = 0;
        this.chargedProfitOnTaxes = 0;
        this.isEmbargoed = false;
        this.embargoedValue = 0;
        this.balance = 0;
    }

    public InversionAccount(int personID, String inversionType, double balance, Date openDate, double accumulatedProfit,
            double chargedProfitOnTaxes, boolean isEmbargoed, int portfolioID) {
        
        this.personID = personID;
        this.inversionType = inversionType;
        this.balance = balance;
        this.openDate = openDate;
        this.accumulatedProfit = accumulatedProfit;
        this.chargedProfitOnTaxes = chargedProfitOnTaxes;
        this.isEmbargoed = isEmbargoed;
        this.portfolioID = portfolioID;
    }

    public int getInversionNumber() {
        return inversionNumber;
    }

    public void setInversionNumber(int inversionNumber) {
        this.inversionNumber = inversionNumber;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getInversionType() {
        return inversionType;
    }

    public void setInversionType(String inversionType) {
        this.inversionType = inversionType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getAccumulatedProfit() {
        return accumulatedProfit;
    }

    public void setAccumulatedProfit(double accumulatedProfit) {
        this.accumulatedProfit = accumulatedProfit;
    }

    public double getChargedProfitOnTaxes() {
        return chargedProfitOnTaxes;
    }

    public void setChargedProfitOnTaxes(double chargedProfitOnTaxes) {
        this.chargedProfitOnTaxes = chargedProfitOnTaxes;
    }

    public boolean isEmbargoed() {
        return isEmbargoed;
    }

    public void setEmbargoed(boolean isEmbargoed) {
        this.isEmbargoed = isEmbargoed;
    }

    public double getEmbargoedValue() {
        return embargoedValue;
    }

    public void setEmbargoedValue(double embargoedValue) {
        this.embargoedValue = embargoedValue;
    }

    public int getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }    

}
