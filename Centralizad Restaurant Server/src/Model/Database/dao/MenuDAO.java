package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Menu;
import Model.Database.Entity.Restaurant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MenuDAO {
    public MenuDAO() {
    }
    public void addMenu(Restaurant owner, String name){
        String query1 = "INSERT INTO Menu (name,usernameRestaurant,emailRestaurant) " +
                "VALUES " +
                "('"+name+"','"+owner.getUser()+"','"+owner.getMail()+"');";
        BBDDHelper.getInstance().insertData(query1);
    }
    public void associateMenu(int idDish, int idMenu){
        String query2 = "INSERT INTO MenuContainsDish (id_menu,id_dish) " +
                "VALUES " +
                "("+idMenu+","+idDish+");";
        BBDDHelper.getInstance().insertData(query2);
    }
    public LinkedList<Menu> getAllRestaurantMenus(Restaurant owner){
        String query = "SELECT * FROM Menu " +
                "WHERE usernameRestaurant LIKE '"+owner.getUser()+"';";
        ResultSet rs = BBDDHelper.getInstance().selectTable(query);
        LinkedList<Menu> menus = new LinkedList<>();
        try {
            while (rs.next()){
                menus.add(new Menu(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }
}
