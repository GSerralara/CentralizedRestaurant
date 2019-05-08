package Model;
import Controller.FormController;
import Network.Network;
import View.WindowForm;

import java.sql.*;
public class CentralizedRestaurantServer {
    public static void main(String[] args) {
        //We create the view
        WindowForm view = new WindowForm();
        //We create the model
        Model model = new Model();
        //We create the access to the Server
        Network net = new Network();
        //We create the controller passing him the view, the model and the access to the Server.
        FormController c = new FormController(view, model, net);
        //We register the controller with the view
        view.registerController(c);
        //We register the controller with the net
        net.registerController(c);
        //We init the view
        view.go();
    }
}
