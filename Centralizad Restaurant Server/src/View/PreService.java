package View;

import Controller.PreServiceController;
import View.SubView.Carte;
import View.SubView.TableMangement;
import View.SubView.Menu;
import Model.Time;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;




public class PreService extends JPanel {
    private final ProgressListener listener;
    private PreServiceController controller;
    private JLabel time;
    private TableMangement tableMangement;
    private Menu menu;
    private Carte carte;

    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views.
     * @param controller it's the respective controller of this view.
     */

    public PreService(ProgressListener listener, PreServiceController controller) {
        this.listener = listener;
        this.controller = controller;
        this.controller.setPreService(this);
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We configure the window.
        setLayout(new BorderLayout());
        // instance label of time of the view
        time = new JLabel("HH:MM");
        Time t =  new Time(time);
        new Thread(t).start();
        // instance JTabbedPane options that will have JPanels as tabs
        JTabbedPane options = new JTabbedPane();
        //JPanel jp1 = new JPanel();
        tableMangement = new TableMangement(controller.getSubController_1());
        menu = new Menu(controller.getSubController_2());
        carte = new Carte(controller.getSubController_3());
        JPanel top = new JPanel(new FlowLayout());
        top.add(time);
        add(top,BorderLayout.NORTH);

        options.addTab("Tables Management", tableMangement);
        options.addTab("Menu", menu);
        options.addTab("Carte",carte);
        add(options,BorderLayout.CENTER);

    }
}
