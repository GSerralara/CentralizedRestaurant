package View.Items;

import Model.Countdown;
import Model.Database.Entity.Dish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class OrderedItem extends JPanel {
    private Dish dish;
    private JButton startCooking;
    private boolean flag;
    private JLabel time;

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

    public void hideButton(){
        startCooking.setEnabled(false);
        Countdown countdown = new Countdown(time,dish.getTime());
        new Thread(countdown).start();
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setTime(String time) {
        this.time.setText(time);
    }


}
