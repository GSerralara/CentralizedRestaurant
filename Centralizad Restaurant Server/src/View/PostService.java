package View;

import Controller.PostServiceController;
import Model.Time;
import View.SubView.CharBar;

import javax.swing.*;
import java.awt.*;

public class PostService extends JPanel {
    private final ProgressListener listener;
    private PostServiceController controller;
    private JLabel time;
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
        JTabbedPane options = new JTabbedPane();
        JPanel top = new JPanel(new FlowLayout());
        top.add(time);
        add(top,BorderLayout.NORTH);

        options.addTab("From Service", fromService());
        options.addTab("Since Inauguration", sinceInaguration());
        add(options,BorderLayout.CENTER);

    }
    /**
     * This method it's about from Service.
     * @return a JPanel with the diffrents options that user will can choose.
     */
    private JPanel fromService(){
        JPanel fromService = new JPanel(new BorderLayout());
        JTabbedPane options = new JTabbedPane();
        JPanel jp1 = new CharBar();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();

        options.addTab("Top 5 Dishes", jp1);
        options.addTab("Benefits", jp2);
        options.addTab("Average profit x Table", jp3);
        options.addTab("Average dishes x Table", jp4);

        fromService.add(options,BorderLayout.CENTER);
        return fromService;
    }
    /**
     * This method it's about since Inaguration
     * @return a JPanel with the diffrents options that user will can choose.
     */
    private JPanel sinceInaguration(){
        JPanel fromService = new JPanel(new BorderLayout());
        JTabbedPane options = new JTabbedPane();
        JPanel jp1 = new CharBar();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();

        options.addTab("Top 5 Dishes", jp1);
        options.addTab("Benefits", jp2);
        options.addTab("Average profit x Table", jp3);
        options.addTab("Average dishes x Table", jp4);

        fromService.add(options,BorderLayout.CENTER);
        return fromService;
    }
}
