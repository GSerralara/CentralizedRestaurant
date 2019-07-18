package Model.Database.Entity;

import java.util.LinkedList;

public class Table {
    private static final long serialVersionUID = 2L;

    private int idTable;
    private String restaurantName;
    private String restaurantMail;
    private int numberClients;
    private LinkedList<Reserve> reserves;

    public Table(int numberClients, int id) {
        this.numberClients = numberClients;
        this.idTable = id;
        restaurantName = "";
        restaurantMail = "";
        reserves = new LinkedList<>();
    }

    public LinkedList<Reserve> getReserves() {
        return reserves;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantMail() {
        return restaurantMail;
    }

    public void setRestaurantMail(String restaurantMail) {
        this.restaurantMail = restaurantMail;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getNumberClients() {
        return numberClients;
    }

    public void setNumberClients(int numberClients) {
        this.numberClients = numberClients;
    }

    public void addClient(Reserve client){
        reserves.add(client);
    }

    public void removeClient(Reserve r){
        reserves.remove(r);
    }
    public boolean isClientAssocietedWithThisTable(Reserve r){
        for(Reserve i: reserves){
            if(r.getReserveName().equals(r.getReserveName())){
                return true;
            }
        }
        return false;
    }
}
