package com.afflorezc.model;

public class Property {
    private int propertyID;
    private String countryLocation;
    private String stateLocation;
    private String cityLocation;
    private String address;
    private int owner;
    private boolean onSale;
    private double valuation;
    private String description;

    public Property() { // valores por defecto de inicializacion
        this.propertyID = -1;
        this.countryLocation = "";
        this.stateLocation = "";
        this.cityLocation = "";
        this.address = "";
        this.owner = -1;
        this.onSale = false;
        this.valuation = -1;
        this.description = "";
    }

    public Property(int propertyID, String countryLocation, String stateLocation, String cityLocation, String address,
            int owner, boolean onSale, double valuation, String description) {
        this.propertyID = propertyID;
        this.countryLocation = countryLocation;
        this.stateLocation = stateLocation;
        this.cityLocation = cityLocation;
        this.address = address;
        this.owner = owner;
        this.onSale = onSale;
        this.valuation = valuation;
        this.description = description;
    }
    
    public int getPropertyID() {
        return propertyID;
    }
    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }
    public String getCountryLocation() {
        return countryLocation;
    }
    public void setCountryLocation(String countryLocation) {
        this.countryLocation = countryLocation;
    }
    public String getStateLocation() {
        return stateLocation;
    }
    public void setStateLocation(String stateLocation) {
        this.stateLocation = stateLocation;
    }
    public String getCityLocation() {
        return cityLocation;
    }
    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getOwner() {
        return owner;
    }
    public void setOwner(int owner) {
        this.owner = owner;
    }
    public boolean isOnSale() {
        return onSale;
    }
    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }
    public double getValuation() {
        return valuation;
    }
    public void setValuation(double valuation) {
        this.valuation = valuation;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
