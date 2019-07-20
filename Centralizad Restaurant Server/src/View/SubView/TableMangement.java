package View.SubView;

import Controller.SubController.TableManagementController;
import Model.Database.Entity.Table;
import Model.Database.dao.TableDAO;
import View.Items.TableItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * TableManagement class
 * extends of JPanel
 * serves as manager of the tables of the restaurant
 */
public class TableMangement extends JPanel {
    private JPanel items;
    private ArrayList<TableItem> tables;

    private JButton add;
    private JLabel numcoms;
    private JSpinner field;

    /**
     * This function is the class constructor. In if this class has as objective to manage the tables.
     * @param controller it's the respective controller of this view.
     */

    public TableMangement(TableManagementController controller) {

        controller.setTableMangement(this);
        setLayout(new BorderLayout());
        items = new JPanel();
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        //JScrollPane dishesList = new JScrollPane(list);
        JScrollPane list = new JScrollPane(items);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(northPart(),BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
        init();
        registerController(controller);
    }

    /**
     * This function creates the view of the upper part.
     * @return a JPanel.
     */
    public JPanel northPart(){
        JPanel up = new JPanel(new FlowLayout());
        add = new JButton("Add Table");

        numcoms = new JLabel("Numero comensales");
        //default value,lower bound,upper bound,increment by
        SpinnerModel sm = new SpinnerNumberModel(1, 1, 999, 1);
        field = new JSpinner(sm);
        up.add(numcoms);
        up.add(field);
        up.add(add);
        return up;
    }

    /**
     * Function that start the view.
     */
    public void init(){
        tables = new ArrayList<>();
        items.removeAll();
        items.revalidate();
        repaint();
    }

    /**
     *Function that adds an item in view.
     * @param num it's a variable that have the number in one String.
     * @param controller it's the respective controller of this view.
     */
    public void addItem(String num,TableManagementController controller){
        int pos = Integer.parseInt(num);
        this.tables.add(new TableItem(controller,tables.size(),pos));
        this.items.add(tables.get(tables.size()-1));
        items.revalidate();
        repaint();
    }

    /**
     * Function that cancel an item in view.
     * @param pos it's a variable that have the number in one String.
     */
    public void cancelItem(String pos){
        int index = Integer.parseInt(pos);
        items.remove(index);
        tables.remove(index);
        for(int i =0; i< tables.size();i++){
            tables.get(i).updatePos(i);
        }
        items.revalidate();
        repaint();
    }

    /**
     * function that gives you the number of tables
     * @return number of tables
     */
    public int listSize(){
        return tables.size();
    }

    /**
     * function that gives you the numeber of max customers per table
     * @return number of max customers
     */
    public int getNumCustomers(){
        int value = (Integer) field.getValue();
        return value;
    }

    /**
     * In this function we will create the setActionCommand and the addActionListener.
     * @param e it's a variable that contains the ActionListener.
     */
    public void registerController(ActionListener e){
        add.setActionCommand("ADD");
        add.addActionListener(e);
    }
}
