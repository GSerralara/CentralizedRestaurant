package Controller;

import Controller.SubController.ClientOrderController;
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
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void addClient(User client){
        System.out.println(client.getUser());
        this.service.addClient(client);
    }
}
