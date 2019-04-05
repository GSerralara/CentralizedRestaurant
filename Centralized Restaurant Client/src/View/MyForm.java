package View;
import Controller.FormController;

import java.awt.*;
import javax.swing.*;
/**
 * JFrame that will be the main window of the GUI.
 *
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 * @version 1.3
 * @since 1.0
 */
public class MyForm implements Runnable, ProgressListener {
    // instance variables
    private JFrame frame;
    private FormController controller;
    /*
    clases of views
     */
    private Launcher launcher;
    private Register register;
    private Main main;
    private MainTable mainTable;
    /**
     * Constructor of the main frame.
     * Uses a UIManager.
     * */
    public MyForm() {
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
        register = new Register(this, controller.getRegisterController());
        main = new Main(this,controller.getMainController());
        mainTable = new MainTable(this,controller.getMainTableController());

        frame = new JFrame("Centralized Restaurant");
        ImageIcon img = new ImageIcon("images/Logo.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setContentPane(launcher);
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
                frame.setContentPane(launcher);
                frame.pack();
                return;

            case REGISTER:
                frame.setContentPane(register);
                frame.pack();
                return;
            case MAIN:
                frame.setContentPane(main);
                frame.pack();
                return;
            case MAINTABLE:
                frame.setContentPane(mainTable);
                frame.pack();
                frame.setSize(new Dimension(300,250));
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
