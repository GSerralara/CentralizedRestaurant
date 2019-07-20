package View.Items;

import Model.Database.Entity.Dish;

import javax.swing.*;
import java.awt.*;

/**
 * Billed Item class.
 */
public class BilledItem  extends JPanel {
    private JLabel name;

    /**
     * Default Constructor.
     * @param dish Dish.
     */
    public BilledItem(Dish dish) {
        setLayout(new FlowLayout());
        JLabel name = new JLabel(dish.getName());
        JLabel price = new JLabel(dish.getPrice()+"$");
        add(name);
        add(price);
    }
}
