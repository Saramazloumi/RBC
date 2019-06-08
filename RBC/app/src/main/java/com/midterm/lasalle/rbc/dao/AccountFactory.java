package com.midterm.lasalle.rbc.dao;

public class AccountFactory {

    private static AccountDao accountDao = new AccountDao();

    public static AccountDao getAccountDao(){
        return accountDao;
    }
}
