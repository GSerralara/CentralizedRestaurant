package Controller;

import Model.Database.Entity.Dish;
import Model.Database.Entity.User;
import Model.ModelClient;
import Model.Time;
import Network.Network;
import Resources.Pop;
import View.MyForm;

import java.io.IOException;
import java.util.LinkedList;

public class FormController {
    private MyForm view;
    private ModelClient model;
    private Network network;

    private LauncherController launcherController;
    private RegisterController registerController;
    private BookController bookController;
    private MainTableController mainTableController;
    private MainMenuController mainMenuController;
    /**
     * Constructor by default of the class.
     * @param view it's a ProgressListener that will contain the other views
     * @param model it's the respective model of the client app
     * @param network it's the class responsable with the comunnication with the server
     * */
    public FormController(MyForm view, ModelClient model, Network network) {
        this.view = view;
        this.model = model;
        this.network = network;
        this.network.registerController(this);
        //network.connect();

        launcherController = new LauncherController(this);
        registerController = new RegisterController(this);
        mainTableController = new MainTableController(this);
        mainMenuController = new MainMenuController(this);
        bookController = new BookController(this);
    }
    /**
     * Function that will return a controller.
     * It's responsible for returning mainTableController.
     */
    public MainTableController getMainTableController() {
        // return Statement
        return mainTableController;
    }
    /**
     * Function that will return a controller.
     * It's responsible for returning bookController.
     */
    public BookController getBookController() {
        // return Statement
        return bookController;
    }
    /**
     * Function that will return a controller.
     * It's responsible for returning registerController.
     */
    public RegisterController getRegisterController() {
        // return Statement
        return registerController;
    }
    /**
     * Function that will return a controller.
     * It's responsible for returning launcherController.
     */
    public LauncherController getLauncherController() {
        // return Statement
        return launcherController;
    }
    /**
     * Function that will return a controller.
     * It's responsible for returning mainMenuController.
     */
    public MainMenuController getMainMenuController() {
        // return Statement
        return mainMenuController;
    }
    /**
     * Function that will return true if connection with the server was a success.
     * False if not
     * It's responsible for notifying how the connection to the server went.
     */
    public boolean tryConnection(){
        boolean connectionDone = (1 == network.connect()) ? true : false;
        // return Statement
        return connectionDone;
    }

    public void sendObject(Object obj){
        System.out.println(obj.getClass().getName());
        network.sendObject(obj);
        Object answer = network.readObject();
        network.disconnect();
    }

    public void closeSession(){
        network.sendObject("CLOSE");
        Object answer = network.readObject();
        network.disconnect();
        model.close();
        if(answer.equals("null")) {
            Pop pop = new Pop("Closed Session");
        }
    }

    public void sendReserve(String reseveName){
        User u = new User(model.getUser().getUser(),model.getUser().getPassword());
        System.out.println(u.getReserve());
        u.setReserve(reseveName);
        System.out.println(u.getReserve());
        network.sendObject(u);
        Object answer = network.readObject();
    }

    public boolean askForReserveState(String reason){
        network.sendObject(reason);
        Object answer = network.readObject();
        String response = (String) answer;
        Pop pop = new Pop(response);
        if(response.equals("Is still being processed")){
            return true;
        }
        return false;
    }

    public void cancelReserve(){
        bookController.cancelReserve();
        network.sendObject("CANCEL");
        Object answer = network.readObject();
    }

    public LinkedList<Dish> getCurrentMenu(){
        network.sendObject("DISHES");
        Object list = network.readObject();
        return (LinkedList<Dish>)list;
    }

    public void updateMenu(Dish dish){
        network.sendObject(dish);
        Object answer = network.readObject();
    }

    public void login(User user){
        network.sendObject(user);
        Object answer = network.readObject();
        System.out.println((String)answer);
        launcherController.setLogType((String)answer);
        if(answer.equals("Reserve")){
            network.sendObject("DISHES");
            Object list = network.readObject();
            mainTableController.updateMenu((LinkedList<Dish>) list);
        }
    }

    public void billed(){
        network.sendObject("BILLED");
        Object answer = network.readObject();
        System.out.println((String)answer);
    }

    public void register(User user){
        //enviamos el user con los 3 campos
        network.sendObject(user);
        Object answer = network.readObject();
        if(answer == "OK"){
            Pop pop = new Pop("Registered Succesfully");
            network.sendObject("CLOSE");//cerramos la conexion
        }
        network.disconnect();
    }

    public void startSession(User user){
        model.setUser(user);
    }

    public String runTime(String t){
        network.sendObject("time");
        Object ans = network.readObject();
        t =(String) ans;
        return t;
    }

    public LinkedList<Dish> getDishesState(){
        return null;
    }
}
