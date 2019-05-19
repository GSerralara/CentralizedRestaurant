package Controller;

import Model.Database.Entity.Dish;
import Model.Database.Entity.User;
import Model.Model;
import Network.Network;
import View.WindowForm;

import java.util.LinkedList;
import java.util.Scanner;

public class FormController {
    private WindowForm view;
    private Model model;
    private Network net;
    private LauncherController launcherController;
    private SideMenuController sideMenuController;
    private ServiceStateController serviceStateController;
    private AunthentificationController aunthentificationController;
    private PreServiceController preServiceController;
    private PostServiceController postServiceController;
    private ServiceController serviceController;
    public FormController(WindowForm view, Model model, Network net) {
        this.view = view;
        this.model = model;
        this.net = net;

        launcherController = new LauncherController();
        sideMenuController = new SideMenuController(this);
        serviceStateController = new ServiceStateController(this);
        aunthentificationController = new AunthentificationController(this);
        preServiceController = new PreServiceController(this);
        postServiceController = new PostServiceController(this);
        serviceController = new ServiceController(this);

    }

    public ServiceController getServiceController() {
        return serviceController;
    }

    public PostServiceController getPostServiceController() {
        return postServiceController;
    }

    public PreServiceController getPreServiceController() {
        return preServiceController;
    }

    public AunthentificationController getAunthentificationController() {
        return aunthentificationController;
    }

    public LauncherController getLauncherController() {
        return launcherController;
    }

    public SideMenuController getSideMenuController() {
        return sideMenuController;
    }

    public ServiceStateController getServiceStateController() {
        return serviceStateController;
    }
    public void changeState(String state){
        this.model.setServerState(state);
    }
    public String getState(){
        return this.model.getServerState();
    }

    public void runServer(){
        net.startServer(model.getCommuncicationPort());
    }
    public void shutServer(){
        net.stopService();
    }
    public void pauseServer(){
        net.pause();
    }
    public void resumeServer(){
        net.resume();
    }
    public void addReserve(User u){
        aunthentificationController.addAuth(u);
    }
    public void authResponse(String msg ,User u){
        net.sendMissage(msg, u);
    }
    public void changeService(){
        sideMenuController.changeWindowState();
    }
    public String reserveState(User u){
        return this.aunthentificationController.getIfWasAccepted(u);
    }
    public void reserveCancelation(User u){
        aunthentificationController.dropReserve(u);
    }
    public LinkedList<User> getReserved(){
        return aunthentificationController.getAccepted();
    }
    public LinkedList<Boolean> dishState(User user){
        return serviceController.isDishCooked(user);
    }
    public void addClientToService(User u){
        serviceController.addClient(u);
    }
    public void addDishToService(User u, Dish d){
        serviceController.addDishToCommand(d,u);
    }
}
