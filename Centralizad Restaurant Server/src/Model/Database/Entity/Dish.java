package Model.Database.Entity;

import java.io.Serializable;
import java.util.Date;

public class Dish implements Serializable {
    private static final long serialVersionUID = 3L;
    private int id;
   private int quantety;
   private float price;
   private String name;
   private Date time;

    public Dish(int quantety, float price, String name, Date time) {
        this.quantety = quantety;
        this.price = price;
        this.name = name;
        this.time = time;
    }

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

    public int getQuantety() {
        return quantety;
    }

    public void setQuantety(int quantety) {
        this.quantety = quantety;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
