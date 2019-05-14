package Controller;

import View.Aunthentification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AunthentificationController implements ActionListener {
    private Aunthentification aunthentification;
    private FormController listener;

    public AunthentificationController(FormController listener) {
        this.listener = listener;
    }

    public void setAunthentification(Aunthentification aunthentification) {
        this.aunthentification = aunthentification;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
