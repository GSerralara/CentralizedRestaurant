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
    private MenuController controller;

    public Menu(MenuController controller) {
        this.controller = controller;
        this.controller.setMenu(this);
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
        addItem("Chicken Poke");
        addItem("Pancake");
        addItem("Soho Mule");
        addItem("burger");
        addItem("risotto");
        addItem("steamed buns");
        addItem("hot dog");
        addItem("fried potatoes");
        menuItems.revalidate();
        repaint();
    }
    public void addItem(String name){
        this.menu.add(new MenuItem(name,controller));
        //this.list.add(menu.get(menu.size()-1));
        this.menuItems.add(menu.get(menu.size()-1));
    }
    public int getCuantity(String name){
        for(int i=0;i<menu.size();i++){
            if(menu.get(i).getDish()==name){
                return menu.get(i).getQuantity();
            }
        }
        return 0;
    }
}
