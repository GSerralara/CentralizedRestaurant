package Model.Database.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Dish Class.
 */
public class Dish implements Serializable {
    private static final long serialVersionUID = 3L;
    private int id;
    private int quantety;
    private float price;
    private String name;
    //ToDo: MIRA LOS TIPOS DE TIEMPOS QUE YO NI IDEA DE COMO LOS HAS PUESTO EN LA BBDD
    private Date time;
    /**
     * Default Constructor.
     * @param quantety Quantity
     * @param price Price
     * @param name Name
     * @param time Time.
     */

    public Dish(int quantety, float price, String name, Date time) {
        this.quantety = quantety;
        this.price = price;
        this.name = name;
        this.time = time;
    }
    /**
     * Second Constructor
     * @param id Id
     * @param quantety Quantity.
     * @param price Price
     * @param name Name
     * @param time Time.
     */
    public Dish(int id, int quantety, float price, String name, Date time) {
        this.id = id;
        this.quantety = quantety;
        this.price = price;
        this.name = name;
        this.time = time;
    }

    public int getId() {
        return id;
    }
    /**
     * Getter a quantity.
     * @return a integer
     */
    public int getQuantety() {
        return quantety;
    }

    /**
     * Setter a quantity
     * @param quantety Q
     */
    public void setQuantety(int quantety) {
        this.quantety = quantety;
    }
    /**
     * Getter Price
     * @return a float.
     */
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Getter name
     * @return a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name.
     * @param name Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter of time
     * @return time in date format
     */
    public Date getTime() {
        return time;
    }
    /**
     * Setter of time
     */
    public void setTime(Date time) {
        this.time = time;
    }
}

