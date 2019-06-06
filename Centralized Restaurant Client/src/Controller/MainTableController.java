package Controller;

import Controller.SubController.BillController;
import Controller.SubController.MenuController;
import Controller.SubController.OrderController;
import Model.Database.Entity.Dish;
import View.MainTable;

import java.net.Inet4Address;
import java.security.spec.DSAGenParameterSpec;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;


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

    public MainTableController(FormController controller) {
        this.listener = controller;
        order =  new LinkedList<>();
        qOrder = new LinkedList<>();
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
                        if(order.get(i).getName()==dish.getName()){
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

    public void currentTime(String time){
        mainTable.updateTime(listener.runTime(time));
    }
    public LinkedList<Dish> getCurrentMenu(){
        this.currentMenu = listener.getCurrentMenu();
        return this.currentMenu;
    }
    public void updateMenu(LinkedList<Dish> menu){
        this.currentMenu = menu;
        menuController.removeDishes();
        for(Dish i:menu){
            menuController.addDishes(i);
        }

    }
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
    public void initTimerService(){
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
    public LinkedList<Boolean> requestDishesState(){
        LinkedList<Boolean> dishes = listener.getDishesState();
        return dishes;
    }
    public void changeSendRequest(){
        this.sendRequest =false;
    }

}
