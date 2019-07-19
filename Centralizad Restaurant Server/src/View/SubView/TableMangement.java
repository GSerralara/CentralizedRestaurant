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

public class TableMangement extends JPanel {
    private JPanel items;
    private ArrayList<TableItem> tables;

    private JButton add;
    private JLabel numcoms;
    private JSpinner field;
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
    public void init(){
        tables = new ArrayList<>();
        items.removeAll();
        items.revalidate();
        repaint();
    }
    public void addItem(String num,TableManagementController controller){
        int pos = Integer.parseInt(num);
        this.tables.add(new TableItem(controller,tables.size(),pos));
        this.items.add(tables.get(tables.size()-1));
        items.revalidate();
        repaint();
    }
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
    public int listSize(){
        return tables.size();
    }
    public int getNumCustomers(){
        int value = (Integer) field.getValue();
        return value;
    }
    public void registerController(ActionListener e){
        add.setActionCommand("ADD");
        add.addActionListener(e);
    }
}
