package View;

import Controller.LauncherController;

import javax.swing.*;
import java.awt.*;

public class Launcher extends JPanel {
    // instance variables
    private final ProgressListener listener;
    private LauncherController controller;

    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    public Launcher(ProgressListener listener, LauncherController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        this.controller = controller;
        this.controller.setLauncher(this);
        // UI configuration of the panel
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We configure the window.
        new BorderLayout();
        // We instance the title of the window
        JLabel welcome = new JLabel("Welcome to the Server");
        // We instance the panel body of the window


        // We create the different parts of the window.

        // We add the content to the window.
        add(welcome,BorderLayout.CENTER);
    }
    /**
     * Method that will create all the components of the panel.
     * @param windowName that indicate which window will target and change into.
     */
    public void goToWindow(String windowName){
        // List of available views from this one
        switch (windowName){
            case "SIGN_IN":

                break;
            case "REGISTER":

                break;
            default:
                System.err.println("Unknown window name Launcher");
                break;
        }
    }
}
