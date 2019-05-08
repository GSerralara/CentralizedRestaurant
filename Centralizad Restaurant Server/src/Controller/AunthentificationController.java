package Controller;

import View.Aunthentification;

public class AunthentificationController {
    private Aunthentification aunthentification;
    private FormController listener;

    public AunthentificationController(FormController listener) {
        this.listener = listener;
    }

    public void setAunthentification(Aunthentification aunthentification) {
        this.aunthentification = aunthentification;
    }
}
