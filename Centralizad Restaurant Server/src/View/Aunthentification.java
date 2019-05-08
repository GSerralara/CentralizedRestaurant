package View;

import Controller.AunthentificationController;
import View.Items.ReserveItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Aunthentification extends JPanel {
    private JPanel items;
    private ArrayList<ReserveItem> reserves;
    private AunthentificationController controller;
    private final ProgressListener listener;

    public Aunthentification(ProgressListener listener,AunthentificationController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        this.controller = controller;
        this.controller.setAunthentification(this);
        // UI configuration of the panel
        windowConfiguration();
    }
    public void windowConfiguration(){
        setLayout(new BorderLayout());
        items = new JPanel();
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        //JScrollPane dishesList = new JScrollPane(list);
        JScrollPane list = new JScrollPane(items);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(list, BorderLayout.CENTER);
    }
    public void init(){
        reserves = new ArrayList<>();
        items.removeAll();
        //get add data

        items.revalidate();
        repaint();
    }
    public void addItem(String name){
        //this.reserves.add(new MenuItem(name,controller));
        //this.list.add(menu.get(menu.size()-1));
        //this.items.add(reserves.get(reserves.size()-1));
        items.revalidate();
        repaint();
    }
}
