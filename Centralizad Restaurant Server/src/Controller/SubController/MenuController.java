package Controller.SubController;

import Controller.PreServiceController;
import Model.Database.Entity.Dish;
import Model.Database.dao.DishDAO;
import Resources.Pop;
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
            if(menu.isNameIntroduced()){
                listener.addDish(menu.addItem());
            }else{
                Pop pop = new Pop("At least introduce a dish name");
            }

        }else {
            menu.cancelItem(e.getActionCommand());
        }
    }
    public void addDish(Dish d){
        menu.addDish(d);
    }
    public void enableAddoption(boolean state){
        menu.enableButton(state);
    }
    public void init(){
        menu.init();
        menu.enableButton(false);
    }
}
