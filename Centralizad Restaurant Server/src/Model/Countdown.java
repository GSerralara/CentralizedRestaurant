package Model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Countdown extends Thread{
    private JLabel etiqueta;
    private Date time;

    public Countdown(JLabel etiqueta, Date time) {
        this.etiqueta = etiqueta;
        this.time = time;
    }

    @Override
    public void run() {
        boolean run = true;
        while (run){
            try {
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Calendar c = Calendar.getInstance();
                c.setTime(time);
                c.add(Calendar.MINUTE,-1);
                time.setTime(c.getTimeInMillis());
                etiqueta.setText(df.format(time));
                if(c.getTimeInMillis() == 0){
                    run = false;
                }else {
                    sleep(60000);
                }


            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
