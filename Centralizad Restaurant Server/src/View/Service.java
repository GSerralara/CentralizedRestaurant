package View;

import Controller.ServiceController;

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
    }
}
