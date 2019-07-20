package View.SubView;

import Controller.SubController.CarteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Carte class
 * extends JPanel
 * serves to admin the carte selection
 */
public class Carte extends JPanel {
    private JButton create, load;
    private JComboBox cartes;
    private JTextField carteName;

    /**
     * This function shows the the menu of dishes.
     * @param controller it's the respective controller of this view.
     */
    public Carte(CarteController controller) {
        controller.setCarte(this);
        JPanel content =  new JPanel(new GridLayout(2,3,30,80));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        create = new JButton("Create");
        JLabel saveLavel = new JLabel("Introduce a name");
        carteName = new JTextField();
        content.add(saveLavel);
        content.add(carteName);
        content.add(create);
        JLabel chooseLavel = new JLabel("Choose a Carte");
        load = new JButton("Load");
        cartes = new JComboBox();
        System.out.println(getCarteListSize());
        content.add(chooseLavel);
        content.add(cartes);
        content.add(load);
        add(Box.createVerticalStrut(40));
        add(content);
        add(Box.createVerticalStrut(40));
        registerController(controller);
        init();
    }

    /**
     * Function that starts the view.
     */
    public void init(){
        if(getCarteListSize() !=0){
            cartes.removeAllItems();
        }

    }

    /**
     * In this function we will create the setActionCommand and the addActionListener.
     * @param listener it's a variable that contains the ActionListener.
     */
    private void registerController(ActionListener listener){
        load.setActionCommand("LOAD");
        create.setActionCommand("SAVE");
        load.addActionListener(listener);
        create.addActionListener(listener);
    }

    /**
     * This function See that in that field there is a name.
     * @return true or false.
     */
    public boolean isNameIntroduced(){
        return !carteName.getText().equals("");
    }

    /**
     * getter of the list of cartes
     * @return the size of the menu dishes.
     */
    public int getCarteListSize(){
        return cartes.getItemCount();
    }

    /**
     * getter of the carte name
     * @return the name of this dish.
     */
    public String getCarteName(){
        return carteName.getText();
    }

    /**
     *
     * @param name it's a parameter that have a one string
     */
    public void addCarteOption(String name){
        cartes.addItem(name);
    }

    /**
     * getter of the string of the menu in the ComboBox
     * @return string of the menu
     */
    public String getSelectedMenu(){
        return cartes.getSelectedItem().toString();
    }
}
