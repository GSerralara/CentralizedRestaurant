package View;

import Controller.LauncherController;
import Resources.Pop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View Launcher class
 * Extends from JPanel
 *
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 */


public class Launcher extends JPanel{
    // instance variables
    private final ProgressListener listener;

    /**
     * Constants for the Buttons and Action Commands
     */
    // Constants of the buttons
    private static final String JB_SINGIN = "Sing In";
    private static final String JB_REGISTER = "Register now";

    // Constants of the Action Commands
    private static final String AC_REGISTER = "REGISTER";
    private static final String AC_SIGNIN= "SIGN_IN";
    /**
     * Constants for the UI
     */
    // Name of the view
    private static final String WINDOW_TITLE = "SING IN";

    // General constants
    private static final String USER_LABEL_TEXT = "Username";
    private static final String PW_LABEL_TEXT = "Password";
    private static final String QUESTION_UX_TEXT = "Not a memeber yet?";
    /**
     * Attributes of the UI
     */

    private JPasswordField pw;
    private JTextField username;
    private JButton signIn,register;
    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    Launcher(ProgressListener listener,LauncherController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        controller.setLauncher(this);
        // UI configuration of the panel
        windowConfiguration();
        registerController(controller);
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We configure the window.
        new BorderLayout();
        // We instance the title of the window
        JLabel title = new JLabel(WINDOW_TITLE);
        // We instance the panel body of the window
        JPanel content = new JPanel(new BorderLayout());
        // We create the different parts of the window.
        content.add(title,BorderLayout.NORTH);
        content.add(bottomPart(),BorderLayout.SOUTH);
        content.add(mainPart(),BorderLayout.CENTER);
        // We add the content to the window.
        add(content,BorderLayout.CENTER);
    }
    /**
     * Function that will return a JPanel.
     * It's responsible for creating the components of the center of the Layout.
     * @return panel in the center
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
        // instance attributes
        username = new JTextField();
        pw = new JPasswordField();
        // add user camp to the from
        form.add(userLabel);
        form.add(username);
        // add password camp to the fom
        form.add(pwLabel);
        form.add(pw);
        // add Sing In button to the bottom
        signIn = new JButton(JB_SINGIN);
        bottom.add(signIn,FlowLayout.LEFT);//set Flow position for UX purposes
        // add secondary panels (from & bottom) to the main panel
        main.add(form,BorderLayout.CENTER);
        main.add(bottom,BorderLayout.SOUTH);
        // return Statement
        return main;
    }
    /**
     * Function that will return a JPanel.
     * It's responsible for creating the components of the bottom of the Layout.
     * @return panel at the bottom
     */
    private JPanel bottomPart(){
        // instance JPanel that will be return
        JPanel bottom = new JPanel(new FlowLayout());//For UX positioning will have a FlowLayout
        // Main question
        JLabel question = new JLabel(QUESTION_UX_TEXT);
        bottom.add(question,FlowLayout.LEFT);
        // Register option Button
        register = new JButton(JB_REGISTER);
        register.setBorderPainted(false);
        register.setForeground(Color.BLUE);

        bottom.add(register,FlowLayout.CENTER);//set Flow position for UX purposes
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
            case "Login":
                this.listener.progressFrom(ProgressListener.AppState.MENU);
                break;
            case "Reserve":
                this.listener.progressFrom(ProgressListener.AppState.MAINTABLE);
                break;
            case "REGISTER":
                this.listener.progressFrom(ProgressListener.AppState.REGISTER);
                break;
            default:
                System.err.println("Unknown window name Launcher");
                break;
        }
    }
    /**
     * Function that will return the String of the username TextField.
     */
    public String getUserField(){
        // return Statement
        return username.getText();
    }
    /**
     * Function that will return the String of the pw PasswordField.
     */
    public String getPwField(){

        // return Statement
        return new String(pw.getPassword());
    }
    /**
     * Function that will return:
     *  @return True: in case all fields are filled or False:in case not all fields are filled
     */
    public boolean allFieldsFilled(){
        if(username.getText().length() != 0 && pw.getPassword().length != 0){
            return true;
        }
        Pop popup = new Pop("All fields must be filled");
        return false;
    }
    /**
     * Register Controller
     * @param e ActionListener.
     */
    private void registerController(ActionListener e){
        // controller command
        register.setActionCommand(AC_REGISTER);//set action command that will get the ActionListener
        register.addActionListener(e);//set which ActionListener
        signIn.setActionCommand(AC_SIGNIN);//set action command that will get the ActionListener
        signIn.addActionListener(e);//set which ActionListener
    }
}
