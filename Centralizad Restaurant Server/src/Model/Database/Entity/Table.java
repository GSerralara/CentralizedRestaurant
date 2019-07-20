package Model.Database.Entity;

import java.util.LinkedList;

/**
 * Table class.
 */

public class Table {
    private static final long serialVersionUID = 2L;

    private int idTable;
    private String restaurantName;
    private String restaurantMail;
    private int numberClients;
    private LinkedList<Reserve> reserves;

    /**
     * Default Constructor.
     * @param numberClients number of clients.
     * @param id id of table.
     */

    public Table(int numberClients, int id) {
        this.numberClients = numberClients;
        this.idTable = id;
        restaurantName = "";
        restaurantMail = "";
        reserves = new LinkedList<>();
    }

    /**
     * Getters of reserves of the list.
     * @return a list.
     */

    public LinkedList<Reserve> getReserves() {
        return reserves;
    }

    /**
     * Getter of a restaurant name.
     * @return a String
     */

    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Set a restuarant name.
     * @param restaurantName name of restaurant.
     */

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    /**
     * Getter an mail.
     * @return a String.
     */

    public String getRestaurantMail() {
        return restaurantMail;
    }

    /**
     * Setter of mail.
     * @param restaurantMail mail.
     */

    public void setRestaurantMail(String restaurantMail) {
        this.restaurantMail = restaurantMail;
    }

    /**
     * Getter an id table.
     * @return int.
     */
    public int getIdTable() {
        return idTable;
    }


    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    /**
     * Getter a number of client.
     * @return a integer.
     */
    public int getNumberClients() {
        return numberClients;
    }

    public void setNumberClients(int numberClients) {
        this.numberClients = numberClients;
    }

    /**
     * Add a client.
     * @param client reserve.
     */

    public void addClient(Reserve client){
        reserves.add(client);
    }

    /**
     * Remove reserve
     * @param r Reserve.
     */
    public void removeClient(Reserve r){
        reserves.remove(r);
    }

    /**
     * Associate with this table
     * @param r Reserve.
     * @return a boolean.
     */
    public boolean isClientAssocietedWithThisTable(Reserve r){
        for(Reserve i: reserves){
            if(i.getReserveName().equals(r.getReserveName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Tell if the name is First.
     * @param nameReserve name of reserve.
     * @return a name.
     */
    public boolean isFirst(String nameReserve){
        if(reserves.size()==0){
            return false;
        }else {
            if(reserves.get(0).getReserveName().equals(nameReserve)){
                return true;
            }
        }
        return false;
    }
}
