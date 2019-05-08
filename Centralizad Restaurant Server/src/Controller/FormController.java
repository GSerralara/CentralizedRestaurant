package Controller;

import Model.Model;
import Network.Network;
import View.WindowForm;

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

    public int getCommuncicationPort(){
        return model.getCommuncicationPort();
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
}
