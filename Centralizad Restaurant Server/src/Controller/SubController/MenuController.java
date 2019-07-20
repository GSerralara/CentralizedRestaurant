package Controller.SubController;

import Controller.PreServiceController;
import Model.Database.Entity.Dish;
import Model.Database.dao.DishDAO;
import Resources.Pop;
import View.SubView.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This method it's a ActionListener of the Menu.
 */

public class MenuController implements ActionListener {
    private PreServiceController listener;
    private Menu menu;

    /**
     * * Constructor by default of the class.
     * @param listener it's a PreServiceController that the class will use to move to other views
     */

    public MenuController(PreServiceController listener) {
        this.listener = listener;
    }

    /**
     * view setter
     * @param menu view
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * This method override the method ActionPerformed.
     * @param e it's a parameter the type of ActionEvent.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("ADD")){
            if(menu.isNameIntroduced()){
                listener.addDish(menu.addItem(this));
            }else{
                Pop pop = new Pop("At least introduce a dish name");
            }

        }else {
            menu.cancelItem(e.getActionCommand());
        }
    }

    /**
     * Function that add a dish.
     * @param d it's a parameter that have all the information of each dish.
     */
    public void addDish(Dish d){
        menu.addDish(d,this);
    }

    /**
     * enable button
     * @param state
     */
    public void enableAddoption(boolean state){
        menu.enableButton(state);
    }

    /**
     * Function that start the menu.
     */
    public void init(){
        menu.init();
        menu.enableButton(false);
    }
}
