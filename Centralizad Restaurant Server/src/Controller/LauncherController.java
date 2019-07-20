package Controller;

import Model.Database.Entity.Restaurant;
import View.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LauncherController class
 */
public class LauncherController implements ActionListener {
    private Launcher launcher;
    private FormController listener;

    /**
     * Default class constructor
     * @param listener controller
     */
    public LauncherController(FormController listener) {
        this.listener = listener;
    }

    /**
     *  view setter
     * @param launcher view
     */
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    /**
     * override function
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "SIGN_IN":
                //first we check that all camps are filled
                if(launcher.allFieldsFilled()){
                    Restaurant r = new Restaurant(launcher.getUserField(),
                            MD5(launcher.getPwField()));
                    //ToDo: log out reset restaurant
                    if(listener.login(r)){
                        launcher.goToWindow("SIGN_IN");
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
     * crypt string
     * @param md5 string to encrypt
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
