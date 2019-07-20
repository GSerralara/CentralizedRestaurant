package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * UserDao class.
 */

public class UserDAO {
    /**
     * Add a User
     * @param user User.
     */
    public void addUser(User user){
        String query = "INSERT INTO Customer VALUES ('"+user.getUser()+"','"+user.getPassword()+"');";
        BBDDHelper.getInstance().insertData(query);
    }

    /**
     * Getter a list of Users
     * @return a list.
     */
    public LinkedList<User> getAllUsers(){
        LinkedList<User> users = new LinkedList<>();
        String query = "SELECT * FROM Customer";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                //add
                User u = new User(resultat.getString("username"),resultat.getString("password"));
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
    public void addReserve(){

    }
}
