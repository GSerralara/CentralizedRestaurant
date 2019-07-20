package View.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Menu Item class.
 */
public class MenuItem extends JPanel {
    private JLabel dish;
    private JComboBox quantity;

    /**
     * Default Constructor
     * @param name Name
     * @param listener listener
     * @param quantety Quantity.
     */

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
        JButton order = new JButton("Order");
        order.setActionCommand(name);//the name of the dishes will be primary key
        order.addActionListener(listener);

        information.add(dish,BorderLayout.NORTH);
        form.add(qLabel);
        form.add(quantity);
        information.add(form,BorderLayout.CENTER);
        jpButton.add(order);

        //We set the general layout.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //We add both panels to the general one.
        this.add(information);
        this.add(jpButton);
    }

    /**
     * Getter a dish
     * @return a String
     */
    public String getDish() {
        return dish.getText();
    }

    /**
     * Getter a Quantity
     * @return a integer.
     */
    public int getQuantity() {
        String aux = quantity.getSelectedItem().toString();
        if (!aux.matches("^[0-9]*$")){
            //it does not contains numbers
            return 0;
        }
        return Integer.parseInt(aux);
    }
}
