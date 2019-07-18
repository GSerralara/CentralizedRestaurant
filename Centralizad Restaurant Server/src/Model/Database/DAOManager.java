package Model.Database;

import Model.Database.Entity.*;
import Model.Database.dao.*;

import java.util.LinkedList;

public class DAOManager {
    private DishDAO dishDAO;
    private RestaurantDAO restaurantDAO;
    private TableDAO tableDAO;
    private MenuDAO menuDAO;
    private ReserveDAO reserveDAO;
    private RankingDAO rankingDAO;

    public DAOManager() {
        dishDAO = new DishDAO();
        restaurantDAO = new RestaurantDAO();
        tableDAO = new TableDAO();
        menuDAO = new MenuDAO();
        reserveDAO = new ReserveDAO();
        rankingDAO = new RankingDAO();
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
    public void addReserve(Reserve r, int id){
        reserveDAO.addBookingTable(r,id);
    }
    public void addDishToReserve(int id_d,int id_t,int q){
        reserveDAO.addDishToReserve(id_d,id_t,q);
    }
    public LinkedList<Ranking> getTop5(){
        return rankingDAO.getTop5();
    }
    public LinkedList<Ranking> getTopActual5(){
        return rankingDAO.getTopActual5();
    }
    public void updateState(){
        dishDAO.dishUpadate();
    }

}
