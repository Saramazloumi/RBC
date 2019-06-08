package com.midterm.lasalle.rbc.dao;

import com.midterm.lasalle.rbc.model.Account;

import java.util.LinkedList;

public class AccountDao {

    private LinkedList<Account> list = new LinkedList<>();

    public AccountDao(){
    }

    public AccountDao(LinkedList<Account> list) {
        this.list = list;
    }

    public Account getUserByEmail(String email) {
        for (Account user : list) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean addUserAccount(Account account){
        if (list.contains(account)){
            return false;
        }else{
            list.add(account);
            return true;
        }
    }

    public Double getTotalBalance(){
        for (Account user: list){
                return user.getBalance();
        }
        return null;
    }

    public boolean contain(String email){
        for (Account user: list) {
            if (user.getEmail().equals(email)){
                return  true;
            }
        }
        return false;
    }

  public void updateBalance(String email, double newBalance){
      for (Account user: list)
          if (user.getEmail().equals(email)){
              user.setABalance(user.getBalance() + newBalance) ;
          }
  }


}
