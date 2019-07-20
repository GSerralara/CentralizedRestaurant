package Controller;

import View.SideMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * sideMenuController class
 */
public class SideMenuController implements ActionListener {
    private SideMenu sideMenu;
    private FormController listener;
    private String windowName ="PRE-SERVICE";

    /**
     * Constructor by default of the class.
     * @param listener it's a parameter the type of FormController
     */

    public SideMenuController(FormController listener) {
        this.listener = listener;
    }

    public void setSideMenu(SideMenu sideMenu) {
        this.sideMenu = sideMenu;
    }

    /**
     * This method change the State of the different Windows.
     */

    public void changeWindowState() {
        if(this.windowName.equals("PRE-SERVICE")){
            this.windowName = "SERVICE";
        }else{
            this.windowName ="PRE-SERVICE";
        }
    }

    /**
     * Override Method from ActionListener that activates when a Swing element,
     * with this class as an ActionListener, is interacted with.
     * @param e ActionEvent that will get the method.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "CHANGE":
                sideMenu.goToWindow(e.getActionCommand());
                break;
            case "CURRENT":
                String state = "";
                if(listener.getState().equals("STOP")) sideMenu.goToWindow("POST-SERVICE");
                if(listener.getState().equals("END")) sideMenu.goToWindow("WELCOME");
                if(listener.getState().equals("INIT")){
                    //ToDo: init at this point the network as open
                    sideMenu.goToWindow(windowName);
                }
                break;
            case "AUTH":
                sideMenu.goToWindow(e.getActionCommand());
                break;
        }
    }
}
