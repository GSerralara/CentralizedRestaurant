package Controller;


import View.Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private Register register;
    private FormController listener;
    public RegisterController(FormController listener) {
        this.listener = listener;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case "REGISTER":
                register.goToWindow("REGISTER");
                break;
            case "SIGN_IN":
                register.goToWindow("SIGN_IN");
                break;
            default:
                System.err.println("Unknown Button register");
                break;
        }
    }
}
