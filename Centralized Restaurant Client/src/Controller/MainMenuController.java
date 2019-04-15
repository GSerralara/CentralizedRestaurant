package Controller;

import View.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
    private FormController listener;
    private MainMenu mainMenu;

    public MainMenuController(FormController listener) {
        this.listener = listener;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGOUT":
                mainMenu.goToWindow("LOGOUT");
                break;
            case "BOOK":
                mainMenu.goToWindow("BOOK");
                break;
            case "CANCEL":
                mainMenu.goToWindow("CANCEL");
                break;
        }
    }
}
