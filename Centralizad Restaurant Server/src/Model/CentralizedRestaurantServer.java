package Model;
import Controller.FormController;
import Network.Network;
import Resources.Pop;
import View.WindowForm;

import java.sql.*;
/**
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Victor Salvador
 * @version 1.0
 *
 * Centralized Restaurant(MVC) DPOO - 2018/19
 *
 * Main class of the program.
 */
public class CentralizedRestaurantServer {
    /**
     * entry point
     * @param args of the function
     */
    public static void main(String[] args) {
        //We create the model
        Model model = new Model();
        if(model.getServerState().equals("OUT")){
            Pop warning = new Pop("config.json not configured");
        }else {
            //We create the view
            WindowForm view = new WindowForm();
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
}
