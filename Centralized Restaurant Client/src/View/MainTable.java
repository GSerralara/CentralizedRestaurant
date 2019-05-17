package View;

import Controller.SubController.BillController;
import Controller.MainTableController;
import Controller.SubController.MenuController;
import Controller.SubController.OrderController;
import Model.Time;
import View.SubView.Bill;
import View.SubView.Menu;
import View.SubView.Order;

import javax.swing.*;
import java.awt.*;

public class MainTable extends JPanel{
    // instance variables
    private final ProgressListener listener;
    private MainTableController controller;
    /**
     * Constants for the UI
     */
    // General constants
    private static final String TIME_LABEL_TEXT = "HH:MM";
    private static final String MENU_TAB_TEXT = "Menu";
    private static final String ORDER_TAB_TEXT = "Order";
    private static final String BILL_TAB_TEXT = "Bill";

    /**
     * Attributes of the UI
     */
    private String strTime;
    private JLabel time;
    private Bill bill;
    private Menu menu;
    private Order order;
    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    public MainTable(ProgressListener listener, MainTableController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        this.controller = controller;
        controller.setMainTable(this);
        // UI configuration of the panel
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We configure the window.
        setLayout(new BorderLayout());
        // instance label of time of the view
        this.strTime = TIME_LABEL_TEXT;
        time = new JLabel(strTime);
        // instance JPanels that will from the tabs of the panel
        bill = new Bill(new BillController(controller));
        menu = new Menu(new MenuController(controller));
        menu.init();//We init the menu to have the contents
        order = new Order(new OrderController(controller));
        // instance JTabbedPane options that will have JPanels as tabs
        JTabbedPane options = new JTabbedPane();
        // instance JPanel that will go to the top of the view
        JPanel top = new JPanel(new FlowLayout());
        top.add(time);
        add(top,BorderLayout.NORTH);
        // adding JPanels as tabs
        options.addTab(MENU_TAB_TEXT, menu);
        options.addTab(ORDER_TAB_TEXT, order);
        options.addTab(BILL_TAB_TEXT, bill);
        add(options,BorderLayout.CENTER);

    }
    /**
     * Method that will create all the components of the panel.
     * @param windowName that indicate which window will target and change into.
     */
    public void goToWindow(String windowName){
        switch (windowName){
            // List of available views from this one
            case "LAUNCHER":
                this.listener.progressFrom(ProgressListener.AppState.LAUNCHER);
                break;
            default:
                System.err.println("Unknown window name MainTable");
                break;
        }
    }

    /**
     * Method that will update time String of the view
     * @param currentTime string that represents being the current time HH:MM format
     */
    public void updateTime(String currentTime){
        this.strTime = currentTime;
        this.time.setText(strTime);
    }

    public void initTime(){
        controller.currentTime(strTime);
        Time t = new Time(this.time);
        new Thread(t).start();
    }
}
