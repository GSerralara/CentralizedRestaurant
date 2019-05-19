package Controller;

import Controller.SubController.ClientOrderController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.User;
import View.Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ServiceController implements ActionListener {
    private FormController listener;
    private Service service;
    private LinkedList<ClientOrderController> clientsController;

    public ServiceController(FormController listener) {
        this.listener = listener;
        clientsController = new LinkedList<>();
    }

    public void setService(Service service) {
        this.service = service;
    }
    public ClientOrderController getNewClientController(){
        clientsController.add(new ClientOrderController(this));
        return clientsController.get(clientsController.size());
    }
    public void addNewController(ClientOrderController obj){
        clientsController.add(obj);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public LinkedList<Boolean> isDishCooked(User user){
        for (ClientOrderController i: clientsController){
            if(user.getUser().equals(i.getUser().getUser())){
               return i.dishesState();
            }
        }
        return null;
    }
    public void addClient(User client){
        System.out.println(client.getUser());
        this.service.addClient(client);
    }

    public void addDishToCommand(Dish d, User u){
        System.out.println("ADDING DISH");
        for(ClientOrderController i: clientsController){
            System.out.println(u.getUser()+ " " + i.getUser().getUser());
            if(u.getUser().equals(i.getUser().getUser())){
                i.addDish(d);
            }
        }
        service.redraw();
    }
}
