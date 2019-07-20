package Model.Database.Entity;

/**
 * DishTable class.
 */

public class DishTable {
    private int quantity;
    private String name;

    /***
     * DEfault Constructor.
     * @param quantity Quantity
     * @param name Name.
     */
    public DishTable(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter of name.
     * @return a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter a name
     * @param name Name.
     */

    public void setName(String name) {
        this.name = name;
    }
}
