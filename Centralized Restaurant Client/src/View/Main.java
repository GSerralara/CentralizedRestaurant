package View;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    private final ProgressListener listener;
    private MainController controller;
    private JLabel time;

    public Main(ProgressListener listener, MainController controller) {
        this.listener = listener;
        this.controller = controller;
        this.controller.setMain(this);
        windowConfiguration();
    }
    private void windowConfiguration(){
        setLayout(new BorderLayout());
        time = new JLabel("HH:MM");

        JTabbedPane options = new JTabbedPane();
        //JPanel content = new JPanel(new BorderLayout());
        JPanel top = new JPanel(new FlowLayout());
        top.add(time);
        add(top,BorderLayout.NORTH);

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JLabel label1 = new JLabel();
        label1.setText("You are in area of Tab1");
        JLabel label2 = new JLabel();
        label2.setText("You are in area of Tab2");
        jp1.add(label1);
        jp2.add(label2);
        options.addTab("Tab1", jp1);
        options.addTab("Tab2", jp2);
        add(options,BorderLayout.CENTER);

    }
    public void goToWindow(String windowName){
        switch (windowName){

            default:
                System.err.println("Unknown window name");
                break;
        }
    }
}
