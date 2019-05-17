package View;

import Controller.ServiceController;
import Model.Database.Entity.User;
import Model.Time;
import View.SubView.ClientOrder;

import javax.swing.*;
import java.awt.*;

public class Service extends JPanel {
    private final ProgressListener listener;
    private ServiceController controller;
    private JLabel time;
    private JTabbedPane clients;

    public Service(ProgressListener listener, ServiceController controller) {
        this.listener = listener;
        this.controller = controller;
        this.controller.setService(this);
        windowConfiguration();
    }

    public void windowConfiguration(){
        // We configure the window.
        setLayout(new BorderLayout());
        clients = new JTabbedPane();
        // instance label of time of the view
        time = new JLabel("HH:MM");
        Time t = new Time(time);
        new Thread(t).start();
    }

    public void addClient(User user){
        JPanel client = new ClientOrder(user,controller.getNewClientController());
        clients.addTab(user.getUser(),client);
    }
}
