package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserDAO {
    public void addUser(User user){
        String query = "";
        BBDDHelper.getInstance().insertRegister(query);
    }
    public LinkedList<User> getAllUsers(){
        LinkedList<User> users = new LinkedList<>();
        String query = "";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                //add
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    public void deleteUser(){
        String query = "";
        BBDDHelper.getInstance().deleteRegister(query);
    }
}
