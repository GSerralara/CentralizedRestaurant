package Controller.SubController;

import Controller.ServiceController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;
import View.SubView.ClientOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ClientOrderController implements ActionListener {
    private ServiceController listener;
    private ClientOrder clientOrder;
    private LinkedList<Boolean> cooking;

    public ClientOrderController(ServiceController listener) {
        this.listener = listener;
        cooking = new LinkedList<>();
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }
    public Reserve getUser(){
        return clientOrder.getUser();
    }
    public String getReserveName(){
        return clientOrder.getUser().getReserveName();
    }
    public void addDish(Dish dish){
        cooking.add(false);
        clientOrder.addToOrder(dish);
    }
    public LinkedList<Boolean> dishesState(){
        return cooking;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        clientOrder.initDish(Integer.parseInt(e.getActionCommand()));
        cooking.set(Integer.parseInt(e.getActionCommand()),true);
    }

}
