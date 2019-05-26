package com.midterm.lasalle.loginregistration.dao;

import com.midterm.lasalle.loginregistration.model.User;

import java.util.Collection;
import java.util.LinkedList;

public class UserDao {

    private LinkedList<User> users = new LinkedList<>();

    public UserDao(){
    }

    public UserDao(LinkedList<User> users) {
        this.users = users;
    }

    public boolean addUser(User user){
        if (users.contains(user)){
            return false;
        }else{
            users.add(user);
            return true;
        }
    }

    public boolean removeUser(User user){
        if (users.contains(user)){
            users.remove(user);
            return  true;
        }else{
            return false;
        }
    }

    public boolean updateUser(User user, User newUser){
        if (!users.contains(user)){
            return false;
        }else{
            users.remove(user);
            users.add(user);
            return true;
        }
    }

    public boolean loginUser(String email, String password){
        for (User user: users){
            if ((user.getEmail().equals(email)) && (user.getPassword().equals(password))){
               return true;
            }
        }
        return false;
    }

    public User getUserByEmail(String email){
        for (User user: users) {
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public  User getUserByPassword(String password){
        for (User user:users) {
            if (user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public User getUser(User user){
        if (users.contains(user)){
            return users.get(users.indexOf(user));
        }else{
            return null;
        }
    }

    public User getUserById(int id){
        if (users.size() -1 >= id){
            return users.get(id);
        }else{
            return null;
        }
    }

    public boolean contains(User user){
        return users.contains(user);
    }

    public boolean contains(String email){
        for (User user: users) {
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public void clear(){
        users.clear();
    }

    public Collection<User> getAll(){
        return users;
    }
}
