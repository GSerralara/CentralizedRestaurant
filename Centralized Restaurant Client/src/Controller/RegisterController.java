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
    RegisterController(FormController listener) {
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
                        if(register.passCheck(register.getPwd())){
                            //after that we try to connect to the server
                            if(listener.tryConnection()){
                                //send petition
                                User user = new User(register.getUser(),
                                        MD5(register.getPwd()));
                                user.isRegister();
                                listener.register(user);
                                register.goToWindow("REGISTER");
                            }
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

    /**
     * Encyptation function
     * @param md5 String
     * @return a string.
     */
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
