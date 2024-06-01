package com.afflorezc.model;

public class User {

    private int clientID;
    private int personID;
    private String userName;
    private String password;
    private boolean celebrity;
    private boolean hasBankAccount;
    private String userType;

    public User() {

    }

    public User(String userName, String password, boolean celebrity, boolean hasBankAccount, String userType) {
        this.userName = userName;
        this.password = password;
        this.celebrity = celebrity;
        this.hasBankAccount = hasBankAccount;
        this.userType = userType;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCelebrity() {
        return celebrity;
    }

    public void setCelebrity(boolean celebrity) {
        this.celebrity = celebrity;
    }

    public boolean hasBankAccount() {
        return hasBankAccount;
    }

    public void setHasBankAccount(boolean hasBankAccount) {
        this.hasBankAccount = hasBankAccount;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    

}
