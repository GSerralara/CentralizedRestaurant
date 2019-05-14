package View.Items;

import Model.DataBase.Entity.Dish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderedItem extends JPanel {
    private Dish dish;
    private JButton startCooking;

    public OrderedItem(Dish dish, ActionListener listener, int pos) {
        this.dish = dish;
        startCooking = new JButton("StartCooking");
        startCooking.setActionCommand(""+pos);
        new FlowLayout();
        JLabel name = new JLabel(dish.getName());
        JLabel time = new JLabel(dish.getTime().toString());
        add(name);
        add(time);
        add(startCooking);
    }
}
