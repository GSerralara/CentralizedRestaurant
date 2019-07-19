package View.SubView;

import Controller.SubController.ClientOrderController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;
import View.Items.OrderedItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientOrder extends JPanel {
    private Reserve user;
    private JPanel clientOrders;
    private ArrayList<OrderedItem> currentOrder;


    public ClientOrder(Reserve user, ClientOrderController controller) {
        this.user = user;
        controller.setClientOrder(this);
        windowConfiguration();
    }

    public Reserve getUser() {
        return user;
    }

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
    public void init(){
        currentOrder = new ArrayList<>();
        clientOrders.removeAll();
        clientOrders.revalidate();
        repaint();
    }
    public void addToOrder(Dish dish, ClientOrderController controller){
        this.currentOrder.add(new OrderedItem(dish,controller,currentOrder.size()));
        this.clientOrders.add(currentOrder.get(currentOrder.size()-1));
        clientOrders.revalidate();
        repaint();
    }
    public void initDish(int pos){
        currentOrder.get(pos).hideButton();
        currentOrder.get(pos).setFlag(true);
    }
}
