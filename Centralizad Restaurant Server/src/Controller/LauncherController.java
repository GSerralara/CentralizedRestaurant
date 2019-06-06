package Controller;

import Model.Database.Entity.Restaurant;
import View.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherController implements ActionListener {
    private Launcher launcher;
    private FormController listener;
    public LauncherController(FormController listener) {
        this.listener = listener;
    }

    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "SIGN_IN":
                //first we check that all camps are filled
                if(launcher.allFieldsFilled()){
                    Restaurant r = new Restaurant(launcher.getUserField(),launcher.getPwField());
                    //ToDo: check if log was successful and kind of log
                    launcher.goToWindow("SIGN_IN");
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
}
