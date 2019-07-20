package Model.Database;

import Model.Database.Entity.*;
import Model.Database.dao.*;

import java.util.LinkedList;

/**
 * DAOManager class
 * manages all data access objects
 */
public class DAOManager {
    private DishDAO dishDAO;
    private RestaurantDAO restaurantDAO;
    private TableDAO tableDAO;
    private MenuDAO menuDAO;
    private ReserveDAO reserveDAO;
    private RankingDAO rankingDAO;

    /**
     * Default constructor of the class
     */
    public DAOManager() {
        dishDAO = new DishDAO();
        restaurantDAO = new RestaurantDAO();
        tableDAO = new TableDAO();
        menuDAO = new MenuDAO();
        reserveDAO = new ReserveDAO();
        rankingDAO = new RankingDAO();
    }

    /**
     * Register a restaurant
     * @param restaurant it's a variable that contains all information about restaurant
     */
    public void registerRestaurant(Restaurant restaurant){
        restaurantDAO.addRestaurant(restaurant);
    }

    /**
     * Function that returns a restaurant
     * @param restaurant it's a parameter that contains all information about restaurant
     * @return  a integer.
     */
    public int loginRestaurant(Restaurant restaurant){
        return restaurantDAO.logRestaurant(restaurant);
    }

    /**
     * Getter a restaurant.
     * @param restaurant it's a parameter that contains all information about restaurant.
     * @return a restaurant.
     */
    public Restaurant getRestaurant(Restaurant restaurant){
        return restaurantDAO.getRestaurant(restaurant);
    }

    /**
     * Add a table in database.
     * @param table Table to add.
     */
    public void addTable(Table table){
        tableDAO.addTable(table);
    }

    /**
     * Getter of a list of menus.
     * @param owner of the menus.
     * @return the list.
     */
    public LinkedList<Menu> getMenus(Restaurant owner){
        return menuDAO.getAllRestaurantMenus(owner);
    }

    /**
     * Add a list to database
     * @param d dish.
     */
    public void addDish(Dish d){
        dishDAO.addDish(d);
    }

    /**
     * Getter the last dish.
     * @return the last dish.
     */
    public Dish getLastDishAdded(){
        return dishDAO.getLastDishAdded();
    }

    /**
     * Getter a list of dish.
     * @param id_menu menu that contains all the dishes.
     * @return the list of dish.
     */
    public LinkedList<Dish> getMenuDishes(int id_menu){
        return dishDAO.getAllDishes(id_menu);
    }

    /**
     * Associate the all dish of menu
     * @param id_Dish dish.
     * @param id_Menu menu.
     */
    public void associateMenuDish(int id_Dish, int id_Menu){
        menuDAO.associateMenu(id_Dish,id_Menu);
    }

    /**
     * Getter of list of the tables.
     * @param owner of dishes.
     * @return the list.
     */
    public LinkedList<Table> getTables(String owner){
        return tableDAO.getAllTables(owner);
    }

    /**
     * Function that create a menu.
     * @param name name of menu.
     * @param owner ownwer of menu.
     */
    public void createMenu(String name, Restaurant owner){
        menuDAO.addMenu(owner,name);
    }

    /**
     * Delete the table.
     * @param t table.
     */
    public void deleteTable(Table t){
        tableDAO.deleteTable(t);
    }

    /**
     * Add a reserve in the program.
     * @param r reserve.
     * @param id id of the table.
     */
    public void addReserve(Reserve r, int id){
        reserveDAO.addBookingTable(r,id);
    }

    /**
     * Add dish in specific reserve.
     * @param id_d id of dish.
     * @param id_t id of table.
     * @param q id of quantity.
     */
    public void addDishToReserve(int id_d,int id_t,int q){
        reserveDAO.addDishToReserve(id_d,id_t,q);
    }

    /**
     * Achieve the 5 dishes.
     * @return a list of dishes.
     */
    public LinkedList<Ranking> getTop5(){
        return rankingDAO.getTop5();
    }

    /**
     * Achieve the 5 dishes in the actuality.
     * @return the list.
     */
    public LinkedList<Ranking> getTopActual5(){
        return rankingDAO.getTopActual5();
    }

    /**
     * Update the state.
     */
    public void updateState(){
        dishDAO.dishUpadate();
    }

}
