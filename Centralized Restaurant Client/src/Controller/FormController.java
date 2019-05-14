package Controller;

import Model.ModelClient;
import Network.Network;
import View.MyForm;

import java.io.IOException;

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
    public void startSession(String username){
        model.setLogin(username);
    }
}
