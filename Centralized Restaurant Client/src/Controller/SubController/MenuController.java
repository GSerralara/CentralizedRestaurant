package Controller.SubController;

import Controller.MainTableController;
import View.SubView.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController  implements ActionListener {
    private Menu menu;
    private MainTableController listener;
    public MenuController(MainTableController listener) {
        this.listener = listener;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        this.listener.setMenuController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int times = menu.getCuantity(e.getActionCommand());
        for(int i=0;i<times;i++){
            this.listener.giveCommand("ADD_TO_ORDER",e.getActionCommand());
        }
    }
}
