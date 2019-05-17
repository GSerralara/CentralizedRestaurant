package View.Items;

import Model.Database.Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AuthItem extends JPanel {
    private User user;
    private String reserve;
    private JButton accept, negate;


    public AuthItem(User user, ActionListener listener, int pos) {
        System.out.println("Auth item");
        this.user = user;
        this.reserve = user.getReserve();
        accept = new JButton("Accept");
        negate = new JButton("Negate");
        accept.setActionCommand("A:"+pos);
        negate.setActionCommand("N:"+pos);
        accept.addActionListener(listener);
        negate.addActionListener(listener);
        setLayout(new FlowLayout());
        JLabel reserveName = new JLabel(reserve);
        add(accept);
        add(reserveName);
        add(negate);
    }
    public void updatePos(int pos){
        negate.setActionCommand(""+pos);
        accept.setActionCommand(""+pos);
    }

    public User getUser() {
        return user;
    }
}
