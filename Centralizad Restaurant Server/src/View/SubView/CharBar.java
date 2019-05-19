package View.SubView;

import javax.swing.*;
import java.awt.*;

/**********************************

 */

public class CharBar extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle r = super.getBounds();
        int DIM_WIDTH = r.width; //483
        int DIM_HEIGHT = r.height; //218
        float grafica = DIM_HEIGHT-(DIM_HEIGHT/10);
        int intervalo = Math.round(DIM_HEIGHT/20); //11
        System.out.println("esto es la "+grafica);

        int top1= 1050;
        int top2 = 130;
        int top3 = 30;
        int top4 = 9;
        int top5 = 7;

        float corte1 = (float)top1 /10;
        int cortee = Math.round(corte1);

        float total2 = ((float)top2/(float)top1);
        float total3 = ((float)top3/(float)top1);
        float total4 = ((float)top4/(float)top1);
        float total5 = ((float)top5/(float)top1);

        System.out.println("total 5 es:"+total5);





        float prop2 = (grafica*(1-total2));
        float prop3 = (grafica*(1-total3));
        float prop4 = (grafica*(1-total4));
        float prop5 = (grafica*(1-total5));

        //int prop5 = Math.round(total5);


        g.drawLine(0,(DIM_HEIGHT/20)*19,DIM_WIDTH,(DIM_HEIGHT/20)*19); //eje horizontal n y1 y2 son iguale pq sino tendria inclinacion

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




        //int prop2 = (1- (top5/top1))*20;
        //int prop2 = 6;
        // int prop5 = 14;


        //provamos 5 barras horizontales
        g.drawRect((DIM_WIDTH/10)*1,(DIM_HEIGHT/20),(DIM_WIDTH/30),(DIM_HEIGHT/20)*18); //referencia
        g.drawString("Queso",(DIM_WIDTH/10)*1,(DIM_HEIGHT/20)*20);
        g.drawString(top1+"",(DIM_WIDTH/10)*1,((DIM_HEIGHT/20)*20)+15);

        g.drawRect((DIM_WIDTH/10)*3,Math.round(prop2 + (DIM_HEIGHT/20)),(DIM_WIDTH/30),Math.round(grafica -(1021-(int)(4.61*DIM_HEIGHT)) - prop2));
        g.drawString("Macarrone",(DIM_WIDTH/10)*3,(DIM_HEIGHT/20)*20);
        g.drawString(top2+"",(DIM_WIDTH/10)*3,((DIM_HEIGHT/20)*20)+15);

        System.out.println("GRAFICA POS:"+(grafica*(1-prop2)));
        System.out.println("GRAFICA POS2:"+intervalo);

        g.drawRect((DIM_WIDTH/10)*5,Math.round(prop3 + (DIM_HEIGHT/20)),(DIM_WIDTH/30),Math.round(grafica -(DIM_HEIGHT/13) - prop3));
        g.drawString("Pantumaca",(DIM_WIDTH/10)*5,(DIM_HEIGHT/20)*20);
        g.drawString(top3+"",(DIM_WIDTH/10)*5,((DIM_HEIGHT/20)*20)+15);

        g.drawRect((DIM_WIDTH/10)*7,Math.round(prop4 + (DIM_HEIGHT/20)),(DIM_WIDTH/30),Math.round(grafica -(DIM_HEIGHT/13) - prop4));
        g.drawString("Mejillone",(DIM_WIDTH/10)*7,(DIM_HEIGHT/20)*20);
        g.drawString(top4+"",(DIM_WIDTH/10)*7,((DIM_HEIGHT/20)*20)+15);

        g.drawRect((DIM_WIDTH/10)*9,Math.round(prop5 + (DIM_HEIGHT/20)),(DIM_WIDTH/30), Math.round(grafica -(DIM_HEIGHT/13) - prop5));
        g.drawString("Cocreta",(DIM_WIDTH/10)*9,(DIM_HEIGHT/20)*20);
        g.drawString(top5+"",(DIM_WIDTH/10)*9,((DIM_HEIGHT/20)*20)+15);

        System.out.println("aprox croqueta:"+(Math.round(prop5)));

    }
}

