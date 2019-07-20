package View.Items;

import Model.Countdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Order Item class.
 */

public class OrderedItem extends JPanel {
    private JLabel status, time;
    private JButton cancelOrder;

    /**
     * Default constructor.
     * @param nameDish Name Dish.
     * @param preparationTime Preparation Time
     * @param listener Listener.
     * @param pos Position.
     */
    public OrderedItem(String nameDish, String preparationTime,  ActionListener listener, int pos) {
        //We create the JPanel.
        super();
        JLabel dish = new JLabel(nameDish);
        this.time = new JLabel(preparationTime);
        this.status = new JLabel("Pedido");
        this.cancelOrder = new JButton("Cancel");
        this.cancelOrder.setActionCommand(""+pos);
        this.cancelOrder.addActionListener(listener);


        JPanel form = new JPanel(new FlowLayout());
        form.add(dish,FlowLayout.LEFT);
        form.add(time,FlowLayout.CENTER);
        form.add(status,FlowLayout.RIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(form);
        this.add(cancelOrder);
    }

    /**
     * Update Pos
     * @param pos Integer.
     */
    public void updatePos(int pos){
        this.cancelOrder.setActionCommand(""+pos);
    }

    /**
     * Hide Button
     */
    private void hideButton(){
        cancelOrder.setVisible(false);
    }

    /**
     * Update Status
     * @param dishState String
     */
    public void updateStatus(String dishState){
        status.setText(dishState);
        if(!dishState.equals("Pedido"))  hideButton();
    }

    /**
     * Countdown
     */
    public void initCountDown(){

        try {
            Date date;
            date = new SimpleDateFormat("mm:ss").parse(time.getText());
            Countdown countdown = new Countdown(time,date,status);
            new Thread(countdown).start();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
