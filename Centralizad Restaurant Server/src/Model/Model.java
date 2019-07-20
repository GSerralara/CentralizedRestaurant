package Model;

import Model.Database.BBDDHelper;
import Model.Database.DAOManager;
import Model.Database.Entity.*;

import java.util.LinkedList;

/**
 * Model class
 * serves as main model of the app
 */
public class Model {
    private BBDDHelper database;
    private DAOManager manager;
    private String serverState;
    private Restaurant onService;
    private Menu curentMenu;
    private LinkedList<Reserve> reserves;
    private LinkedList<Table> tables;
    private LinkedList<Menu> menus;
    private LinkedList<Dish> dishes;

    /**
     * Default class Constructor
     */
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

    /**
     * setter of the string of server state
     * @param state string of the server state
     */
    public void setServerState(String state){
        this.serverState = state;
        regulateConnection();

    }

    /**
     * function that takes a comannd an based of that treats a object
     * @param command string that takes an order
     * @param obj to treat
     */
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
                Reserve reserve = (Reserve) obj;
                reserves.add(reserve);
                break;
            case "Cancel":
                User cancelation = (User)obj;
                for(Reserve i: reserves){
                    if(cancelation.getUser().equals(i.getUser().getUser())){
                        reserves.remove(i);
                    }
                }
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
            case "DropReserve":
                User user = (User) obj;
                for(Reserve i: reserves){
                    if(i.getUser().getUser().equals(user.getUser())){
                        System.out.println("DROPING RESERVE");
                        removeReserveTable(i);
                        reserves.remove(i);
                    }
                }
            case "Billed":
                User logReserve = (User) obj;
                for(Reserve i: reserves){
                    if(i.getReserveName().equals(logReserve.getUser())){
                        System.out.println("DROPING RESERVE");
                        removeReserveTable(i);
                        reserves.remove(i);
                        System.out.println(numeberOfReserves());
                    }
                }
        }
    }

    /**
     * getter of number of reserves made
     * @return number of reserves
     */
    public int numeberOfReserves(){
        return reserves.size();
    }

    /**
     * getter of list of dishes
     * @return list of dishes
     */
    public LinkedList<Dish> getDishes() {
        if(curentMenu == null){
            return dishes;
        }
        return manager.getMenuDishes(curentMenu.getId());
    }

    /**
     * getter of list of tables
     * @return list of tables
     */
    public LinkedList<Table> getTables() {
        return tables;
    }

    /**
     * getter of list of reserves
     * @return list of reserves
     */
    public LinkedList<Reserve> getReserves() {
        return reserves;
    }

    /**
     * getter of list of Menus
     * @return list of menus
     */
    public LinkedList<Menu> getMenus() {
        return menus;
    }

    /**
     * remove a table reserved
     * @param var Reserve to remove
     */
    public void removeReserveTable(Reserve var){

        for(Table i: tables){
            i.removeClient(var);
        }
    }

    /**
     * getter of boolean that tells if is a reserve now or not
     * @param nameReseve name of reserve
     * @return true in case is a reserve
     */
    public boolean isReservedForNow(String nameReseve){
        for(Table i: tables){
            if(i.isFirst(nameReseve)){
                return true;
            }
        }
        return false;
    }

    /**
     * procedure regulates the connection based on the state
     */
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

    /**
     * adds a reserve to the list
     * @param client to add
     */
    public void addReserve(Reserve client){
        reserves.add(client);
    }

    /**
     * add a reserve into the table
     * @param id of tale
     * @param client to add
     */
    public void addReserve(int id, Reserve client){
        for(Table i:tables){
            if(i.getIdTable() == id){
                i.addClient(client);
                manager.addReserve(client,id);
            }
        }
    }

    /**
     * getter of reserve based on a reserve name
     * @param nameReseve name of reserve
     * @return a reserve
     */
    public Reserve getReserveNamed(String nameReseve) {
        System.out.println(reserves.size());
        for (Reserve i: reserves){
            System.out.println(i.getReserveName());
            if(i.getReserveName().equals(nameReseve)){
                return i;
            }
        }
        return null;
    }

    /**
     * getter reserve from user
     * @param u user
     * @return reserve
     */
    public Reserve getReserveFromUser(User u){
        for(Reserve i: reserves){
            if(i.getUser().getUser().equals(u.getUser())){
                return i;
            }
        }
        return null;
    }

    /**
     * getter of the server state
     * @return a string that represents the server state
     */
    public String getServerState() {
        return serverState;
    }

    /**
     * getter communication Port
     * @return integer of port
     */
    public int getCommuncicationPort(){
        return database.getCommuncicationPort();
    }

    /**
     * getter of the current restaurant
     * @return current restaurant
     */
    public Restaurant getOnService() {
        return onService;
    }

    /**
     * adding dish to bbdd
     * @param client to add to the bbdd
     * @param dish to add to the bbdd
     */
    public void addDishToReserve(User client,Dish dish){
        int id_d = dish.getId();
        int id_t;
        for(Reserve i: reserves){
            if(client.getUser().equals(i.getReserveName())){
                id_t=getReserveTableId(i);
                manager.addDishToReserve(id_d,id_t,1);
            }
        }

    }

    /**
     * getter of the table id from reserve
     * @param r reserve
     * @return table id
     */
    public int getReserveTableId(Reserve r){
        for(Table i: tables){
            if(i.isClientAssocietedWithThisTable(r)){
                return i.getIdTable();
            }
        }
        return 0;
    }

    /**
     * getter of reserve from a reserve name
     * @param reserveName string of reserve name
     * @return reserve asossociated with param
     */
    public Reserve getReserveFromReserveName(String reserveName){
        for(Reserve i: reserves){
            if(i.getReserveName().equals(reserveName)){
                return i;
            }
        }
        return null;
    }

    /**
     * setter of the reserves list
     * @param reserves list of reserves
     */
    public void setReserves(LinkedList<Reserve> reserves) {
        this.reserves = reserves;
    }

    /**
     * get the stats
     * @return the stats
     */
    public LinkedList<Ranking> top5(){
        return manager.getTop5();
    }

    /**
     * get the stats of the current service
     * @return stats of the current service
     */
    public LinkedList<Ranking> actual5(){
        return manager.getTopActual5();
    }

    /**
     * close service
     */
    public void closeService(){
        manager.updateState();
    }
}
