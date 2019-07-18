package View.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Time;

public class MenuItem extends JPanel {
    private JLabel fieldTime,fieldPrice,fieldUnits,fieldName;
    private JButton cancel;
    public MenuItem(String time, float price, int units, String name, int pos, ActionListener listener) {
        super();
        System.out.println(name);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel up = new JPanel(new FlowLayout());
        JPanel middle = new JPanel(new FlowLayout());
        JPanel down = new JPanel(new FlowLayout());

        fieldName = new JLabel(name);
        fieldTime = new JLabel(time);

        up.add(fieldName);
        up.add(fieldTime);
        add(up);

        fieldPrice = new JLabel(price+"€");
        fieldUnits = new JLabel("Available: "+units);
        middle.add(fieldUnits);
        middle.add(fieldPrice);
        add(middle);

        cancel = new JButton("Cancel");
        cancel.setActionCommand(""+pos);
        cancel.addActionListener(listener);
        down.add(cancel);
        add(down);
    }
    public void updatePos(int pos){
        this.cancel.setActionCommand(""+pos);
    }
}
