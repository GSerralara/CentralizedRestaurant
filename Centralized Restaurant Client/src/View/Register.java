package View;

import Controller.RegisterController;

import javax.swing.*;
import java.awt.*;
//ToDO: Hacer que quede bonito la view
//ToDo: Funciones que den la informcion de los campos
/**
 * View Register class
 * Extends from JPanel
 *
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 * @version 1.3
 * @since 1.3
 */
public class Register extends JPanel{
    // instance variables
    private final ProgressListener listener;
    private RegisterController controller;

    /**
     * Constants for the Buttons and Action Commands
     */
    // Constants of the buttons
    public static final String JB_SINGUP = "SIGN UP";
    public static final String JB_LOGIN = "Login here";

    // Constants of the Action Commands
    public static final String AC_REGISTER = "REGISTER";
    public static final String AC_LOGIN = "SIGN_IN";

    /**
     * Constants for the UI
     */
    // Name of the view
    private static final String WINDOW_TITLE = "SING UP";

    // General constants
    private static final String USER_LABEL_TEXT = "Username";
    private static final String PW_LABEL_TEXT = "Password";
    private static final String RPW_LABEL_TEXT = "Repeat Password";
    private static final String MAIL_LABEL_TEXT = "E-Mail";
    private static final String QUESTION_UX_TEXT = "Already have an account?";

    /**
     * Attributes of the UI
     */
    private JPasswordField pw;
    private JPasswordField rpw;
    private JTextField username,mail;
    private JButton signUp, logIn;
    private JLabel title;

    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    public Register(ProgressListener listener, RegisterController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        this.controller = controller;
        this.controller.setRegister(this);
        // UI configuration of the panel
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We instance the title of the window
        title = new JLabel(WINDOW_TITLE);
        // We instance the panel body of the window
        JPanel content = new JPanel(new BorderLayout());
        // We create the different parts of the window.
        content.add(title,BorderLayout.NORTH);
        content.add(bottomPart(),BorderLayout.SOUTH);
        content.add(mainPart(),BorderLayout.CENTER);
        // We configure the window.
        new BorderLayout();
        // We add the content to the window.
        add(content,BorderLayout.CENTER);
    }
    /**
     * Function that will return a JPanel.
     * It's responsible for creating the components of the center of the Layout.
     */
    private JPanel mainPart(){
        // instance JPanel that will be return
        JPanel main = new JPanel(new BorderLayout());
        // instance JPanels that will from the main panel
        JPanel form = new JPanel();
        JPanel bottom = new JPanel(new FlowLayout());//For UX positioning will have a FlowLayout
        // set Layout to a box Form for the content
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        // instance labels of the view
        JLabel userLabel = new JLabel(USER_LABEL_TEXT);
        JLabel pwLabel = new JLabel(PW_LABEL_TEXT);
        JLabel rpwLabel = new JLabel(RPW_LABEL_TEXT);
        JLabel mailLabel = new JLabel(MAIL_LABEL_TEXT);
        // instance attributes
        mail = new JTextField();
        rpw = new JPasswordField();
        pw = new JPasswordField();
        username = new JTextField();
        // add user camp to the from
        form.add(userLabel);
        form.add(username);
        // add mal camp to the form
        form.add(mailLabel);
        form.add(mail);
        // add password camp to the fom
        form.add(pwLabel);
        form.add(pw);
        // add repeat password camp to the form
        form.add(rpwLabel);
        form.add(rpw);
        // add Sing Up button to the bottom
        signUp = new JButton(JB_SINGUP);
        signUp.setActionCommand(AC_REGISTER);//set action command that will get the ActionListener
        signUp.addActionListener(controller);//set which ActionListener
        bottom.add(signUp,FlowLayout.LEFT);//set Flow position for UX purposes
        // add secondary panels (from & bottom) to the main panel
        main.add(form,BorderLayout.CENTER);
        main.add(bottom,BorderLayout.SOUTH);
        // return Statement
        return main;
    }
    /**
     * Function that will return a JPanel.
     * It's responsible for creating the components of the bottom of the Layout.
     */
    private JPanel bottomPart(){
        // instance JPanel that will be return
        JPanel bottom = new JPanel(new FlowLayout());//For UX positioning will have a FlowLayout

        //Main question
        JLabel question = new JLabel(QUESTION_UX_TEXT);
        bottom.add(question,FlowLayout.LEFT);

        //LogIn option Button
        logIn = new JButton(JB_LOGIN);
        logIn.setBorderPainted(false);
        logIn.setForeground(Color.BLUE);
        //controller command
        logIn.setActionCommand(AC_LOGIN);//set action command that will get the ActionListener
        logIn.addActionListener(controller);//set which ActionListener
        bottom.add(logIn,FlowLayout.CENTER);//set Flow position for UX purposes
        // return Statement
        return bottom;
    }
    /**
     * Method that will create all the components of the panel.
     * @param windowName that indicate which window will target and change into.
     */
    public void goToWindow(String windowName){
        // List of available views from this one
        switch (windowName){
            case "REGISTER":
                this.listener.progressFrom(ProgressListener.AppState.LAUNCHER);
                break;
            case "SIGN_IN":
                this.listener.progressFrom(ProgressListener.AppState.LAUNCHER);
                break;
            default://In case it doesn't exist
                System.err.println("Unknown window name Register");
                break;
        }
    }

    public String getPwd(){
        // instance String that will be return
        String pwd = new String(pw.getPassword());
        // return Statement
        return pwd;
    }

    public boolean samePwd(){
        String firstPwd = new String(pw.getPassword());
        String secondPwd = new String(rpw.getPassword());
        return firstPwd.equals(secondPwd);
    }
}