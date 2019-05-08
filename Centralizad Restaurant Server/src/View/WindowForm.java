package View;

import Controller.FormController;

import javax.swing.*;
import java.awt.*;

public class WindowForm implements Runnable, ProgressListener {
    // instance variables
    private JFrame frame;
    private FormController controller;

    private Launcher launcher;
    private SideMenu sideMenu;
    private SplitPlane splitPlane;
    private ServiceState serviceState;
    private Aunthentification aunthentification;
    private PreService preService;
    private PostService postService;
    /**
     * Constructor of the main frame.
     * Uses a UIManager.
     * */
    public WindowForm() {
        /* manages the current look and feel, the set of available look and feels */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Interface initialization.
     * This Override method from runnable is executed in parallel on the current Thread.
     * Initialize all JPanel for post use and sets the Launcher as first view
     * */
    @Override
    public void run() {
        //Instructions:
        // These should handle their own component initialization.
        // They should, at least, receive a reference to the listener.

        launcher = new Launcher(this, controller.getLauncherController());
        sideMenu = new SideMenu(this,controller.getSideMenuController());
        serviceState = new ServiceState(this,controller.getServiceStateController());
        aunthentification = new Aunthentification(this,controller.getAunthentificationController());
        preService = new PreService(this,controller.getPreServiceController());
        postService = new PostService(this,controller.getPostServiceController());

        splitPlane = new SplitPlane(sideMenu,launcher);
        frame = new JFrame("Centralized Restaurant Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setContentPane(splitPlane);
        splitPlane.setSplitSize(500,300);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * Override Method from ProgressListener that swings the view to a JPanel to another.
     * @param  window AppState of the program.
     * We end the switch with a return statement because we want to end the function
     * in the moment we set JPanel.
     * */
    @Override
    public void progressFrom(AppState window) {
        switch (window) {
            case LAUNCHER:
                splitPlane.changeCurrentView(launcher);
                frame.pack();
                return;
            case PRESERVICE:
                splitPlane.changeCurrentView(preService);
                frame.pack();
                return;
            case SERVICE:
                return;
            case POSTSERVICE:
                splitPlane.changeCurrentView(postService);
                frame.pack();
                return;
            case SERVICESTATE:
                splitPlane.changeCurrentView(serviceState);
                frame.pack();
                return;
            case AUNTHENTICATION:
                splitPlane.changeCurrentView(aunthentification);
                frame.pack();
                return;
            default:
                System.out.println("Not Existing Window");
                //ToDo: Crear ventana para este error
                return;
        }
    }
    /**
     * We register the main ActionListener.
     * @param c ActionListener of the program.
     */
    public void registerController(FormController c){
        this.controller = c;
    }
    /**
     * Method that causes doRun.run() to be executed asynchronously on the AWT event dispatching thread.
     * This will happen after all pending AWT events have been processed.
     * This method its used because the an application thread needs to update the GUI.
     * */
    public void go() {
        SwingUtilities.invokeLater(this);
    }

}
