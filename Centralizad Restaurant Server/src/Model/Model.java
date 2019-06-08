package Model;

import Model.Database.BBDDHelper;
import Model.Database.DAOManager;
import Model.Database.Entity.Restaurant;
import Model.Database.Entity.Table;
import Model.Database.Entity.User;

import java.util.LinkedList;

public class Model {
    private BBDDHelper database;
    private DAOManager manager;
    private String serverState;
    private Restaurant onService;
    private LinkedList<User> reserves;
    private LinkedList<Table> tables;
    public Model(){
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
                onService.setMail("");
                onService.setUsername("");
                onService.setPassword("");
                break;
        }
    }

    public LinkedList<Table> getTables() {
        return tables;
    }

    public LinkedList<User> getReserves() {
        return reserves;
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
