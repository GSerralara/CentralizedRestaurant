package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Dish;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DishDAO {
    public void addDish(Dish dish){
        String query = "";
        BBDDHelper.getInstance().insertRegister(query);
    }
    public LinkedList<Dish> getAllDishes(){
        LinkedList<Dish> dishes =  new LinkedList<>();
        String query = "";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                //add
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dishes;
    }
    public void deleteDish(){
        String query = "";
        BBDDHelper.getInstance().deleteRegister(query);
    }
}
