package Model.DataBase.Entity;

import java.io.Serializable;
import java.util.Date;

public class Dish implements Serializable {
   private int quantety;
   private float price;
   private String name;
   //ToDo: MIRA LOS TIPOS DE TIEMPOS QUE YO NI IDEA DE COMO LOS HAS PUESTO EN LA BBDD
   private Date time;

    public Dish(int quantety, float price, String name, Date time) {
        this.quantety = quantety;
        this.price = price;
        this.name = name;
        this.time = time;
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
