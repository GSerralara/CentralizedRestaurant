package Model;

import Model.Database.BBDDHelper;
import Model.Database.DAOManager;
import Model.Database.Entity.*;

import java.util.LinkedList;

public class Model {
    private BBDDHelper database;
    private DAOManager manager;
    private String serverState;
    private Restaurant onService;
    private Menu curentMenu;
    private LinkedList<User> reserves;
    private LinkedList<Table> tables;
    private LinkedList<Menu> menus;
    private LinkedList<Dish> dishes;
    public Model(){
        curentMenu = null;
        dishes =  new LinkedList<>();
        menus =  new LinkedList<>();
        manager = new DAOManager();
        reserves = new LinkedList<>();
        tables = new LinkedList<>();
        onService = new Restaurant("","","");
        this.database = new BBDDHelper();
        if(database.isConnected()){
            this.serverState = "END";
        }else {
            this.serverState = "OUT";
        }
    }
    public void setServerState(String state){
        this.serverState = state;
        regulateConnection();

    }
    public void callCommand(String command,Object obj){
        switch (command){
            case "Register":
                Restaurant res = (Restaurant)obj;
                manager.registerRestaurant(res);
                break;
            case "Login":
                Restaurant reslog = (Restaurant)obj;
                if(manager.loginRestaurant(reslog)==1){
                    this.onService = manager.getRestaurant(reslog);
                    this.tables = manager.getTables(onService.getUser());
                    this.menus = manager.getMenus(onService);
                }
                break;
            case "Reserve":
                User reserve = (User)obj;
                reserves.add(reserve);
                break;
            case "Cancel":
                User cancelation = (User)obj;
                reserves.remove(cancelation);
                break;
            case "TableAdd":
                int q = (int)obj;
                Table t = new Table(q,0);
                t.setRestaurantMail(this.onService.getMail());
                t.setRestaurantName(this.onService.getUser());
                manager.addTable(t);
                this.tables = manager.getTables(onService.getUser());
                break;
            case "TableDelete":
                int pos = (int)obj;
                manager.deleteTable(tables.get(pos));
                this.tables = manager.getTables(onService.getUser());
                break;
            case "logout":
                reserves.clear();
                //ToDo:drop all users and close their sessions
                tables.clear();
                menus.clear();
                dishes.clear();
                onService.setMail("");
                onService.setUsername("");
                onService.setPassword("");
                break;
            case "CreateMenu":
                String menuName = (String)obj;
                manager.createMenu(menuName,onService);
                menus = manager.getMenus(onService);
                curentMenu = menus.get(menus.size()-1);
                break;
            case "AddDish":
                Dish d = (Dish)obj;
                manager.addDish(d);
                dishes.add(manager.getLastDishAdded());
                manager.associateMenuDish(dishes.get(dishes.size()-1).getId(),curentMenu.getId());
                break;
            case "LoadMenu":
                String menuToLoad =(String)obj;
                for(Menu i:menus){
                    if(i.getName().equals(menuToLoad)){
                        curentMenu = i;
                        dishes = manager.getMenuDishes(curentMenu.getId());
                    }
                }
                break;
        }
    }

    public LinkedList<Dish> getDishes() {
        return dishes;
    }

    public LinkedList<Table> getTables() {
        return tables;
    }

    public LinkedList<User> getReserves() {
        return reserves;
    }

    public LinkedList<Menu> getMenus() {
        return menus;
    }

    private void regulateConnection(){
        switch (this.serverState){
            case "INIT":
                break;
            case "END":
                //todo: cuando te conectas enciendes la network y cuando cierras la terminas
                //this.database.disconnectBBDD();
                break;
        }
    }

    public String getServerState() {
        return serverState;
    }
    public int getCommuncicationPort(){
        return database.getCommuncicationPort();
    }

    public Restaurant getOnService() {
        return onService;
    }
}
