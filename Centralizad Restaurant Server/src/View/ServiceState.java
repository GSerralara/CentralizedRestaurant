package View;

import Controller.ServiceStateController;

import javax.swing.*;
import java.awt.*;

public class ServiceState extends JPanel {
    // instance variables
    private final ProgressListener listener;
    private ServiceStateController controller;
    private JButton init;
    private JButton stop;
    private JButton halt;
    JLabel currentState;
    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    public ServiceState(ProgressListener listener,ServiceStateController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        this.controller = controller;
        this.controller.setServiceState(this);
        // UI configuration of the panel
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        new BorderLayout();
        currentState = new JLabel("Now is shutted Down the server");
        JLabel title = new JLabel("Service State");
        JPanel listScrollPane = new JPanel();
        listScrollPane.setLayout(new BoxLayout(listScrollPane,BoxLayout.Y_AXIS));
        init = new JButton("Init Service");
        init.setActionCommand("INIT");
        init.setForeground(Color.GREEN);
        init.addActionListener(controller);
        stop = new JButton("Stop Service");
        stop.setActionCommand("STOP");
        stop.setForeground(Color.ORANGE);
        stop.addActionListener(controller);
        halt = new JButton("End Service");
        halt.setActionCommand("END");
        halt.setForeground(Color.RED);
        halt.addActionListener(controller);
        listScrollPane.add(title);
        listScrollPane.add(Box.createVerticalStrut(20));
        listScrollPane.add(init);
        listScrollPane.add(Box.createVerticalStrut(20));
        listScrollPane.add(stop);
        listScrollPane.add(Box.createVerticalStrut(20));
        listScrollPane.add(halt);
        listScrollPane.add(Box.createVerticalStrut(20));
        listScrollPane.add(currentState);
        add(listScrollPane,BorderLayout.CENTER);
    }
    public void changeCurrentState(String state){
        currentState.setText(state);
    }
    public String getCurrentState(){
        return currentState.getText();
    }

}
