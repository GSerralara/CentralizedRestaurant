package View.Items;

import Model.DataBase.Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AuthItem extends JPanel {
    private User user;
    private String reserve;
    private JButton accept, negate;
    public AuthItem(User user, String reserve, ActionListener listener, int pos) {
        this.user = user;
        this.reserve = reserve;
        accept = new JButton("Accept");
        negate = new JButton("Negate");
        accept.setActionCommand(""+pos);
        negate.setActionCommand(""+pos);
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
}
