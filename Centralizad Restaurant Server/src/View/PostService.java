package View;

import Controller.PostServiceController;
import Model.Database.Entity.Ranking;
import Model.Time;
import View.SubView.CharBar;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PostService extends JPanel {
    private final ProgressListener listener;
    private PostServiceController controller;
    private JLabel time;
    private JTabbedPane options;
    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views.
     * @param controller it's the respective controller of this view.
     */
    public PostService(ProgressListener listener, PostServiceController controller) {
        this.listener = listener;
        this.controller = controller;
        this.controller.setPostService(this);
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
        Time t = new Time(time);
        new Thread(t).start();
        // instance JTabbedPane options that will have JPanels as tabs
        options = new JTabbedPane();
        JPanel top = new JPanel(new FlowLayout());
        top.add(time);
        add(top,BorderLayout.NORTH);
        add(options,BorderLayout.CENTER);

    }
    public void addTab(String name, LinkedList<Ranking> ranking){
        JPanel tab = new CharBar(ranking);
        options.addTab(name,tab);
    }

}
