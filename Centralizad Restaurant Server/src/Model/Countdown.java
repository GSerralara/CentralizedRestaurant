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
        Calendar c = Calendar.getInstance();
        while (run){
            try {
                SimpleDateFormat df = new SimpleDateFormat("mm:ss");
                c.setTime(time);
                c.add(Calendar.SECOND,-1);
                time.setTime(c.getTimeInMillis());
                etiqueta.setText(df.format(time));
                if(df.format(time).equals("00:00")){
                    run = false;
                }else {
                    sleep(1000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
