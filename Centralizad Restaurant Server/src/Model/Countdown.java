package Model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The main function of this type is to carry out the countdown operation.
 */
public class Countdown extends Thread{
    private JLabel etiqueta;
    private Date time;


    /**
     * This function is the class constructor countdown.
     * @param etiqueta it's a variable that have the string of time of each dish.
     * @param time it's a variable that have the time.
     */
    public Countdown(JLabel etiqueta, Date time) {
        this.etiqueta = etiqueta;
        this.time = time;
    }

    /**
     * Here in this run is where we'll do the countdown.
     */
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
