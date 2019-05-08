package View.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TableItem extends JPanel {
    private JButton cancel;
    private JLabel tableName, numMaxCustomers;
    public TableItem(ActionListener listener, int pos, int maxCustomers) {
        super();
        tableName = new JLabel("Table"+(pos+1));
        numMaxCustomers = new JLabel("x"+maxCustomers);
        cancel = new JButton("Remove");
        cancel.setActionCommand(""+pos);
        cancel.addActionListener(listener);
        JPanel form = new JPanel(new FlowLayout());
        form.add(tableName,FlowLayout.LEFT);
        form.add(numMaxCustomers,FlowLayout.CENTER);
        form.add(cancel,FlowLayout.RIGHT);
        add(form);
    }
    public void updatePos(int pos){
        this.cancel.setActionCommand(""+pos);
        this.tableName.setText("Table"+(pos+1));
    }

}
