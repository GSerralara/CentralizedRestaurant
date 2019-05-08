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

    public FormController(MyForm view, ModelClient model, Network network) {
        this.view = view;
        this.model = model;
        this.network = network;
        this.network.registerController(this);
        network.connect();

        launcherController = new LauncherController(this);
        registerController = new RegisterController(this);
        mainTableController = new MainTableController(this);
        mainMenuController = new MainMenuController(this);
        bookController = new BookController(this);
    }

    public MainTableController getMainTableController() {
        return mainTableController;
    }

    public BookController getBookController() {
        return bookController;
    }

    public RegisterController getRegisterController() {
        return registerController;
    }

    public LauncherController getLauncherController() {
        return launcherController;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }
}
