package Controller.SubController;

import Controller.ServiceController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;
import View.SubView.ClientOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
/**
 * Client Order controller
 */
public class ClientOrderController implements ActionListener {
    private ServiceController listener;
    private ClientOrder clientOrder;
    private LinkedList<Boolean> cooking;
    private int dishes;

    /**
     * Default contructor
     * @param listener controller
     */
    public ClientOrderController(ServiceController listener) {
        this.listener = listener;
        cooking = new LinkedList<>();
        dishes=0;
    }

    /**
     * view setter
     * @param clientOrder view
     */
    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    /**
     * user getter
     * @return reserve
     */
    public Reserve getUser(){
        return clientOrder.getUser();
    }

    /**
     * reserve name getter
     * @return string
     */
    public String getReserveName(){
        return clientOrder.getUser().getReserveName();
    }

    /**
     * Dish add
     * @param dish dish
     */
    public void addDish(Dish dish){
        dishes++;
        cooking.add(false);
        clientOrder.addToOrder(dish,this);
    }

    /**
     * dish states
     * @return list of boolean
     */
    public LinkedList<Boolean> dishesState(){
        System.out.println("number of dishes"+dishes);
        return clientOrder.dishesState();
    }

    /**
     * deletes a dish
     * @param name name
     * @param num index in array
     */
    public void removeDish(String name, int num){
        dishes--;
        clientOrder.deleteItem(name,num);
        cooking.remove(num);
    }

    /**
     * override function
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        clientOrder.initDish(Integer.parseInt(e.getActionCommand()));
        cooking.set(Integer.parseInt(e.getActionCommand()),true);
    }

}
