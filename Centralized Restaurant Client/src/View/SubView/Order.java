package View.SubView;

import Controller.SubController.OrderController;
import View.Items.OrderedItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Order extends JPanel {
    private JPanel orderedItems;
    private ArrayList<OrderedItem> currentOrder;
    /**
     * Constructor by default of the class.
     * @param controller it's the respective controller of this view
     * */
    public Order(OrderController controller) {
        // instance attributes with passed parameters

        controller.setOrder(this);
        // UI configuration of the panel
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We configure the window.
        setLayout(new BorderLayout());
        // We instance the panel body of the window
        orderedItems = new JPanel();
        orderedItems.setLayout(new BoxLayout(orderedItems, BoxLayout.Y_AXIS));//For UX positioning will have a FlowLayout
        // We instance the scroll panel of the window
        JScrollPane orderList = new JScrollPane(orderedItems);
        orderList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//For UX set scrollbar vertical
        // We init the different items of the window.
        init();
        // We add the content to the window.
        add(orderList, BorderLayout.CENTER);
    }
    /**
     * Method that will init the list of the panel.
     */
    private void init(){
        currentOrder = new ArrayList<>();
        orderedItems.removeAll();
        orderedItems.revalidate();
        repaint();
    }
    /**
     * Method that will add a new item to the panel list.
     */
    public void addToOrder(String name, String time, OrderController controller){
        this.currentOrder.add(new OrderedItem(name,time,controller,currentOrder.size()));
        this.orderedItems.add(currentOrder.get(currentOrder.size()-1));
        orderedItems.revalidate();
        repaint();
    }
    /**
     * Method that will reconfigure all the items of the panel list.
     */
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

    public void updateDishState(int index){
        currentOrder.get(index).updateStatus("Cocinando");
        currentOrder.get(index).initCountDown();
    }
}
