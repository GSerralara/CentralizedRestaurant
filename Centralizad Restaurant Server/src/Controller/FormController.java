package Controller;

import Model.Database.Entity.*;
import Model.Model;
import Network.Network;
import View.WindowForm;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * FormController class
 */
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

    /**
     * Constructor by default of the class.
     * @param view it's a WindowForm that will contain the other views
     * @param model it's the respective model of the client app
     * @param net it's the class responsable with the comunnication with the server
     */
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

    /**
     * Procedure that calls the model to register a Restaurant form
     * @param restaurant that stores the data to register
     */
    public void register(Restaurant restaurant){
        model.callCommand("Register",restaurant);
    }

    /**
     * Procedure that calls the model to review a Restaurant form
     * @param restaurant that stores the data of the client
     * @return true in case that it exists, false in case it doesn't exists yet
     */
    public boolean login(Restaurant restaurant){
        model.callCommand("Login",restaurant);
        if(model.getOnService().same(restaurant)){
            initTable();
            initMenus();
        }
        return model.getOnService().same(restaurant);
    }

    /**
     * getter RegisterController
     * @return RegisterController
     */
    public RegisterController getRegisterController() {
        return registerController;
    }

    /**
     * getter WelcomeController
     * @return WelcomeController
     */
    public WelcomeController getWelcomeController() {
        return welcomeController;
    }

    /**
     * getter ServiceController
     * @return ServiceController
     */
    public ServiceController getServiceController() {
        return serviceController;
    }

    /**
     * getter PostServiceController
     * @return PostServiceController
     */
    public PostServiceController getPostServiceController() {
        return postServiceController;
    }

    /**
     * getter PreServiceController
     * @return PreServiceController
     */
    public PreServiceController getPreServiceController() {
        return preServiceController;
    }

    /**
     * getter AuthentificationController
     * @return AuthentificationController
     */
    public AunthentificationController getAunthentificationController() {
        return aunthentificationController;
    }

    /**
     * getter LauncherController
     * @return LauncherController
     */
    public LauncherController getLauncherController() {
        return launcherController;
    }

    /**
     * getter SideMenuController
     * @return SideMenuController
     */
    public SideMenuController getSideMenuController() {
        return sideMenuController;
    }

    /**
     * getter ServiceStateController
     * @return ServiceStateController
     */
    public ServiceStateController getServiceStateController() {
        return serviceStateController;
    }

    /**
     * changes the state to service to preservice, and vice-verse
     * @param state name of the current state at init
     */
    public void changeState(String state){
        this.model.setServerState(state);
    }

    /**
     * getter of the state of the service when its in init
     * @return string of characters of the state
     */
    public String getState(){
        return this.model.getServerState();
    }

    /**
     * procedure that inits the network server
     */
    public void runServer(){
        net.startServer(model.getCommuncicationPort());
    }

    /**
     *  procedure that shuts the network connection
     */
    public void shutServer(){
        net.stopService();
        model.closeService();
    }

    /**
     * procedure that stops the dataflow in the server connection
     */
    public void pauseServer(){
        net.pause();
        postServiceController.addPanel("Service",model.actual5());
        postServiceController.addPanel("Inauguration",model.top5());
    }

    /**
     * resume server state
     */
    public void resumeServer(){
        net.resume();
    }

    /**
     * add a reserve
     * @param u reserve
     */
    public void addReserve(Reserve u){
        aunthentificationController.addAuth(u);
        model.addReserve(u);
    }

    /**
     * add a reserve to bbdd
     * @param u reserve
     * @param idTable id table
     */
    public void acceptedReserve(Reserve u, int idTable){
        model.addReserve(idTable,u);
    }
    public void authResponse(String msg ,User u){
        net.sendMissage(msg, u);
    }

    /**
     * change service state
     */
    public void changeService(){
        sideMenuController.changeWindowState();
    }

    /**
     * reserve state
     * @param u user
     * @return string
     */
    public String reserveState(User u){
        return this.aunthentificationController.getIfWasAccepted(u);
    }

    /**
     * cancel reserve
     * @param u user
     */
    public void reserveCancelation(User u){
        System.out.println(u == null);
        Reserve r = model.getReserveFromReserveName(u.getUser());
        if(r == null){
            r = model.getReserveFromUser(u);
            System.out.println("DROP WITHOUT RESERVE");
            aunthentificationController.dropReserve(r.getUser());
            model.callCommand("DropReserve",u);
            if(model.numeberOfReserves()==0){
                changeService();
            }
        }else{
            System.out.println("DROP RESERVE ssass");
            if(aunthentificationController.getIfWasAccepted(r.getUser()).equals("YES")){
                System.out.println("YES");
                aunthentificationController.dropReserve(r.getUser());
                if(u.getUser().equals(r.getReserveName())){
                    model.callCommand("Billed",u);
                }else{
                    model.callCommand("DropReserve",u);
                }

                if(model.numeberOfReserves()==0){
                    changeService();
                }
            }else{
                System.out.println("NO");
                aunthentificationController.dropReserve(r.getUser());
            }
        }

    }

    /**
     * add dish
     * @param d dish
     */
    public void addDish(Dish d){
        model.callCommand("AddDish",d);
    }

    /**
     * load a menu
     * @param menu string
     */
    public void loadMenu(String menu){
        model.callCommand("LoadMenu",menu);
        LinkedList<Dish> aux =model.getDishes();
        for(Dish i: aux){
            preServiceController.loadMenuDish(i);
        }
    }

    /**
     * getter tables
     * @return list of tables
     */
    public LinkedList<Table> getTables(){
        return model.getTables();
    }

    /**
     * get reserves
     * @return list of reserves
     */
    public LinkedList<Reserve> getReserved(){
        return model.getReserves();
    }

    /**
     * getter of dishes
     * @return list of dishes
     */
    public LinkedList<Dish> getDishes(){
        return model.getDishes();
    }

    /**
     * getter og dish state
     * @param user user
     * @return list of boolean
     */
    public LinkedList<Boolean> dishState(User user){
        LinkedList<Boolean> cook = serviceController.isDishCooked(user);
        return cook;
    }

    /**
     * Add client to service
     * @param u User
     */
    public void addClientToService(User u){
        System.out.println("ADDING TO SERVICE");
        System.out.println(u.getUser());
        Reserve r = model.getReserveNamed(u.getUser());
        System.out.println("Gotten reserve data");
        serviceController.addClient(r);
    }

    /**
     * You can enter
     * @param name Name
     * @return a boolean
     */
    public boolean youCanEnter(String name){
        return model.isReservedForNow(name);
    }

    /**
     * Init Tables
     */
    public void initTable(){
        //ToDo:add tables to table management, add limit to auth for not having more users than
        //tables, and lastly change textfield for spinner and a combobox of tables on auth
        preServiceController.init();
        LinkedList<Table> aux = model.getTables();
        for(Table i: aux){
            preServiceController.addExistingTable(i.getNumberClients());
        }
    }

    /**
     * Init menu
     */
    public void initMenus(){
        LinkedList<Menu> aux = model.getMenus();
        for(Menu i:aux){
            preServiceController.addMenu(i.getName());
        }
    }

    /**
     * Create the menu
     * @param name Name
     */
    public void createMenu(String name){
        model.callCommand("CreateMenu",name);
    }

    /**
     * LogOut
     */
    public void logout(){
        model.closeService();
        model.callCommand("logout",null);
    }

    /**
     * Remove table
     * @param pos Position
     */
    public void removeTable(int pos){
        model.callCommand("TableDelete",pos);
    }

    /**
     * Add Table
     * @param q integer
     */
    public void addTable(int q){
        model.callCommand("TableAdd",q);
    }

    /**
     * Add Dish
     * @param u User
     * @param d Dish
     */
    public void addDishToService(User u, Dish d){
        serviceController.addDishToCommand(d,u);
        model.addDishToReserve(u,d);
    }

    /**
     * removes a dish from order
     * @param u user
     * @param name dish name
     * @param num index array
     */
    public void removeDishOrder(User u, String name, int num){
        serviceController.deleteDish(u,name,num);
        model.callCommand("restock",name);
    }

}
