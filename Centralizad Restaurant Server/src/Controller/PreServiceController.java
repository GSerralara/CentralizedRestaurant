package Controller;

import Controller.SubController.CarteController;
import Controller.SubController.MenuController;
import Controller.SubController.TableManagementController;
import Model.Database.Entity.Dish;
import View.PreService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PreserviceController Class
 */
public class PreServiceController implements ActionListener {
    private FormController listener;
    private PreService preService;
    private TableManagementController subController_1;
    private MenuController subController_2;
    private CarteController subController_3;

    /**
     * * Constructor by default of the class.
     * @param listener it's a FormController that the class will use to move to other views
     */

    public PreServiceController(FormController listener) {
        this.listener = listener;
        subController_1 = new TableManagementController(this);
        subController_2 = new MenuController(this);
        subController_3 = new CarteController(this);
    }

    /**
     * setter of view
     * @param preService view
     */
    public void setPreService(PreService preService) {
        this.preService = preService;
    }

    /**
     * getter of subcontroller
     * @return
     */
    public TableManagementController getSubController_1() {
        return subController_1;
    }

    /**
     * getter of subcontroller
     * @return
     */
    public MenuController getSubController_2() {
        return subController_2;
    }

    /**
     * getter of subcontroller
     * @return
     */
    public CarteController getSubController_3() {
        return subController_3;
    }

    /**
     *
     * @param q
     */
    public void addTable(int q){
        listener.addTable(q);
    }

    /**
     * adds a existing Table from bbdd
     * @param q max customer
     */
    public void addExistingTable(int q){
        subController_1.addExistingTable(q);
    }

    /**
     * removes a table
     * @param pos
     */
    public void removeTable(String pos){
        int index = Integer.parseInt(pos);
        listener.removeTable(index);
    }

    /**
     * create a menu
     * @param name name of menu
     */
    public void createMenu(String name){
        listener.createMenu(name);
        subController_2.enableAddoption(true);
    }

    /**
     * init all subcontrollers
     */
    public void init(){
        subController_1.init();
        subController_2.init();
        subController_3.init();
    }

    /**
     * add a menu
     * @param name name
     */
    public void addMenu(String name){
        subController_3.addMenu(name);
    }

    /**
     * add a dish
     * @param d dish
     */
    public void addDish(Dish d){
        listener.addDish(d);
    }

    /**
     * load a menu from bbdd
     * @param menu menu name
     */
    public void loadMenu(String menu){
        subController_2.init();
        listener.loadMenu(menu);
        subController_2.enableAddoption(true);
    }

    /**
     * load a menu dish
     * @param d Dish
     */
    public void loadMenuDish(Dish d){
        subController_2.addDish(d);
    }

    /**
     * override fucntion
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
