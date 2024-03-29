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

/**
 * Dish Dao class.
 */

public class DishDAO {
    public DishDAO() {
    }

    /**
     * Add a dish
     * @param dish Dish.
     */

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

    /**
     * Getter all dishes.
     * @param id_menu menu
     * @return list.
     */
    public LinkedList<Dish> getAllDishes(int id_menu){
        LinkedList<Dish> dishes =  new LinkedList<>();
        String query = "SELECT d.* " +
                "FROM menucontainsdish AS mcd\n" +
                "JOIN dish AS d\n" +
                "ON d.id_dish = mcd.id_dish AND mcd.id_menu = "+id_menu+"" +
                " WHERE d.units <> 0;";
        System.out.println(query);
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                Timestamp ts = resultat.getTimestamp("cooking_time");
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(ts.getTime());
                Dish d = new Dish(resultat.getInt(1),resultat.getInt("units"), resultat.getFloat("price"),
                        resultat.getString("name"), cal.getTime());
                 dishes.add(d);
                System.out.println(d.getName()+" "+d.getQuantety()+" "+d.getId());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dishes;
    }

    /**
     * Getter the last dish.
     * @return a dish.
     */
    public Dish getLastDishAdded(){
        Dish d;
        try {
            String query = "SELECT * FROM Dish " +
                    "ORDER BY id_dish DESC LIMIT 1;";
            ResultSet rs = BBDDHelper.getInstance().selectTable(query);
            rs.next();
            d = new Dish(rs.getInt(1),rs.getInt(4),
                    rs.getFloat(2),rs.getString(3),rs.getDate(5));
            return d;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    /**
     * increase the units in dish plus one
     * @param id_menu id of menu
     * @param dishName name of dish
     */
    public void increseDishQuantity(int id_menu, String dishName){
        String query = "update dish " +
                "natural join menucontainsdish " +
                "set units = units +1 " +
                "where id_menu = "+id_menu+" and name like '"+dishName+"';";
        BBDDHelper.getInstance().updateQuery(query);
    }

    /**
     * Update a dish.
     */
    public void dishUpadate(){
        String query = "UPDATE tableorderdish " +
                "SET cur_service = FALSE " +
                "WHERE id_table >= 1 AND id_dish >= 1;";
        BBDDHelper.getInstance().updateQuery(query);
    }
}
