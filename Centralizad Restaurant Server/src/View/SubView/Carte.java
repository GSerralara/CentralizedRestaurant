package View.SubView;

import Controller.SubController.CarteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Carte extends JPanel {
    private JButton create, load;
    private JComboBox cartes;
    private JTextField carteName;

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
    public void init(){
        if(getCarteListSize() !=0){
            cartes.removeAllItems();
        }

    }
    private void registerController(ActionListener listener){
        load.setActionCommand("LOAD");
        create.setActionCommand("SAVE");
        load.addActionListener(listener);
        create.addActionListener(listener);
    }
    public boolean isNameIntroduced(){
        return !carteName.getText().equals("");
    }
    public int getCarteListSize(){
        return cartes.getItemCount();
    }
    public String getCarteName(){
        return carteName.getText();
    }
    public void addCarteOption(String name){
        cartes.addItem(name);
    }
    public String getSelectedMenu(){
        return cartes.getSelectedItem().toString();
    }
}
