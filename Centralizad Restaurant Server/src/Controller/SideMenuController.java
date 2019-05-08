package Controller;

import View.SideMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuController implements ActionListener {
    private SideMenu sideMenu;
    private FormController listener;

    public SideMenuController(FormController listener) {
        this.listener = listener;
    }

    public void setSideMenu(SideMenu sideMenu) {
        this.sideMenu = sideMenu;
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
                    sideMenu.goToWindow("PRE-SERVICE");
                }
                break;
            case "AUTH":
                sideMenu.goToWindow(e.getActionCommand());
                break;
        }
    }
}
