package View.Items;

import Model.Database.Entity.Dish;

import javax.swing.*;
import java.awt.*;

public class BilledItem  extends JPanel {
    private JLabel name;
    public BilledItem(Dish dish) {
        setLayout(new FlowLayout());
        JLabel name = new JLabel(dish.getName());
        JLabel price = new JLabel(dish.getPrice()+"$");
        add(name);
        add(price);
    }
}
