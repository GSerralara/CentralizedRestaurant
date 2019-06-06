package Controller;

import View.Welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeController implements ActionListener {
    private Welcome welcome;
    private FormController listener;

    public WelcomeController(FormController listener) {
        this.listener = listener;
    }

    public void setWelcome(Welcome welcome) {
        this.welcome = welcome;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        welcome.goToWindow("OUT");
    }
}
