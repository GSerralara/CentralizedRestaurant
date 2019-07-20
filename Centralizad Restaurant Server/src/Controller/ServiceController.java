package Controller;

import Controller.SubController.ClientOrderController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;
import View.Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * ServiceController
 */
public class ServiceController implements ActionListener {
    private FormController listener;
    private Service service;
    private LinkedList<ClientOrderController> clientsController;

    /**
     * defacult class constructor
     * @param listener
     */
    public ServiceController(FormController listener) {
        this.listener = listener;
        clientsController = new LinkedList<>();
    }

    /**
     * setter of service view
     * @param service
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * getter of the new controller
     * @return client controller
     */
    public ClientOrderController getNewClientController(){
        clientsController.add(new ClientOrderController(this));
        return clientsController.get(clientsController.size());
    }

    /**
     * add a newController to the list
     * @param obj client controller
     */
    public void addNewController(ClientOrderController obj){
        clientsController.add(obj);
    }

    /**
     * override
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * get a list of the dishes state
     * @param user owner
     * @return list
     */
    public LinkedList<Boolean> isDishCooked(User user){
        for (ClientOrderController i: clientsController){
            if(user.getUser().equals(i.getUser().getReserveName())){
               return i.dishesState();
            }
        }
        return null;
    }

    /**
     * add a client
     * @param client client
     */
    public void addClient(Reserve client){

        this.service.addClient(client, this);
    }

    /**
     * add a dish to the command pane
     * @param d dish
     * @param u user
     */
    public void addDishToCommand(Dish d, User u){
        for(ClientOrderController i: clientsController){
            if(u.getUser().equals(i.getUser().getReserveName())){
                i.addDish(d);
            }
        }
        service.redraw();
    }

    /**
     * deletes a dish of the service
     * @param client client
     * @param dishname name
     */
    public void deleteDish(User client, String dishname){
        for(ClientOrderController i: clientsController){
            if(client.getUser().equals(i.getUser().getReserveName())){
                i.removeDish(dishname);
            }
        }
        service.redraw();

    }
}
