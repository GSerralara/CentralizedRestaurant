package Controller.SubController;

import Controller.MainTableController;
import View.SubView.Order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class OrderController implements ActionListener {
    private Order order;
    private MainTableController listener;
    private boolean state;
    public OrderController(MainTableController listener) {
        this.listener = listener;
    }

    public void setOrder(Order order) {
        this.order = order;
        this.listener.setOrderController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        order.cancelItem(e.getActionCommand());

    }
    public void addToOrder(String name, String time){
        order.addToOrder(name, time);
    }
    public void treatOrder(LinkedList<Boolean> isCooking){
        System.out.println(""+isCooking.size());
        for(int i=0;i<isCooking.size();i++){
            System.out.println(isCooking.get(i));
            if(isCooking.get(i)){
                order.updateDishState(i);
            }
        }
    }

}
