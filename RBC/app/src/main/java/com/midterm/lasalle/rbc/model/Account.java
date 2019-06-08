package com.midterm.lasalle.rbc.model;

public class Account {

    private double balance;
    private String email;

    public Account(){
    }

    public Account(double balance, String email) {
        this.balance = balance;
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setABalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account=" + balance +
                ", email='" + email + '\'' +
                '}';
    }
}
