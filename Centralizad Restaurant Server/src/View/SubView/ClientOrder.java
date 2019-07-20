package View.SubView;

import Controller.SubController.ClientOrderController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;
import View.Items.OrderedItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * ClientOrder class
 * extends Jpanel
 * serves to admin Service
 */
public class ClientOrder extends JPanel {
    private Reserve user;
    private JPanel clientOrders;
    private LinkedList<OrderedItem> currentOrder;


    /**
     * This function aims at realizing its respective constructor.
     * @param user it's a parameter that have the user.
     * @param controller it's the respective controller of this view.
     */

    public ClientOrder(Reserve user, ClientOrderController controller) {
        this.user = user;
        controller.setClientOrder(this);
        windowConfiguration();
    }

    /**
     * getter the Reserve that is in the clientOrder
     * @return a specific reserve.
     */
    public Reserve getUser() {
        return user;
    }

    /**
     * This method makes a new JScroll Panel.
     * Method that will create all the components of the panel.
     */
    public void windowConfiguration(){
        // We configure the window.
        setLayout(new BorderLayout());
        // We instance the panel body of the window
        clientOrders = new JPanel();
        clientOrders.setLayout(new BoxLayout(clientOrders,BoxLayout.Y_AXIS));
        // We instance the scroll panel of the window
        JScrollPane orderList = new JScrollPane(clientOrders);
        orderList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//For UX set scrollbar vertical
        // We init the different items of the window.
        init();
        add(orderList,BorderLayout.CENTER);
    }
    /**
     * Function that starts the view.
     */
    public void init(){
        currentOrder = new LinkedList<>();
        clientOrders.removeAll();
        clientOrders.revalidate();
        repaint();
    }

    /**
     * Function that adds orderItem to the view
     * @param dish to add
     * @param controller to know what will happen to the item
     */
    public void addToOrder(Dish dish, ClientOrderController controller){
        this.currentOrder.add(new OrderedItem(dish,controller,currentOrder.size()));
        this.clientOrders.add(currentOrder.get(currentOrder.size()-1));
        clientOrders.revalidate();
        repaint();
    }

    /**
     * Function that starts the dish.
     */
    public void initDish(int pos){
        currentOrder.get(pos).hideButton();
        currentOrder.get(pos).setFlag(true);
    }

    /**
     * delete a item
     * @param nameDish name a dish
     */
    public void deleteItem(String nameDish){
        for(OrderedItem i: currentOrder){
            System.out.println(i.getDish()+" == "+nameDish+" "+i.getDish().equals(nameDish));
            System.out.println(i.isFlag());
            if(i.getDish().equals(nameDish) && !i.isFlag()){
                currentOrder.remove(i);
                clientOrders.removeAll();

            }
        }
        for(OrderedItem i: currentOrder){
            clientOrders.add(i);
        }
        clientOrders.revalidate();
        repaint();
    }
}
