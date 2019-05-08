package View.SubView;

import Controller.SubController.TableManagementController;
import View.Items.TableItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//tODO: que si introduzxco letra no pete
public class TableMangement extends JPanel {
    private JPanel items;
    private ArrayList<TableItem> tables;
    private TableManagementController controller;
    private JButton add;
    private JLabel numcoms;
    private TextField field;
    public TableMangement(TableManagementController controller) {
        this.controller = controller;
        this.controller.setTableMangement(this);
        setLayout(new BorderLayout());
        items = new JPanel();
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        //JScrollPane dishesList = new JScrollPane(list);
        JScrollPane list = new JScrollPane(items);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(northPart(),BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
        init();
    }

    public JPanel northPart(){
        JPanel up = new JPanel(new FlowLayout());
        add = new JButton("Add Table");
        add.setActionCommand("ADD");
        add.addActionListener(controller);
        numcoms = new JLabel("Numero comensales");
        field = new TextField();

        up.add(numcoms);
        up.add(field);
        up.add(add);
        return up;
    }
    public void init(){
        tables = new ArrayList<>();
        items.removeAll();
        //get add data

        items.revalidate();
        repaint();
    }
    public void addItem(String num){
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
        return Integer.parseInt(field.getText());
    }
}
