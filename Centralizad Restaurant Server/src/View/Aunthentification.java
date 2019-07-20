package View;

import Controller.AunthentificationController;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.Table;
import Model.Database.Entity.User;
import View.Items.AuthItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Aunthentification extends JPanel {
    private JPanel items;
    private ArrayList<AuthItem> reserves;
    private final ProgressListener listener;

    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views.
     * @param controller it's the respective controller of this view.
     */

    public Aunthentification(ProgressListener listener,AunthentificationController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        controller.setAunthentification(this);
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
    public void addItem(Reserve user, LinkedList<Table> list, AunthentificationController controller){

        //AuthItem item2 = new AuthItem(user);
        //item2.prepareUI(controller,reserves.size());
        AuthItem item = new AuthItem(user,controller,reserves.size(),list);
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
     * redraws the view
     */
    public void redraw(){
        items.revalidate();
        repaint();
    }

    /**
     * This method remove the user in the different list.
     * @param user it's a parameter that have all information about the User.
     */
    public void dropUser(User user){
        System.out.println("Items number "+ reserves.size());
        for(int i=0;i<reserves.size();i++){
            System.out.println("dropping funciton");
            if(reserves.get(i).getUser().getUser().equals(user.getUser())){
                reserves.remove(i);
                items.remove(i);
            }
        }
        for(int i =0; i< reserves.size();i++){
            reserves.get(i).updatePos(i);
        }
        items.revalidate();
        repaint();
    }

    /**
     * remove
     */
    public void removeAccept(int index){
        System.out.println(index);
        System.out.println(reserves.size());
        reserves.get(index).removeButton();
    }
    /**
     *
     * @param pos it's parameter that used to search for the product name inside the array and remove it.
     * @return this method return a the user exactly that you want.
     */

    public Reserve getReserve(String pos){
        String num ="";
        for(int i =2; i< pos.length();i++){
            num += ""+pos.charAt(i);
        }
        int index = Integer.parseInt(num);
        return reserves.get(index).getReserve();
    }

    /**
     * This function gives you the id of the table.
     * @param pos it's a variable that parses the id
     * @return returns the number
     */

    public int getTable(String pos){
        String num ="";
        for(int i =2; i< pos.length();i++){
            num += ""+pos.charAt(i);
        }
        int index = Integer.parseInt(num);
        String table =  reserves.get(index).getTable();
        num ="";
        for(int i = 5; i< table.length() && table.charAt(i) != ' ' ;i++){
            num += ""+table.charAt(i);
        }
        int id = Integer.parseInt(num);
        return id;
    }

    /**
     * This function checks if the user exists inside the queue.
     * @param u it's a variable that contains user information.
     * @return returns true or false.
     */
    public boolean userIsOnWainting(User u){
        for(int i=0;i<reserves.size();i++){
            if(reserves.get(i).getUser()==u){
                return true;
            }
        }
        return false;
    }
}
