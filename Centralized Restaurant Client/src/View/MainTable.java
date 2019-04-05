package View;

import Controller.BillController;
import Controller.MainTableController;
import Controller.MenuController;
import Controller.OrderController;

import javax.swing.*;
import java.awt.*;

public class MainTable extends JPanel {
    // instance variables
    private final ProgressListener listener;
    private MainTableController controller;
    /**
     * Attributes of the UI
     */
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

        time = new JLabel("HH:MM");
        bill = new Bill(new BillController(controller));
        menu = new Menu(new MenuController(controller));
        menu.init();
        order = new Order(new OrderController(controller));
        JTabbedPane options = new JTabbedPane();
        //JPanel content = new JPanel(new BorderLayout());
        JPanel top = new JPanel(new FlowLayout());
        top.add(time);
        add(top,BorderLayout.NORTH);

        options.addTab("Menu", menu);
        options.addTab("Order", order);
        options.addTab("Bill",bill);
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

}
