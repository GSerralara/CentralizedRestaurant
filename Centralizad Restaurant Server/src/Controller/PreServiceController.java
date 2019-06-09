package Controller;

import Controller.SubController.CarteController;
import Controller.SubController.MenuController;
import Controller.SubController.TableManagementController;
import Model.Database.Entity.Dish;
import View.PreService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreServiceController implements ActionListener {
    private FormController listener;
    private PreService preService;
    private TableManagementController subController_1;
    private MenuController subController_2;
    private CarteController subController_3;


    public PreServiceController(FormController listener) {
        this.listener = listener;
        subController_1 = new TableManagementController(this);
        subController_2 = new MenuController(this);
        subController_3 = new CarteController(this);
    }

    public void setPreService(PreService preService) {
        this.preService = preService;
    }

    public TableManagementController getSubController_1() {
        return subController_1;
    }

    public MenuController getSubController_2() {
        return subController_2;
    }

    public CarteController getSubController_3() {
        return subController_3;
    }

    public void addTable(int q){
        listener.addTable(q);
    }
    public void addExistingTable(int q){
        subController_1.addExistingTable(q);
    }
    public void removeTable(String pos){
        int index = Integer.parseInt(pos);
        listener.removeTable(index);
    }
    public void createMenu(String name){
        listener.createMenu(name);
        subController_2.enableAddoption(true);
    }
    public void init(){
        subController_1.init();
        subController_2.init();
        subController_3.init();
    }
    public void addMenu(String name){
        subController_3.addMenu(name);
    }
    public void addDish(Dish d){
        listener.addDish(d);
    }
    public void loadMenu(String menu){
        subController_2.init();
        listener.loadMenu(menu);
        subController_2.enableAddoption(true);
    }
    public void loadMenuDish(Dish d){
        subController_2.addDish(d);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
