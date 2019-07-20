package Controller.SubController;

import Controller.MainTableController;
import View.SubView.Order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * order controller class
 */
public class OrderController implements ActionListener {
    private Order order;
    private MainTableController listener;
    private LinkedList<Boolean> previusState;

    /**
     * default constructor
     * @param listener controller
     */
    public OrderController(MainTableController listener) {
        this.listener = listener;
    }

    /**
     * setter order
     * @param order view
     */
    public void setOrder(Order order) {
        this.order = order;
        previusState = new LinkedList<>();
        this.listener.setOrderController(this);
    }

    /**
     * override function
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = Integer.parseInt(e.getActionCommand());
        System.out.println(index);
        listener.cancelDish(order.getNamedish(index),index);
        order.cancelItem(e.getActionCommand());

    }

    /**
     * add to order
     * @param name name
     * @param time time
     */
    public void addToOrder(String name, String time){
        order.addToOrder(name, time,this);
    }

    /**
     * treat the order state
     * @param isCooking list of bools
     */
    public void treatOrder(LinkedList<Boolean> isCooking){
        if(previusState.size()<isCooking.size()) {
            for (int i = previusState.size(); i < isCooking.size(); i++) {
                previusState.add(false);
            }
        }
        for(int i=0;i<isCooking.size();i++){
            if(isCooking.get(i) && !previusState.get(i)){
                order.updateDishState(i);
                previusState.set(i,true);
            }
        }


    }

}
