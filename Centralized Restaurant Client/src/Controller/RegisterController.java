package Controller;


import Model.Database.Entity.User;
import Resources.Pop;
import View.Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * RegisterController class
 * Implements ActionListener
 * It's function is to manage what happens in the Register view
 *
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 */
public class RegisterController implements ActionListener {

    // attributes of the class
    private Register register;
    private FormController listener;
    /**
     * Constructor by default of the class.
     * @param listener it's the controller father that manages all petitions
     * */
    public RegisterController(FormController listener) {
        this.listener = listener;
    }
    /**
     * Setter that sets the view that will retrieve data from.
     * @param register the view corresponding to this controller
     * */
    public void setRegister(Register register) {
        this.register = register;
    }
    /**
     * Override Method from ActionListener that activates when a Swing element,
     * with this class as an ActionListener, is interacted with.
     * @param e ActionEvent that will get the method.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "REGISTER":
                //first we check that all camps are filled
                if(register.allfilled()){
                    //then if passwords match
                    if(register.samePwd()){
                        //after that we try to connect to the server
                        if(listener.tryConnection()){
                            //send petition
                            User user = new User(register.getUser(),register.getPwd());
                            //ToDo: comprobar el registro que vaya bien y luego hacer el go to window
                            listener.register(user);
                            register.goToWindow("REGISTER");
                        }
                    }else{
                        Pop popup = new Pop("Passwords don't match");
                    }
                }
                break;
            case "SIGN_IN":
                register.goToWindow("SIGN_IN");
                break;
            default:
                System.err.println("Unknown Button register");
                break;
        }
    }
}
