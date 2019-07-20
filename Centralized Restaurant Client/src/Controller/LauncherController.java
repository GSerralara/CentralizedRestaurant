package Controller;

import Model.Database.Entity.User;
import View.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * LauncherController class
 * Implements ActionListener
 * It's function is to manage what happens in the Register view
 *
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 */
public class LauncherController implements ActionListener {

    // attributes of the class
    private Launcher launcher;
    private FormController listener;
    //indicates if it goes to the MainMenu(false) or MainTable(true)
    private String logType;
    /**
     * Constructor by default of the class.
     * @param listener it's the controller father that manages all petitions
     * */
    LauncherController(FormController listener) {
        this.listener = listener;
        logType = new String();
    }
    /**
     * Setter that sets the view that will retrieve data from.
     * @param launcher the view corresponding to this controller
     * */
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }
    /**
     * Override Method from ActionListener that activates when a Swing element,
     * with this class as an ActionListener, is interacted with.
     * @param e ActionEvent that will get the method.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Validate login, or whatever.
        // All went well, notify listener to progress.
        switch (e.getActionCommand()){
            case "SIGN_IN":
                //first we check that all camps are filled
                if(launcher.allFieldsFilled()){
                    //after that we try to connect to the server
                    if(listener.tryConnection()){
                        System.out.println(MD5(launcher.getPwField()));
                        User user = new User(launcher.getUserField(),
                                MD5(launcher.getPwField()));
                        listener.login(user);
                        listener.startSession(user);
                        launcher.goToWindow(logType);
                    }
                }
                break;
            case "REGISTER":
                launcher.goToWindow("REGISTER");
                break;
            default:
                System.err.println("Unknown Button Launcher");
                break;
        }
    }

    /**
     * setter type log
     * @param state string
     */
    void setLogType(String state){
        this.logType = state;
    }

    /**
     * encrypt md5
     * @param md5 string
     * @return string
     */
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
