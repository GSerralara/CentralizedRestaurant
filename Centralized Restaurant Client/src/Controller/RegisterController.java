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
    private Register register;
    private FormController listener;
    public RegisterController(FormController listener) {
        this.listener = listener;
    }

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
        //System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case "REGISTER":
                System.out.println(register.getUser()+register.getMail());
                if(register.samePwd()){
                    if(listener.tryConnection()){
                        User user = new User(register.getUser(),register.getMail(),register.getPwd());
                        listener.register(user);
                        register.goToWindow("REGISTER");
                    }
                }else{
                    Pop popup = new Pop("Passwords don't match or not written");
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
