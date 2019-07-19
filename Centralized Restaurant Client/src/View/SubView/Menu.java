package View.SubView;

import Controller.SubController.MenuController;
import View.Items.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * View Menu Tab class
 * Extends from JPanel
 *
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 * @version 1.3
 * @since 1.3
 */
public class Menu extends JPanel {
    private JPanel menuItems;
    private ArrayList<View.Items.MenuItem> menu;


    public Menu(MenuController controller) {

        controller.setMenu(this);
        //list = new JList<MenuItem>();
        setLayout(new BorderLayout());
        menuItems = new JPanel();
        menuItems.setLayout(new BoxLayout(menuItems, BoxLayout.Y_AXIS));
        //JScrollPane dishesList = new JScrollPane(list);
        JScrollPane dishesList = new JScrollPane(menuItems);
        dishesList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(dishesList, BorderLayout.CENTER);
    }
    public void init(){
        menu = new ArrayList<>();
        menuItems.removeAll();
        //add data

        menuItems.revalidate();
        repaint();
    }
    public void addItem(String name, int q,MenuController controller){
        this.menu.add(new MenuItem(name,controller,q));
        this.menuItems.add(menu.get(menu.size()-1));
        menuItems.revalidate();
        repaint();
    }
    public int getCuantity(String name){
        for(MenuItem i: menu){
            if(i.getDish().equals(name)){
                return i.getQuantity();
            }
        }
        return 0;
    }

}
