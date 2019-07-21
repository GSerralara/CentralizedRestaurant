package Controller;

import Controller.SubController.BillController;
import Controller.SubController.MenuController;
import Controller.SubController.OrderController;
import Model.Database.Entity.Dish;
import View.MainTable;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * MainTableController class
 */
public class MainTableController {
    private MainTable mainTable;
    private FormController listener;
    private MenuController menuController;
    private OrderController orderController;
    private BillController billController;
    private LinkedList<Dish> currentMenu;
    private LinkedList<Dish> order;
    private LinkedList<Integer> qOrder;
    private boolean sendRequest;

    /**
     * default controller
     * @param controller controller
     */
    MainTableController(FormController controller) {
        this.listener = controller;
        order =  new LinkedList<>();
        qOrder = new LinkedList<>();
    }

    /**
     * setter billController
     * @param billController c
     */
    public void setBillController(BillController billController) {
        this.billController = billController;
    }

    /**
     * setter OrderController
     * @param orderController c
     */
    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    /**
     * setter MenuController
     * @param menuController c
     */
    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    /**
     * view setter
     * @param mainTable view
     */
    public void setMainTable(MainTable mainTable) {
        this.mainTable = mainTable;
    }

    /**
     * function acts based on a command on call
     * @param command string
     * @param dish d
     */
    public void giveCommand(String command, Dish dish){
        //Take menu items, request time, give data ...
        switch (command){
            case "PAY":
                //close session
                listener.billed();
                listener.closeSession();
                mainTable.goToWindow("LAUNCHER");
                break;
            case "ADD_TO_ORDER":
                boolean pasive = false;
                if(order.size() != 0){
                    for(int i=0;i<order.size(); i++){
                        if(order.get(i).getName().equals(dish.getName())){
                            int q = qOrder.get(i).intValue()+1;
                            qOrder.set(i,q);
                            i =order.size();
                            pasive = true;
                        }
                    }
                    if(!pasive){
                        order.add(dish);
                        qOrder.add(1);
                    }
                }else{
                    initTimerService();
                    order.add(dish);
                    qOrder.add(1);
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss");

                this.orderController.addToOrder(dish.getName(),df.format(dish.getTime()));
                break;
        }

    }

    /**
     *  update time label
     * @param time timer
     */
    public void currentTime(String time){
        mainTable.updateTime(listener.runTime(time));
    }

    /**
     * getter currentMenu
     * @return list
     */
    public LinkedList<Dish> getCurrentMenu(){
        this.currentMenu = listener.getCurrentMenu();
        return this.currentMenu;
    }

    /**
     * update menu
     * @param menu list
     */
     void updateMenu(LinkedList<Dish> menu){
        this.currentMenu = menu;
        menuController.removeDishes();
        for(Dish i:menu){
            menuController.addDishes(i);
        }

    }

    /**
     * update menu state
     * @param name name
     */
    public void updateMenuState(String name){
        Dish dishAdded;
        for(int i=0;i<order.size(); i++){
            if(order.get(i).getName().equals(name)){
                dishAdded = order.get(i);
                dishAdded.setQuantety(qOrder.get(i));
                listener.updateMenu(dishAdded);
            }
        }
        menuController.removeDishes();
        for(Dish i:currentMenu){
            menuController.addDishes(i);
        }
    }

    /**
     * init timer
     */
    private void initTimerService(){
        sendRequest = true;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                LinkedList<Boolean> states;
                if(sendRequest){
                    states = requestDishesState();
                    orderController.treatOrder(states);
                    billController.addToPay(states,order);
                }else{
                    this.cancel();
                }
            }
        };
        timer.schedule(task,0,5000);
    }

    /**
     * cancel a dish by name
     * @param name name dish
     * @param index position in array
     */
    public void cancelDish(String name,int index){
        listener.cancelDish(name, index);
        for(int i=0; i<order.size();i++){
            System.out.println("actual order size "+order.size());
            if(order.get(i).getName().equals(name)){

                int q = qOrder.get(i).intValue() - 1;
                qOrder.set(i,q);
                System.out.println(q);
                if(q==0){
                    qOrder.remove(i);
                    order.remove(i);
                    if(order.size()==0){
                        sendRequest = false;
                        System.out.println("CLOSING");
                    }
                }
            }
        }
    }
    /**
     * request dishes states
     * @return list
     */
    private LinkedList<Boolean> requestDishesState(){
        return listener.getDishesState();
    }

    /**
     * change request bool
     */
    public void changeSendRequest(){
        this.sendRequest =false;
    }

}
