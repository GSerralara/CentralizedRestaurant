package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Restaurant;
import Resources.Pop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantDAO {
    public RestaurantDAO() {
    }

    public void addRestaurant(Restaurant restaurant){
        String msg = "";
        int outputValue = BBDDHelper.getInstance().insertRegister("Restaurant-New",restaurant);
        if(outputValue ==1){
            msg = "Registration was Successful!";
        }else{
            msg = "Error, during registration";
        }
        Pop pop = new Pop(msg);
    }

    public int logRestaurant(Restaurant restaurant){
        String msg = "";
        int outputValue = BBDDHelper.getInstance().insertRegister("Restaurant-Log",restaurant);
        if(outputValue ==1){
            msg = "Login was Successful!";
        }else{
            msg = "Error, during login";
        }
        Pop pop = new Pop(msg);
        return outputValue;
    }

    public Restaurant getRestaurant(Restaurant restaurant){
        String query = "";
        Restaurant aux;
        if(restaurant.getUser().equals("")){
            query = "SELECT * " +
                    "FROM restaurant " +
                    "WHERE email LIKE '"+restaurant.getMail()+"';";
        }else{
            query = "SELECT * " +
                    "FROM restaurant " +
                    "WHERE username LIKE '"+restaurant.getUser()+"';";
        }
        ResultSet rs = BBDDHelper.getInstance().selectTable(query);
        try {
            rs.next();
            aux = new Restaurant(rs.getString(2),rs.getString(1),rs.getString(3));
            return aux;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
