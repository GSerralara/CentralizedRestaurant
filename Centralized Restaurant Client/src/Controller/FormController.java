package Controller;

import Model.ModelClient;
import Network.Network;
import View.MyForm;

public class FormController {
    private MyForm view;
    private ModelClient model;
    private Network network;

    private LauncherController launcherController;
    private RegisterController registerController;
    private MainController mainController;
    private MainTableController mainTableController;

    public FormController(MyForm view, ModelClient model, Network network) {
        this.view = view;
        this.model = model;
        this.network = network;

        launcherController = new LauncherController(this);
        registerController = new RegisterController(this);
        mainController = new MainController(this);
        mainTableController = new MainTableController(this);
    }

    public MainTableController getMainTableController() {
        return mainTableController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public RegisterController getRegisterController() {
        return registerController;
    }

    public LauncherController getLauncherController() {
        return launcherController;
    }
}
