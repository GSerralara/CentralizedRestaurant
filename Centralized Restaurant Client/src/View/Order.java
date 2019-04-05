package View;

import Controller.OrderController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Order extends JPanel {
    private JPanel orderedItems;
    private ArrayList<OrderedItem> currentOrder;
    private OrderController controller;

    public Order(OrderController controller) {
        this.controller = controller;
        this.controller.setOrder(this);
        setLayout(new BorderLayout());
        orderedItems = new JPanel();
        orderedItems.setLayout(new BoxLayout(orderedItems, BoxLayout.Y_AXIS));
        JScrollPane orderList = new JScrollPane(orderedItems);
        orderList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        init();
        add(orderList, BorderLayout.CENTER);
    }
    public void init(){
        currentOrder = new ArrayList<>();
        orderedItems.removeAll();
        orderedItems.revalidate();
        repaint();
    }
    public void addToOrder(String name, String time){
        this.currentOrder.add(new OrderedItem(name,time,controller,currentOrder.size()));
        this.orderedItems.add(currentOrder.get(currentOrder.size()-1));
        orderedItems.revalidate();
        repaint();
    }
    public void cancelItem(String pos){
        int index = Integer.parseInt(pos);
        currentOrder.remove(index);
        orderedItems.remove(index);
        for(int i =0; i< currentOrder.size();i++){
            currentOrder.get(i).updatePos(i);
        }
        orderedItems.revalidate();
        repaint();
    }
}
