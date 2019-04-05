package Controller;

import View.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private FormController listener;
    private Main main;

    public MainController(FormController listener) {
        this.listener = listener;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
