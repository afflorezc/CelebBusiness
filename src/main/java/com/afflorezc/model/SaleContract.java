package com.afflorezc.model;

import java.sql.Date;

public class SaleContract {
    private int contractID;
    private int propertyID;
    private double sellPrice;
    private int sellerID;
    private int buyerID;
    private Date contractDate; 
    private Date paymentDate;
    private int auctionNumber;
    private String contractState;
    private Date fulfillmentDate;

    public SaleContract() {
        this.contractID = -1;
        this.propertyID = -1;
        this.sellPrice = -1;
        this.sellerID = -1;
        this.buyerID = -1;
        this.contractDate = Date.valueOf("");
        this.paymentDate = Date.valueOf("");
        this.auctionNumber = -1;
        this.contractState = "";
        this.fulfillmentDate = Date.valueOf("");
    }

    public SaleContract(int contractID, int propertyID, double sellPrice, int sellerID, int buyerID, Date contractDate,
            Date paymentDate, int auctionNumber, String contractState, Date fulfillmentDate) {
        this.contractID = contractID;
        this.propertyID = propertyID;
        this.sellPrice = sellPrice;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.contractDate = contractDate;
        this.paymentDate = paymentDate;
        this.auctionNumber = auctionNumber;
        this.contractState = contractState;
        this.fulfillmentDate = fulfillmentDate;
    }

    public int getContractID() {
        return contractID;
    }
    public void setContractID(int contractID) {
        this.contractID = contractID;
    }
    public int getPropertyID() {
        return propertyID;
    }
    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }
    public double getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
    public int getSellerID() {
        return sellerID;
    }
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
    public int getBuyerID() {
        return buyerID;
    }
    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }
    public Date getContractDate() {
        return contractDate;
    }
    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public int getAuctionNumber() {
        return auctionNumber;
    }
    public void setAuctionNumber(int auctionNumber) {
        this.auctionNumber = auctionNumber;
    }
    public String getContractState() {
        return contractState;
    }
    public void setContractState(String contractState) {
        this.contractState = contractState;
    }
    public Date getFulfillmentDate() {
        return fulfillmentDate;
    }
    public void setFulfillmentDate(Date fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }
    
}
