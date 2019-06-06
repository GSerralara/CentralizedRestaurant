package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserDAO {
    public void addUser(User user){
        String query = "INSERT INTO Restaurant VALUES(\""+user.getMail()+"\",\""+user.getUser()+"\",\""+user.getPassword()+"\");";
        BBDDHelper.getInstance().insertData(query);
    }
    public LinkedList<User> getAllUsers(){
        LinkedList<User> users = new LinkedList<>();
        String query = "SELECT * FROM restaurant";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                //add
                User u = new User(resultat.getString("username"),resultat.getString("email"),resultat.getString("password"));
                users.add(u);
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
