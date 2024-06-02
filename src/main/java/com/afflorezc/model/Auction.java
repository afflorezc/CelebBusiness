package com.afflorezc.model;

import java.sql.Date;

public class Auction {
    private int auctionID;
    private Date auctionDate;
    private int offeredProperties;
    private int sales;
    private int adminID;
    
    public Auction() { // valores por defecto de inicializacion
        this.auctionID = -1;
        this.auctionDate = Date.valueOf("");
        this.offeredProperties = -1;
        this.sales = -1;
        this.adminID = -1;
    }

    public Auction(int auctionID, Date auctionDate, int offeredProperties, int sales, int adminID) {
        this.auctionID = auctionID;
        this.auctionDate = auctionDate;
        this.offeredProperties = offeredProperties;
        this.sales = sales;
        this.adminID = adminID;
    }

    public Auction(Date auctionDate, int offeredProperties, int sales, int adminID) {
        this.auctionDate = auctionDate;
        this.offeredProperties = offeredProperties;
        this.sales = sales;
        this.adminID = adminID;
    }

    public int getAuctionID() {
        return auctionID;
    }
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
    public Date getAuctionDate() {
        return auctionDate;
    }
    public void setAuctionDate(Date auctionDate) {
        this.auctionDate = auctionDate;
    }
    public int getOfferedProperties() {
        return offeredProperties;
    }
    public void setOfferedProperties(int offeredProperties) {
        this.offeredProperties = offeredProperties;
    }
    public int getSales() {
        return sales;
    }
    public void setSales(int sales) {
        this.sales = sales;
    }
    public int getAdminID() {
        return adminID;
    }
    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    
}
