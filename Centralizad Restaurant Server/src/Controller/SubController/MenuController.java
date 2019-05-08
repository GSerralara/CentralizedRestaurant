package Controller.SubController;

import Controller.PreServiceController;
import View.SubView.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private PreServiceController listener;
    private Menu menu;

    public MenuController(PreServiceController listener) {
        this.listener = listener;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("ADD")){
            menu.addItem();
        }else {
            menu.cancelItem(e.getActionCommand());
        }
    }
}
