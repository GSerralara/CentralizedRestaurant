package Model;

import Controller.FormController;
import Network.Network;
import View.MyForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

/**
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 * @version 1.0
 *
 * Centralized Restaurant(MVC) DPOO - 2018/19
 *
 * Main class of the program.
 */
public class CentralizedRestaurant {

    public static void main(String[] args){

        //We create the view
        MyForm view = new MyForm();
        //We create the model
        ModelClient model = new ModelClient();
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
