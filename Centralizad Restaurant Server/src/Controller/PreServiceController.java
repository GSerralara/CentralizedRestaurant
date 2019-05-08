package Controller;

import Controller.SubController.MenuController;
import Controller.SubController.TableManagementController;
import View.PreService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreServiceController implements ActionListener {
    private FormController listener;
    private PreService preService;
    private TableManagementController subController_1;
    private MenuController subController_2;

    public PreServiceController(FormController listener) {
        this.listener = listener;
        subController_1 = new TableManagementController(this);
        subController_2 = new MenuController(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
