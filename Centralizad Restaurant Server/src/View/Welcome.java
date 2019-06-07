package View;



import Controller.WelcomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Welcome extends JPanel {
    // instance variables
    private final ProgressListener listener;
    private JButton logOut;

    public Welcome(ProgressListener listener, WelcomeController controller) {
        this.listener = listener;
        controller.setWelcome(this);
        // UI configuration of the panel
        windowConfiguration();
        registerController(controller);
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We configure the window.
        new BorderLayout();
        JPanel grid = new JPanel();
        grid.setLayout(new BoxLayout(grid,BoxLayout.Y_AXIS));
        // We instance the title of the window
        JLabel welcome = new JLabel("Welcome to the Server");
        grid.add(Box.createVerticalStrut(20));
        grid.add(welcome);
        grid.add(Box.createVerticalStrut(20));
        logOut = new JButton("LOG OUT");
        grid.add(logOut);
        // We add the content to the window.
        add(grid,BorderLayout.CENTER);
    }

    private void registerController(ActionListener e){
        logOut.setActionCommand("LOGOUT");
        logOut.addActionListener(e);
    }
    /**
     * Method that will create all the components of the panel.
     * @param windowName that indicate which window will target and change into.
     */
    public void goToWindow(String windowName){
        // List of available views from this one
        switch (windowName){
            case "OUT":
                listener.progressFrom(ProgressListener.AppState.LAUNCHER);
                break;
            default:
                System.err.println("Unknown window name Welcome");
                break;
        }
    }
}
