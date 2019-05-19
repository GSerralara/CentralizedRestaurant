package Model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Countdown extends Thread{
    private JLabel etiqueta,state;
    private Date time;

    public Countdown(JLabel etiqueta, Date time, JLabel state) {
        this.etiqueta = etiqueta;
        this.state = state;
        this.time = time;
    }

    @Override
    public void run() {
        boolean run = true;
        Calendar c = Calendar.getInstance();
        while (run){
            try {
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                c.setTime(time);
                c.add(Calendar.SECOND,-1);
                time.setTime(c.getTimeInMillis());
                etiqueta.setText(df.format(time));
                if(c.getTimeInMillis() == 0){
                    run = false;
                    state.setText("Servido");
                }else {
                    sleep(1000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
