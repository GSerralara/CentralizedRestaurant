package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Restaurant;
import Resources.Pop;

import java.sql.ResultSet;

public class RestaurantDAO {
    public RestaurantDAO() {
    }

    public void addRestaurant(Restaurant restaurant){
        String msg = "";
        int outputValue = BBDDHelper.getInstance().insertRegister("Restaurant",restaurant);
        if(outputValue ==1){
            msg = "Registration was Successful!";
        }else{
            msg = "Error, during registration";
        }
        Pop pop = new Pop(msg);
    }
}
