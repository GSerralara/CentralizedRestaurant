package Controller;

import View.Welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * WelcomeController class
 */
public class WelcomeController implements ActionListener {
    private Welcome welcome;
    private FormController listener;

    /**
     * Default class Constructor
     * @param listener link to formController
     */
    public WelcomeController(FormController listener) {
        this.listener = listener;
    }

    /**
     * setter of main view
     * @param welcome view
     */
    public void setWelcome(Welcome welcome) {
        this.welcome = welcome;
    }

    /**
     * override function
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        welcome.goToWindow("OUT");
        listener.logout();
    }
}
