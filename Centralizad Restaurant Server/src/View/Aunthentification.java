package View;

import Controller.AunthentificationController;
import Model.DataBase.Entity.User;
import View.Items.AuthItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Aunthentification extends JPanel {
    private JPanel items;
    private ArrayList<AuthItem> reserves;
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
        items.revalidate();
        repaint();
    }
    public void addItem(User user, String name){
        this.reserves.add(new AuthItem(user,name,controller,reserves.size()));
        this.items.add(reserves.get(reserves.size()-1));
        items.revalidate();
        repaint();
    }
    public void CancelItem(String pos){
        int index = Integer.parseInt(pos);
        reserves.remove(index);
        items.remove(index);
        for(int i =0; i< reserves.size();i++){
            reserves.get(i).updatePos(i);
        }
        items.revalidate();
        repaint();
    }
}
