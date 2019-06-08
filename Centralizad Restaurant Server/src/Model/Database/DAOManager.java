package Model.Database;

import Model.Database.Entity.Dish;
import Model.Database.Entity.Restaurant;
import Model.Database.Entity.Table;
import Model.Database.dao.DishDAO;
import Model.Database.dao.RestaurantDAO;
import Model.Database.dao.TableDAO;

import java.util.LinkedList;

public class DAOManager {
    private DishDAO dishDAO;
    private RestaurantDAO restaurantDAO;
    private TableDAO tableDAO;

    public DAOManager() {
        dishDAO = new DishDAO();
        restaurantDAO = new RestaurantDAO();
        tableDAO = new TableDAO();
    }
    public void registerRestaurant(Restaurant restaurant){
        restaurantDAO.addRestaurant(restaurant);
    }
    public int loginRestaurant(Restaurant restaurant){
        return restaurantDAO.logRestaurant(restaurant);
    }
    public Restaurant getRestaurant(Restaurant restaurant){
        return restaurantDAO.getRestaurant(restaurant);
    }
    public void addTable(Table table){
        tableDAO.addTable(table);
    }

    public LinkedList<Table> getTables(String owner){
        return tableDAO.getAllTables(owner);
    }

    public void deleteTable(Table t){
        tableDAO.deleteTable(t);
    }
}
