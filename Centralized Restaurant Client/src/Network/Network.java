package Network;

import Controller.FormController;

public class Network {
    private FormController controller;
    public Network() {

    }
    public void registerController(FormController c){
        this.controller = c;
    }
}
