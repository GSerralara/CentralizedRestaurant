package Controller;

import Resources.Pop;
import View.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainMenuController class
 */
public class MainMenuController implements ActionListener {
    private FormController listener;
    private MainMenu mainMenu;

    /**
     * default class
     * @param listener controller
     */
    MainMenuController(FormController listener) {
        this.listener = listener;
    }

    /**
     * setter main menu
     * @param mainMenu MainMenu
     */
    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * override fucntion
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGOUT":
                listener.closeSession();
                mainMenu.goToWindow("LOGOUT");
                break;
            case "BOOK":
                mainMenu.goToWindow("BOOK");
                break;
            case "CANCEL":
                Pop pop = new Pop("Your reserve was cancelled");
                listener.cancelReserve();
                break;
        }
    }
}
