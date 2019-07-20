package View.Items;

import Model.Database.Entity.Reserve;
import Model.Database.Entity.Table;
import Model.Database.Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * AuthItem class
 * extends JPanel
 * serves a minimum unit of Authentification
 */
public class AuthItem extends JPanel {
    private Reserve user;

    private JButton accept, negate;
    private JComboBox combo;


    /**
     * Default constructor
     * @param user that ask for auth
     * @param listener that will manages what happens in the view
     * @param pos number of auth
     * @param tables list of tables
     */
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

    /**
     * procedure that updates the pos in the actionListener
     * @param pos int of position
     */
    public void updatePos(int pos){
        negate.setActionCommand(""+pos);
        accept.setActionCommand(""+pos);
    }

    /**
     * getter of user
     * @return User associated to the item
     */
    public User getUser(){
        return user.getUser();
    }

    /**
     * getter of the reserve
     * @return reserve associated to the item
     */
    public Reserve getReserve(){
        return user;
    }

    /**
     * getter table string
     * @return string of table
     */
    public String getTable(){
        String aux = String.valueOf(combo.getSelectedItem());
        return aux;
    }

    /**
     * remove accept button
     */
    public void removeButton(){
        System.out.println("hello remove");
        this.accept.setVisible(false);
    }
}
