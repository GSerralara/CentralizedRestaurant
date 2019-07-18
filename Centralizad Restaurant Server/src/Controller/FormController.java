package Controller;

import Model.Database.Entity.*;
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
    private WelcomeController welcomeController;
    private RegisterController registerController;
    public FormController(WindowForm view, Model model, Network net) {
        this.view = view;
        this.model = model;
        this.net = net;

        launcherController = new LauncherController(this);
        sideMenuController = new SideMenuController(this);
        serviceStateController = new ServiceStateController(this);
        aunthentificationController = new AunthentificationController(this);
        preServiceController = new PreServiceController(this);
        postServiceController = new PostServiceController(this);
        serviceController = new ServiceController(this);
        welcomeController = new WelcomeController(this);
        registerController = new RegisterController(this);

    }

    public void register(Restaurant restaurant){
        model.callCommand("Register",restaurant);
    }
    public boolean login(Restaurant restaurant){
        model.callCommand("Login",restaurant);
        if(model.getOnService().same(restaurant)){
            initTable();
            initMenus();
        }
        return model.getOnService().same(restaurant);
    }
    public RegisterController getRegisterController() {
        return registerController;
    }

    public WelcomeController getWelcomeController() {
        return welcomeController;
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

    public void addReserve(Reserve u){
        aunthentificationController.addAuth(u);
        model.addReserve(u);
    }
    public void acceptedReserve(Reserve u, int idTable){
        model.addReserve(idTable,u);
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
        model.callCommand("DropReserve",u);
    }
    public void addDish(Dish d){
        model.callCommand("AddDish",d);
    }
    public void loadMenu(String menu){
        model.callCommand("LoadMenu",menu);
        LinkedList<Dish> aux =model.getDishes();
        for(Dish i: aux){
            preServiceController.loadMenuDish(i);
        }
    }
    public LinkedList<Table> getTables(){
        return model.getTables();
    }
    public LinkedList<Reserve> getReserved(){

        return model.getReserves();
        //return aunthentificationController.getAccepted();
    }
    public LinkedList<Dish> getDishes(){
        return model.getDishes();
    }
    public LinkedList<Boolean> dishState(User user){
        LinkedList<Boolean> cook = serviceController.isDishCooked(user);
        return cook;
    }
    public void addClientToService(User u){
        System.out.println("ADDING TO SERVICE");
        System.out.println(u.getUser());
        Reserve r = model.getReserveNamed(u.getUser());
        System.out.println("Gotten reserve data");
        //todo: añadir info a la bbdd
        serviceController.addClient(r);
    }
    public boolean youCanEnter(String name){
        return model.isReservedForNow(name);
    }
    public void initTable(){
        //ToDo:add tables to table management, add limit to auth for not having more users than
        //tables, and lastly change textfield for spinner and a combobox of tables on auth
        preServiceController.init();
        LinkedList<Table> aux = model.getTables();
        for(Table i: aux){
            preServiceController.addExistingTable(i.getNumberClients());
        }
    }
    public void initMenus(){
        LinkedList<Menu> aux = model.getMenus();
        for(Menu i:aux){
            preServiceController.addMenu(i.getName());
        }
    }
    public void createMenu(String name){
        model.callCommand("CreateMenu",name);
    }
    public void logout(){
        model.callCommand("logout",null);
    }
    public void removeTable(int pos){
        model.callCommand("TableDelete",pos);
    }
    public void addTable(int q){
        model.callCommand("TableAdd",q);
    }
    public void addDishToService(User u, Dish d){
        serviceController.addDishToCommand(d,u);
    }
    public void updateReserves(LinkedList<Reserve> reserves){
        model.setReserves(reserves);
    }
}
