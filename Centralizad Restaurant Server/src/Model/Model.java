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

    public Model(){
        manager = new DAOManager();
        reserves = new LinkedList<>();
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
                    System.out.println(onService.getUser()+" "+onService.getMail());
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
                break;
        }
    }

    public LinkedList<User> getReserves() {
        return reserves;
    }

    private void regulateConnection(){
        switch (this.serverState){
            case "INIT":
                break;
            case "END":
                this.database.disconnectBBDD();
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
