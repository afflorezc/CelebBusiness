package com.afflorezc.model;

public class Bid {
    private int bidID;
    private int auctionID;
    private int offererID;
    private double bidValue;
    private String message;

    public Bid(int bidID, int auctionID, int offererID, double bidValue, String message) {
        this.bidID = bidID;
        this.auctionID = auctionID;
        this.offererID = offererID;
        this.bidValue = bidValue;
        this.message = message;
    }

    public Bid(int bidID, int auctionID, int offererID, double bidValue) {
        this.bidID = bidID;
        this.auctionID = auctionID;
        this.offererID = offererID;
        this.bidValue = bidValue;
    }

    public int getBidID() {
        return bidID;
    }
    public void setBidID(int bidID) {
        this.bidID = bidID;
    }
    public int getAuctionID() {
        return auctionID;
    }
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
    public int getOffererID() {
        return offererID;
    }
    public void setOffererID(int offererID) {
        this.offererID = offererID;
    }
    public double getBidValue() {
        return bidValue;
    }
    public void setBidValue(double bidValue) {
        this.bidValue = bidValue;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
