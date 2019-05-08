package View;

import Controller.ServiceController;
import Model.Time;

import javax.swing.*;
import java.awt.*;

public class Service extends JPanel {
    private final ProgressListener listener;
    private ServiceController controller;
    private JLabel time;

    public Service(ProgressListener listener, ServiceController controller) {
        this.listener = listener;
        this.controller = controller;
        this.controller.setService(this);
        windowConfiguration();
    }
    public void windowConfiguration(){
        // We configure the window.
        setLayout(new BorderLayout());
        JTabbedPane clients = new JTabbedPane();
        // instance label of time of the view
        time = new JLabel("HH:MM");
        Time t = new Time(time);
        new Thread(t).start();
    }
}
