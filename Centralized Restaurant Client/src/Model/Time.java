package Model;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time extends Thread {
    private Date date;
    private JLabel time;

    public Time(JLabel time) {
        this.time = time;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            date = df.parse(time.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        int secs = 0;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        while(true){
            try{
                sleep(1000);
                secs++;
                if(secs==60){
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.MINUTE,1);
                    date.setTime(c.getTimeInMillis());
                    time.setText(df.format(date));
                    secs =0;
                }
            }catch(Exception e){
                e.getMessage();

            }
        }
    }
}
