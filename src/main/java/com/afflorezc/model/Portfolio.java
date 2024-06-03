package com.afflorezc.model;

public class Portfolio {

    private int portfolioID;
    private String portfolioName;
    private double unitValue;
    private double minimumInvestment;
    private double commision;
    private int riskGrade;

    public Portfolio() {
    }

    public Portfolio(String portfolioName, double unitValue, double minimumInvestment,int riskGrade, double commision) {
        this.portfolioName = portfolioName;
        this.unitValue = unitValue;
        this.minimumInvestment = minimumInvestment;
        this.riskGrade = riskGrade;
        this.commision = commision;
    }

    public int getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public int getRiskGrade() {
        return riskGrade;
    }

    public void setRiskGrade(int riskGrade) {
        this.riskGrade = riskGrade;
    }

    public double getCommision() {
        return commision;
    }

    public void setCommision(double commision) {
        this.commision = commision;
    }

    public double getMinimumInvestment() {
        return minimumInvestment;
    }

    public void setMinimumInvestment(double minimumInvestment) {
        this.minimumInvestment = minimumInvestment;
    }

}
