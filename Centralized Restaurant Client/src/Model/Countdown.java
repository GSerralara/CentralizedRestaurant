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
