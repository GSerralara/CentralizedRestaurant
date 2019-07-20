package View.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TableItem Class extends JPanel.
 * This class instance table objects in this view.
 */
public class TableItem extends JPanel {
    private JButton cancel;
    private JLabel tableName, numMaxCustomers;

    /**
     * This is the function that generate the constructor.
     * @param listener it's a ProgressListener that the class will use to move to other views.
     * @param pos it's a integer that save the position.
     * @param maxCustomers is a parameter that indicates the limit of clients.
     */
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

    /**
     * procedure that updates de actionComanads for the controller
     * @param pos it's a integer that save the position.
     */
    public void updatePos(int pos){
        this.cancel.setActionCommand(""+pos);
        this.tableName.setText("Table"+(pos+1));
    }

}
