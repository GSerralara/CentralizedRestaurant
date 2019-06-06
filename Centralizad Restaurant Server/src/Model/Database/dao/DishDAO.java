package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Dish;
import Model.Database.Entity.DishTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class DishDAO {
    public DishDAO() {
    }

    public void addDish(Dish dish){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dish.getTime());
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp ts = new Timestamp(cal.getTime().getTime());
        cal.clear(Calendar.DAY_OF_WEEK);
        String query = "INSERT INTO Dish (price, name, units, cooking_time, sold_out) VALUES" +
                " ("+dish.getPrice()+", \""+dish.getName()+"\", "+dish.getQuantety()+", '"+ts+"', FALSE);";
        BBDDHelper.getInstance().insertData(query);
    }
    public LinkedList<Dish> getAllDishes(){
        LinkedList<Dish> dishes =  new LinkedList<>();
        String query = "SELECT * FROM Dish;";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                Timestamp ts = resultat.getTimestamp("cooking_time");
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(ts.getTime());
                Dish d = new Dish(resultat.getInt("units"), resultat.getFloat("price"),
                        resultat.getString("name"), cal.getTime());
                 dishes.add(d);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dishes;
    }

    public LinkedList<DishTable> getTodayTopFiveDishes(){
        LinkedList<DishTable> dishes =  new LinkedList<>();
        String query = "SELECT d.name, SUM(t.quantity) AS num_dish FROM TableOrderDish AS t,Dish AS d WHERE d.id_dish = t.id_dish AND t.cur_service = TRUE GROUP BY t.id_dish ORDER BY num_dish DESC LIMIT 5;";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                int num_dish = resultat.getInt("num_dish");
                String dishName = resultat.getString("name");
                dishes.add(new DishTable(num_dish,dishName));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dishes;
    }

    public LinkedList<DishTable> getTopFiveDishes(){
        LinkedList<DishTable> dishes =  new LinkedList<>();
        String query = "SELECT d.name, SUM(t.quantity) AS num_dish FROM TableOrderDish AS t,Dish AS d WHERE d.id_dish = t.id_dish GROUP BY t.id_dish ORDER BY num_dish DESC LIMIT 5;";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);

        try {
            while (resultat.next()) {
                int num_dish = resultat.getInt("num_dish");
                String dishName = resultat.getString("name");
                dishes.add(new DishTable(num_dish,dishName));
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
