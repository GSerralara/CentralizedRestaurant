package Model;

import Model.Database.BBDDHelper;
import Model.Database.DAOManager;
import Model.Database.Entity.Restaurant;

public class Model {
    private BBDDHelper database;
    private DAOManager manager;
    private String serverState;
    public Model(){
        manager = new DAOManager();
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
        }
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
}
