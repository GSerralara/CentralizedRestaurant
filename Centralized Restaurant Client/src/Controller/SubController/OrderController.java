package Controller.SubController;

import Controller.MainTableController;
import View.SubView.Order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderController implements ActionListener {
    private Order order;
    private MainTableController listener;

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
}
