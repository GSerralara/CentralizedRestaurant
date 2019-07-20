package Model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * The main function of this type is to carry out the countdown operation.
 */
public class Countdown extends Thread{
    private JLabel etiqueta,state;
    private Date time;

    /**
     * This function is the class constructor countdown.
     * @param etiqueta it's a variable that have the string of time of each dish.
     * @param time it's a variable that have the time.
     * @param state it's a variable that have the state of each dish.
     */

    public Countdown(JLabel etiqueta, Date time, JLabel state) {
        this.etiqueta = etiqueta;
        this.state = state;
        this.time = time;
    }


    /**
     * Here in this run is where we'll do the countdown.
     */
    @Override
    public void run() {

        Calendar c = Calendar.getInstance();
        while (state.getText().equals("Cocinando")){
            try {
                long milis = 1000;
                SimpleDateFormat df = new SimpleDateFormat("mm:ss");
                c.setTime(time);
                c.add(Calendar.SECOND,-1);
                //time.setTime(c.getTimeInMillis());
                time.setTime(time.getTime()-milis);
                etiqueta.setText(df.format(c.getTime()));
                if(df.format(time).equals("00:00")){
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
