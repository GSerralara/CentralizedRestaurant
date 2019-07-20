package Model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Time class
 * extends Thread
 * serves as time runner updater of a label
 */
public class Time extends Thread {
    private JLabel etiqueta;
    /**
     * Constructor by default of the class.
     * @param tiempo it's a parameter that show the actual time.
     */
    public Time(JLabel tiempo){
        this.etiqueta = tiempo;
    }

    /**
     *
     * This method it's a Thread that's in all moment run and show every time the actual time.
     */
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
