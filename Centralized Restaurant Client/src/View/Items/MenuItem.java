package View.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuItem extends JPanel {
    private JLabel dish;
    private JButton order;
    private JComboBox quantity;

    public MenuItem(String name, ActionListener listener, int quantety) {
        //We create the JPanel.
        super();

        JPanel information = new JPanel(new BorderLayout());
        JPanel jpButton = new JPanel(new FlowLayout());
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form,BoxLayout.X_AXIS));
        dish = new JLabel(name);
        dish.setHorizontalAlignment(JLabel.CENTER);
        JLabel qLabel = new JLabel("Quantity: ");
        quantity = new JComboBox();
        for(int i=1; i<=quantety;i++){
            quantity.addItem(i);
        }
        order = new JButton("Order");
        order.setActionCommand(name);//the name of the dishes will be primary key
        order.addActionListener(listener);

        information.add(dish,BorderLayout.NORTH);
        form.add(qLabel);
        form.add(quantity);
        information.add(form,BorderLayout.CENTER);
        //information.add(quantity,BorderLayout.EAST);

        jpButton.add(order);

        //We set the general layout.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //We add both panels to the general one.
        this.add(information);
        this.add(jpButton);
    }

    public String getDish() {
        return dish.getText();
    }

    public int getQuantity() {
        String aux = quantity.getSelectedItem().toString();
        if (!aux.matches("^[0-9]*$")){
            //it does not contains numbers
            return 0;
            //print error
        }
        return Integer.parseInt(aux);
    }
}
