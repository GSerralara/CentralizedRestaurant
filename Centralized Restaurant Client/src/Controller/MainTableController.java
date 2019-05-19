package Controller;

import Controller.SubController.BillController;
import Controller.SubController.MenuController;
import Controller.SubController.OrderController;
import View.MainTable;



public class MainTableController {
    private MainTable mainTable;
    private FormController listener;
    private MenuController menuController;
    private OrderController orderController;
    private BillController billController;

    public MainTableController(FormController controller) {
        this.listener = controller;
    }

    public void setBillController(BillController billController) {
        this.billController = billController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setMainTable(MainTable mainTable) {
        this.mainTable = mainTable;
    }

    public void giveCommand(String command, String data){
        //Take menu items, request time, give data ...
        switch (command){
            case "PAY":
                //close session
                listener.billed();
                listener.closeSession();
                mainTable.goToWindow("LAUNCHER");
                break;
            case "ADD_TO_ORDER":
                this.orderController.addToOrder(data,"MM:SS");
        }
    }

    public void currentTime(String time){
        mainTable.updateTime(listener.runTime(time));

    }
}
