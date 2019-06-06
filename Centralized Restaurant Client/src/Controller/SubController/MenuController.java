package Controller.SubController;

import Controller.MainTableController;
import Model.Database.Entity.Dish;
import View.SubView.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
        LinkedList<Dish> dishes = listener.getCurrentMenu();
        for(Dish i: dishes){
            if(i.getQuantety() >= times && i.getName().equals(e.getActionCommand())){
                for(int j=0;j<times;j++){
                    this.listener.giveCommand("ADD_TO_ORDER",i);
                }
            }
        }
        listener.updateMenuState(e.getActionCommand());
    }

    public void addDishes(Dish dish){
           menu.addItem(dish.getName(),dish.getQuantety());
    }

    public void removeDishes(){
        menu.init();
    }
}
