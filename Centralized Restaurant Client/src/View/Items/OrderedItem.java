package View.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderedItem extends JPanel {
    private JLabel dish,status, time;
    private JButton cancelOrder;
    public OrderedItem(String nameDish, String preparationTime,  ActionListener listener, int pos) {
        //We create the JPanel.
        super();
        this.dish = new JLabel(nameDish);
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
    public void updatePos(int pos){
        this.cancelOrder.setActionCommand(""+pos);
    }
    public void hideButton(){
        cancelOrder.setVisible(false);
    }
    public void updateTime(){
        //pensar como acceder para cambiar el tiempo
        //maybe a thread
    }
    public void updateStatus(String dishState){
        status.setText(dishState);
        if(!dishState.equals("Pedido"))  hideButton();
    }
}