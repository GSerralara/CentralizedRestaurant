package Model.Database;

import Model.Database.Entity.Dish;
import Model.Database.Entity.Restaurant;
import Model.Database.dao.DishDAO;
import Model.Database.dao.RestaurantDAO;

public class DAOManager {
    private DishDAO dishDAO;
    private RestaurantDAO restaurantDAO;

    public DAOManager() {
        dishDAO = new DishDAO();
        restaurantDAO = new RestaurantDAO();
    }
    public void registerRestaurant(Restaurant restaurant){
        restaurantDAO.addRestaurant(restaurant);
    }
    public int loginRestaurant(Restaurant restaurant){
        return restaurantDAO.logRestaurant(restaurant);
    }
}
