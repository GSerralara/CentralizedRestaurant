package Model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends Thread {
    private JLabel etiqueta;

    public Time(JLabel tiempo){
        this.etiqueta = tiempo;
    }

    @Override
    public void run(){

        while(true){

            Date hoy = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            etiqueta.setText(sdf.format(hoy));

            try{

                sleep(1000); //Segundo a segundo...

            }catch(Exception e){
                e.getMessage();

            }
        }
    }
}
