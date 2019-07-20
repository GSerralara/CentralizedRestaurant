package Controller;

import Model.Database.Entity.Dish;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;
import Model.ModelClient;
import Network.Network;
import Resources.Pop;
import View.MyForm;
import java.util.LinkedList;

/**
 * FormController class
 */
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
    boolean tryConnection(){
        boolean connectionDone = (1 == network.connect()) ? true : false;
        // return Statement
        return connectionDone;
    }


    /**
     * Getter the User
     * @return User.
     */
    User getuser(){
        return model.getUser();
    }

    /**
     * Close the Session
     */
    void closeSession(){
        network.sendObject("CLOSE");
        Object answer = network.readObject();
        network.disconnect();
        model.close();
        if(answer.equals("null")) {
            Pop pop = new Pop("Closed Session");
        }
    }

    /**
     * Send the Reserve
     * @param reserve Reserve
     */
    void sendReserve(Reserve reserve){
        System.out.println("Enviando reserva");
        network.sendObject(reserve);
        System.out.println("Reserva enviada");
        Object answer = network.readObject();
        System.out.println(answer);
    }

    /**
     * Function that ask for reserve State
     * @param reason String
     * @return a String.
     */
    boolean askForReserveState(String reason){
        network.sendObject(reason);
        Object answer = network.readObject();
        String response = (String) answer;
        Pop pop = new Pop(response);
        if(response.equals("Is still being processed")){
            return true;
        }
        return false;
    }
    public void cancelDish(String name){
        network.sendObject("CL"+name);
        Object answer = network.readObject();
    }
    /**
     * Cancel a Reserve.
     */
    void cancelReserve(){
        bookController.cancelReserve();
        network.sendObject("CANCEL");
        Object answer = network.readObject();
    }

    /**
     * Getter a Current Menu
     * @return a list.
     */
    LinkedList<Dish> getCurrentMenu(){
        network.sendObject("DISHES");
        Object list = network.readObject();
        return (LinkedList<Dish>)list;
    }

    /**
     * Update Menu
     * @param dish Dish
     */
    void updateMenu(Dish dish){
        network.sendObject(dish);
        Object answer = network.readObject();
        LinkedList<Dish> carte = (LinkedList<Dish>)answer;
        mainTableController.updateMenu(carte);
    }

    /**
     * Function about login.
     * @param user User
     */
    void login(User user){
        network.sendObject(user);
        Object answer = network.readObject();
        launcherController.setLogType((String)answer);
        if(answer.equals("Reserve")){
            network.sendObject("DISHES");
            Object list = network.readObject();
            mainTableController.updateMenu((LinkedList<Dish>) list);
        }
        if(answer.equals("OUT")){
            Pop p = new Pop("Login doesn't exists");
        }
        if(answer.equals("NOT")){
            Pop p = new Pop("Not your turn yet");
        }
    }

    /**
     * Function about billed.
     */
    void billed(){
        network.sendObject("BILLED");
        Object answer = network.readObject();
        System.out.println((String)answer);
    }

    /**
     * Function about register
     * @param user User.
     */
    void register(User user){
        //enviamos el user con los 3 campos
        network.sendObject(user);
        Object answer = network.readObject();
        if(answer == "OK"){
            Pop pop = new Pop("Registered Succesfully");
            network.sendObject("CLOSE");//cerramos la conexion
        }
        network.disconnect();
    }

    /**
     * Function about Start the session
     * @param user User
     */
    void startSession(User user){
        model.setUser(user);
    }

    /**
     * Function about run
     * @param t string
     * @return a String
     */
    String runTime(String t){
        network.sendObject("time");
        Object ans = network.readObject();
        t =(String) ans;
        return t;
    }

    /**
     * Getter a Dishes a state.
     * @return a list.
     */
    LinkedList<Boolean> getDishesState(){
        network.sendObject("CLOCK");
        Object answer = network.readObject();
        String obj = (String) answer;
        String []array = obj.split(":");
        LinkedList<Boolean> bools = new LinkedList<>();
        for(int i=0;i<array.length;i++){
            if(array[i].equals("true")){
                bools.add(true);
            }else {
                bools.add(false);
            }

        }
        return bools;
    }
}
