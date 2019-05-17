package Controller;

import View.SideMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuController implements ActionListener {
    private SideMenu sideMenu;
    private FormController listener;
    private String windowName ="PRE-SERVICE";

    public SideMenuController(FormController listener) {
        this.listener = listener;
    }

    public void setSideMenu(SideMenu sideMenu) {
        this.sideMenu = sideMenu;
    }

    public void changeWindowState() {
        if(this.windowName.equals("PRE-SERVICE")){
            this.windowName = "SERVICE";
        }else{
            this.windowName ="PRE-SERVICE";
        }
    }

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
                    //ToDo: Check if people are already authorithed
                    sideMenu.goToWindow(windowName);
                }
                break;
            case "AUTH":
                sideMenu.goToWindow(e.getActionCommand());
                break;
        }
    }
}
