package View;


import Controller.SideMenuController;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SideMenu extends JPanel {
    // instance variables
    private final ProgressListener listener;
    private JButton changeState;
    private JButton toCurrentSerice;
    private JButton authentication;
    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    public SideMenu(ProgressListener listener, SideMenuController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        controller.setSideMenu(this);
        // UI configuration of the panel
        windowConfiguration();
        registerController(controller);
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        JPanel listScrollPane = new JPanel();
        listScrollPane.setLayout(new BoxLayout(listScrollPane,BoxLayout.Y_AXIS));
        changeState = new JButton("Change State");
        changeState.setBorderPainted(false);
        toCurrentSerice = new JButton("To Current Service");
        toCurrentSerice.setBorderPainted(false);
        authentication = new JButton("authentication");
        authentication.setBorderPainted(false);
        listScrollPane.add(changeState);
        listScrollPane.add(toCurrentSerice);
        listScrollPane.add(authentication);
        add(listScrollPane);
    }
    /**
     * Method that will create all the components of the panel.
     * @param windowName that indicate which window will target and change into.
     */

    public void goToWindow(String windowName){
        // List of available views from this one
        switch (windowName){
            case "CHANGE":
                this.listener.progressFrom(ProgressListener.AppState.SERVICESTATE);
                break;
            case "POST-SERVICE":
                this.listener.progressFrom(ProgressListener.AppState.POSTSERVICE);
                break;
            case "WELCOME":
                //System.out.println("WEL");
                this.listener.progressFrom(ProgressListener.AppState.WELCOME);
                break;
            case "PRE-SERVICE":
                //System.out.println("PRE");
                this.listener.progressFrom(ProgressListener.AppState.PRESERVICE);
                break;
            case "SERVICE":
                this.listener.progressFrom(ProgressListener.AppState.SERVICE);
                break;
            case "AUTH":
                this.listener.progressFrom(ProgressListener.AppState.AUNTHENTICATION);
                break;
            default:
                System.err.println("Unknown window name Launcher");
                break;
        }
    }

    /**
     * In this function we will create the setActionCommand and the addActionListener.
     * @param e it's a variable that contains the ActionListener.
     */

    private void registerController(ActionListener e){
        changeState.setActionCommand("CHANGE");
        changeState.addActionListener(e);
        toCurrentSerice.setActionCommand("CURRENT");
        toCurrentSerice.addActionListener(e);
        authentication.setActionCommand("AUTH");
        authentication.addActionListener(e);
    }
}
