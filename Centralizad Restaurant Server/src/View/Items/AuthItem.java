package View.Items;

import Model.Database.Entity.Reserve;
import Model.Database.Entity.Table;
import Model.Database.Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AuthItem extends JPanel {
    private Reserve user;

    private JButton accept, negate;
    private JComboBox combo;



    public AuthItem(Reserve user, ActionListener listener, int pos, LinkedList<Table> tables) {
        System.out.println("Auth item");
        this.user = user;
        combo = new JComboBox();
        for(Table i:tables){
            combo.addItem("Mesa "+i.getIdTable()+" ("+i.getNumberClients()+")");
        }
        accept = new JButton("Accept");
        negate = new JButton("Negate");
        accept.setActionCommand("A:"+pos);
        negate.setActionCommand("N:"+pos);
        accept.addActionListener(listener);
        negate.addActionListener(listener);
        setLayout(new FlowLayout());
        JLabel reserveName = new JLabel(user.getReserveName()+" Nº"+user.getBookNumber());
        add(accept);
        add(reserveName);
        add(combo);
        add(negate);
    }
    public void updatePos(int pos){
        negate.setActionCommand(""+pos);
        accept.setActionCommand(""+pos);
    }
    public User getUser(){
        return user.getUser();
    }
    public Reserve getReserve(){
        return user;
    }
    public String getTable(){
        String aux = String.valueOf(combo.getSelectedItem());
        return aux;
    }
}
