package Model.Database;

import Model.Database.Entity.Dish;
import Model.Database.Entity.Menu;
import Model.Database.Entity.Restaurant;
import Model.Database.Entity.Table;
import Model.Database.dao.DishDAO;
import Model.Database.dao.MenuDAO;
import Model.Database.dao.RestaurantDAO;
import Model.Database.dao.TableDAO;

import java.util.LinkedList;

public class DAOManager {
    private DishDAO dishDAO;
    private RestaurantDAO restaurantDAO;
    private TableDAO tableDAO;
    private MenuDAO menuDAO;

    public DAOManager() {
        dishDAO = new DishDAO();
        restaurantDAO = new RestaurantDAO();
        tableDAO = new TableDAO();
        menuDAO = new MenuDAO();
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
    public LinkedList<Menu> getMenus(Restaurant owner){
        return menuDAO.getAllRestaurantMenus(owner);
    }
    public void addDish(Dish d){
        dishDAO.addDish(d);
    }
    public Dish getLastDishAdded(){
        return dishDAO.getLastDishAdded();
    }
    public LinkedList<Dish> getMenuDishes(int id_menu){
        return dishDAO.getAllDishes(id_menu);
    }
    public void associateMenuDish(int id_Dish, int id_Menu){
        menuDAO.associateMenu(id_Dish,id_Menu);
    }
    public LinkedList<Table> getTables(String owner){
        return tableDAO.getAllTables(owner);
    }
    public void createMenu(String name, Restaurant owner){
        menuDAO.addMenu(owner,name);
    }
    public void deleteTable(Table t){
        tableDAO.deleteTable(t);
    }

}
