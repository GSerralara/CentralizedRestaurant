package View.Items;

import Model.Countdown;
import Model.Database.Entity.Dish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * OrderedItem class
 * extends JPanel
 * serves as minimum unit for orders
 */
public class OrderedItem extends JPanel {
    private Dish dish;
    private JButton startCooking;
    private boolean flag;
    private JLabel time;

    /**
     * This is the function that generate the constructor.
     * @param dish it's parameter that have all the information about dish.
     * @param listener it's a ProgressListener that the class will use to move to other views.
     * @param pos it's a integer that save the position.
     */
    public OrderedItem(Dish dish, ActionListener listener, int pos) {
        this.dish = dish;
        flag = false;
        startCooking = new JButton("StartCooking");
        startCooking.setActionCommand(""+pos);
        startCooking.addActionListener(listener);
        new FlowLayout();
        JLabel name = new JLabel(dish.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        this.time = new JLabel(dateFormat.format(dish.getTime()));
        add(name);
        add(time);
        add(startCooking);
    }

    /**
     * The function is stash the buttom.
     */
    public void hideButton(){
        startCooking.setEnabled(false);
        Countdown countdown = new Countdown(time,dish.getTime());
        new Thread(countdown).start();
    }
    /**
    * getter of the flag Boolean
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * setter of the flag boolean
     * @param flag it's a parameter that have a boolean.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    /**
     * setter a string to the timeLabel
     */
    public void setTime(String time) {
        this.time.setText(time);
    }

    /**
     * getter dish name
     * @return name
     */
    public String getDish(){
        return dish.getName();
    }
}
