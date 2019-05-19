package Controller.SubController;

import Controller.ServiceController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.User;
import View.SubView.ClientOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientOrderController implements ActionListener {
    private ServiceController listener;
    private ClientOrder clientOrder;

    public ClientOrderController(ServiceController listener) {
        this.listener = listener;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }
    public User getUser(){
        return clientOrder.getUser();
    }
    public void addDish(Dish dish){
        clientOrder.addToOrder(dish);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
