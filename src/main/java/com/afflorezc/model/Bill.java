package com.afflorezc.model;

import java.sql.Date;

public class Bill {
    private int billNumber;
    private int contractID;
    private double basePrice;
    private double taxes;
    private double comisions;
    private double fines;
    private Date billDate;

    public Bill() {
        this.billNumber = -1;
        this.contractID = -1;
        this.basePrice = -1;
        this.taxes = -1;
        this.comisions = -1;
        this.fines = -1;
        this.billDate = Date.valueOf("");
    }

    public Bill(int billNumber, int contractID, double basePrice, double taxes, double comisions, double fines,
            Date billDate) {
        this.billNumber = billNumber;
        this.contractID = contractID;
        this.basePrice = basePrice;
        this.taxes = taxes;
        this.comisions = comisions;
        this.fines = fines;
        this.billDate = billDate;
    }
    
    public int getBillNumber() {
        return billNumber;
    }
    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }
    public int getContractID() {
        return contractID;
    }
    public void setContractID(int contractID) {
        this.contractID = contractID;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    public double getTaxes() {
        return taxes;
    }
    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }
    public double getComisions() {
        return comisions;
    }
    public void setComisions(double comisions) {
        this.comisions = comisions;
    }
    public double getFines() {
        return fines;
    }
    public void setFines(double fines) {
        this.fines = fines;
    }
    public Date getBillDate() {
        return billDate;
    }
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

}