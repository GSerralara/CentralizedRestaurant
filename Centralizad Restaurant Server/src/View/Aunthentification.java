package View;

import Controller.AunthentificationController;
import Model.Database.Entity.User;
import View.Items.AuthItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Aunthentification extends JPanel {
    private JPanel items;
    private ArrayList<AuthItem> reserves;
    private AunthentificationController controller;
    private final ProgressListener listener;

    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views.
     * @param controller it's the respective controller of this view.
     */

    public Aunthentification(ProgressListener listener,AunthentificationController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        this.controller = controller;
        this.controller.setAunthentification(this);
        // UI configuration of the panel
        windowConfiguration();
    }

    /**
     * This method makes a new JScroll Panel.
     * Method that will create all the components of the panel.
     */

    public void windowConfiguration(){
        setLayout(new BorderLayout());
        items = new JPanel();
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        //JScrollPane dishesList = new JScrollPane(list);
        JScrollPane list = new JScrollPane(items);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(list, BorderLayout.CENTER);
        init();
    }

    /**
     * This method initializes all reservation data.
     */
    public void init(){
        reserves = new ArrayList<>();
        items.removeAll();
        items.revalidate();
        repaint();
    }
    /**
     *
     *This method what it does is to add the different Users.
     *
     * @param user it's a parameter that have all information about the User.
     */
    public void addItem(User user){

        //AuthItem item2 = new AuthItem(user);
        //item2.prepareUI(controller,reserves.size());
        AuthItem item = new AuthItem(user,controller,reserves.size());
        this.reserves.add(item);
        this.items.add(reserves.get(reserves.size()-1));
        items.revalidate();
        repaint();
    }
    /**
     *
     * @param pos it's parameter that used to search for the product name inside the array and remove it.
     */
    public void cancelItem(String pos){
        String num ="";
        for(int i =2; i< pos.length();i++){
           num += ""+pos.charAt(i);
        }
        int index = Integer.parseInt(num);
        reserves.remove(index);
        items.remove(index);
        for(int i =0; i< reserves.size();i++){
            reserves.get(i).updatePos(i);
        }
        items.revalidate();
        repaint();
    }

    /**
     * This method remove the user in the different list.
     * @param user it's a parameter that have all information about the User.
     */
    public void dropUser(User user){
        for(int i=0;i<reserves.size();i++){
            if(reserves.get(i).getUser()==user){
                reserves.remove(i);
                items.remove(i);
            }

        }
        for(int i =0; i< reserves.size();i++){
            reserves.get(i).updatePos(i);
        }
        items.revalidate();
        repaint();
    } /**
     *
     * @param pos it's parameter that used to search for the product name inside the array and remove it.
     * @return this method return a the user exactly that you want.
     */

    public User getUser(String pos){
        String num ="";
        for(int i =2; i< pos.length();i++){
            num += ""+pos.charAt(i);
        }
        int index = Integer.parseInt(num);
        return reserves.get(index).getUser();
    }

    public boolean userIsOnWainting(User u){
        for(int i=0;i<reserves.size();i++){
            if(reserves.get(i).getUser()==u){
                return true;
            }
        }
        return false;
    }
}
