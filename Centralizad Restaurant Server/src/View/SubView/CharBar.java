package View.SubView;

import javax.swing.*;
import java.awt.*;

public class CharBar extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle r = super.getBounds();
        int DIM_WIDTH = r.width;
        int DIM_HEIGHT = r.height;

        int top1= 1450;
        int top2 = 1230;
        int top3 = 97;
        int top4 = 90;
        int top5 = 1;
        int corteeacum = 0;
        float corte1 = (float)top1 /10;
        int cortee = Math.round(corte1);
/*
        for (int i = 0 ; i < 10 ; i++){
            System.out.println(cortee);
            g.drawString("cortee",(DIM_WIDTH/10)*0,(DIM_HEIGHT/20)*corteeacum/5);
            corteeacum = corteeacum + cortee;
        }
*/
        float total2 = ((float)top2/(float)top1);
        float total3 = ((float)top3/(float)top1);
        float total4 = ((float)top4/(float)top1);
        float total5 = ((float)top5/(float)top1);

        g.drawLine(0,(DIM_HEIGHT/20)*19,DIM_WIDTH,(DIM_HEIGHT/20)*19); //eje horizontal n y1 y2 son iguale pq sino tendria inclinacion

        /*for (int i = 1; i < 19; i = i+2){
            g.drawLine(10,(DIM_HEIGHT/20)*i,DIM_WIDTH/19,(DIM_HEIGHT/20)*i);

        }*/
        g.drawLine(25,(DIM_HEIGHT/200)*181,DIM_WIDTH/190,(DIM_HEIGHT/200)*181);
        g.drawString(1*cortee+"",(DIM_WIDTH/100)*0,(DIM_HEIGHT/200)*181);

        g.drawLine(25,(DIM_HEIGHT/200)*162,DIM_WIDTH/190,(DIM_HEIGHT/200)*162);
        g.drawString(2*cortee+"",(DIM_WIDTH/100)*0,(DIM_HEIGHT/200)*162);

        g.drawLine(25,(DIM_HEIGHT/200)*143,DIM_WIDTH/190,(DIM_HEIGHT/200)*143);
        g.drawString(3* cortee+"",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*143);

        g.drawLine(25,(DIM_HEIGHT/200)*124,DIM_WIDTH/190,(DIM_HEIGHT/200)*124);
        g.drawString(4* cortee+"",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*124);

        g.drawLine(25,(DIM_HEIGHT/200)*105,DIM_WIDTH/190,(DIM_HEIGHT/200)*105);
        g.drawString(5* cortee+"",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*105);

        g.drawLine(25,(DIM_HEIGHT/200)*86,DIM_WIDTH/190,(DIM_HEIGHT/200)*86);
        g.drawString(6* cortee+"",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*86);

        g.drawLine(25,(DIM_HEIGHT/200)*67,DIM_WIDTH/190,(DIM_HEIGHT/200)*67);
        g.drawString(7* cortee+"",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*67);

        g.drawLine(25,(DIM_HEIGHT/200)*48,DIM_WIDTH/190,(DIM_HEIGHT/200)*48);
        g.drawString(8* cortee+"",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*48);

        g.drawLine(25,(DIM_HEIGHT/200)*29,DIM_WIDTH/190,(DIM_HEIGHT/200)*29);
        g.drawString(9* cortee+"",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*29);

        g.drawLine(25,(DIM_HEIGHT/200)*10,DIM_WIDTH/190,(DIM_HEIGHT/200)*10);
        g.drawString(10* cortee+"",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*10);

        g.drawLine((DIM_WIDTH/20),0,(DIM_WIDTH/20),DIM_HEIGHT); //eje vertical

        float prop21 = (1- (total2))*18;
        float propr2 = Math.round(prop21);
        int prop2 = (int)propr2;

        float prop31 = (1- (total3))*18;
        float propr3 = Math.round(prop31);
        int prop3 = (int)propr3;

        float prop41 = (1- (total4))*18;
        float propr4 = Math.round(prop41);
        int prop4 = (int)propr4;

        float prop51 = (1-total5)*18;
        float propr5 = Math.round(prop51);
        int prop5 = (int)propr5;


        //int prop2 = (1- (top5/top1))*20;
        //int prop2 = 6;
        // int prop5 = 14;


        //provamos 5 barras horizontales
        g.drawRect((DIM_WIDTH/10)*1,(DIM_HEIGHT/20),(DIM_WIDTH/30),(DIM_HEIGHT/20)*18); //referencia
        g.drawString("Queso",(DIM_WIDTH/10)*1,(DIM_HEIGHT/20)*20);

        g.drawRect((DIM_WIDTH/10)*3,(DIM_HEIGHT/20)*(prop2),(DIM_WIDTH/30),(DIM_HEIGHT/20)*(20-prop2-1));
        g.drawString("Macarrone",(DIM_WIDTH/10)*3,(DIM_HEIGHT/20)*20);

        g.drawRect((DIM_WIDTH/10)*5,(DIM_HEIGHT/20)*(prop3),(DIM_WIDTH/30),(DIM_HEIGHT/20)*(20-prop3-1));
        g.drawString("Pantumaca",(DIM_WIDTH/10)*5,(DIM_HEIGHT/20)*20);

        g.drawRect((DIM_WIDTH/10)*7,(DIM_HEIGHT/20)*(prop4),(DIM_WIDTH/30),(DIM_HEIGHT/20)*(20-prop4-1));
        g.drawString("Mejillone",(DIM_WIDTH/10)*7,(DIM_HEIGHT/20)*20);

        g.drawRect((DIM_WIDTH/10)*9,(DIM_HEIGHT/20)*prop5,(DIM_WIDTH/30),(DIM_HEIGHT/20)*(20-prop5-1));
        g.drawString("Cocreta",(DIM_WIDTH/10)*9,(DIM_HEIGHT/20)*20);
    }
}
