package View;

import Controller.ServiceController;
import Controller.SubController.ClientOrderController;
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

    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views.
     * @param controller it's the respective controller of this view.
     */


    public Service(ProgressListener listener, ServiceController controller) {
        this.listener = listener;
        this.controller = controller;
        this.controller.setService(this);
        windowConfiguration();
    }

    /**
     * Method that will create all the components of the panel.
     */

    public void windowConfiguration(){
        // We configure the window.
        setLayout(new BorderLayout());
        clients = new JTabbedPane();
        // instance label of time of the view
        time = new JLabel("HH:MM");
        Time t = new Time(time);
        add(clients,BorderLayout.CENTER);
        new Thread(t).start();
        JPanel top = new JPanel(new FlowLayout());
        top.add(time);
        add(top,BorderLayout.NORTH);
    }
    /**
     *This method makes that the different clients are being cumulated.
     * @param user it's a parameter that have all the facts of each client.
     */

    public void addClient(User user){
        //ToDo: be able to log reserve and show clients
        System.out.println("IN SERVICE ADDING");
        ClientOrderController obj = new ClientOrderController(controller);
        controller.addNewController(obj);
        JPanel client = new ClientOrder(user,obj);
        clients.addTab(user.getUser(),client);
        revalidate();
        repaint();
    }
    public void redraw(){
        revalidate();
        repaint();
    }
}
