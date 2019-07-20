package View.SubView;

import Model.Database.Entity.Dish;
import Model.Database.Entity.DishTable;
import Model.Database.Entity.Ranking;
import Model.Database.dao.DishDAO;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * CharBar Class
 * extends JPanel
 * generates the bar of the stadistics
 */

public class CharBar extends JPanel {

    private LinkedList<Ranking> list;

    /**
     * Default Constructor of the class
     * @param list of rankings
     */
    public CharBar(LinkedList<Ranking> list){
        this.list = list;
    }

    /**
     * override fucntion that paints the bar
     * @param g variable
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle r = super.getBounds();
        int DIM_WIDTH =483; //r.width; //we obtain the width of the graphic area
        int DIM_HEIGHT = 218;//r.height; //we obtain the height of the graphic area
        float graphic = DIM_HEIGHT-(DIM_HEIGHT/10); //the value of the maxium bar
        int interval = Math.round(DIM_HEIGHT/20); //the division between the up gap in the graphic area



        int top[] = {0,0,0,0,0};
        String name[] = {"","","","",""};


        int i=0;
        for(Ranking aux: list){
            System.out.println(aux.getName()+" "+aux.getServed());
            top[i]= aux.getServed();
            name[i] = aux.getName();
            i++;
        }


        int top1=  top[0];
        int top2 = top[1];
        int top3 = top[2];
        int top4 = top[3];
        int top5 = top[4];

        float cut = (float)top1 /10; //proportional cut parts


        float total2 = ((float)top2/(float)top1); //proportions of the dish 2 between the max
        float total3 = ((float)top3/(float)top1); //proportions of the dish 3 between the max
        float total4 = ((float)top4/(float)top1); //proportions of the dish 4 between the max
        float total5 = ((float)top5/(float)top1); //proportions of the dish 5 between the max


        float prop2 = (graphic*(1-total2)); //the final value of the proportion considering the  distance set.
        float prop3 = (graphic*(1-total3)); //same
        float prop4 = (graphic*(1-total4)); //same
        float prop5 = (graphic*(1-total5)); //same

        float correctionFactor = (1021-Math.round(4.60667*DIM_HEIGHT)); //the correction to match the height of the bar with the horizontal axis


        g.drawLine(0,(DIM_HEIGHT/20)*19,DIM_WIDTH,(DIM_HEIGHT/20)*19); //horizontal axis


        g.drawLine(25,(DIM_HEIGHT/200)*162,DIM_WIDTH/190,(DIM_HEIGHT/200)*162);
        g.drawString(2*cut + "",(DIM_WIDTH/100)*0,(DIM_HEIGHT/200)*162);



        g.drawLine(25,(DIM_HEIGHT/200)*124,DIM_WIDTH/190,(DIM_HEIGHT/200)*124);
        g.drawString(4* cut + "",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*124);


        g.drawLine(25,(DIM_HEIGHT/200)*86,DIM_WIDTH/190,(DIM_HEIGHT/200)*86);
        g.drawString(6* cut + "",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*86);



        g.drawLine(25,(DIM_HEIGHT/200)*48,DIM_WIDTH/190,(DIM_HEIGHT/200)*48);
        g.drawString(8*cut+ "",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*48);


        g.drawLine(25,(DIM_HEIGHT/200)*10,DIM_WIDTH/190,(DIM_HEIGHT/200)*10); //first cut (max number)
        g.drawString(Math.round(10*cut)+ "",(DIM_WIDTH/10)*0,(DIM_HEIGHT/200)*10);

        g.drawLine((DIM_WIDTH/20),0,(DIM_WIDTH/20),DIM_HEIGHT); //vertical axis


        g.fillRect((DIM_WIDTH/10)*1,interval,(DIM_WIDTH/30),interval*18);
        g.drawRect((DIM_WIDTH/10)*1,interval,(DIM_WIDTH/30),interval*18); //reference
        g.drawString(name[0],(DIM_WIDTH/10)*1,interval*20); //we write the first dish
        g.drawString(top1+"",(DIM_WIDTH/10)*1,(interval*20)+15); //we write the content

        /**
         Now we start to draw the graph. To do this we distribute in 5 proportional parts in horizontal and we use the proportion plus the gap between the up
         part and the max representation to set he origin of the rectangle generation. We set the rectangle height by minus the graphic, the proportion and
         a factor of correction .

         */
        g.fillRect((DIM_WIDTH/10)*3,Math.round(prop2 + interval),(DIM_WIDTH/30),Math.round(graphic - correctionFactor - prop2));//we paint in black the bar
        g.drawRect((DIM_WIDTH/10)*3,Math.round(prop2 + interval),(DIM_WIDTH/30),Math.round(graphic - correctionFactor - prop2));
        g.drawString(name[1],(DIM_WIDTH/10)*3,interval*20);
        g.drawString(top2 + "",(DIM_WIDTH/10)*3,((DIM_HEIGHT/20)*20)+15);


        g.fillRect((DIM_WIDTH/10)*5,Math.round(prop3 + interval),(DIM_WIDTH/30),Math.round(graphic - correctionFactor - prop3));
        g.drawRect((DIM_WIDTH/10)*5,Math.round(prop3 + interval),(DIM_WIDTH/30),Math.round(graphic -correctionFactor - prop3));
        g.drawString(name[2],(DIM_WIDTH/10)*5,interval*20);
        g.drawString(top3 + "",(DIM_WIDTH/10)*5,(interval*20)+15);

        g.drawRect((DIM_WIDTH/10)*7,Math.round(prop4 + interval),(DIM_WIDTH/30),Math.round(graphic - correctionFactor - prop4));
        g.fillRect((DIM_WIDTH/10)*7,Math.round(prop4 + interval),(DIM_WIDTH/30),Math.round(graphic - correctionFactor - prop4));
        g.drawString(name[3],(DIM_WIDTH/10)*7,interval*20);
        g.drawString(top4 + "",(DIM_WIDTH/10)*7,(interval*20)+15);

        g.fillRect((DIM_WIDTH/10)*9,Math.round(prop5 + interval),(DIM_WIDTH/30), Math.round(graphic - correctionFactor - prop5));
        g.drawRect((DIM_WIDTH/10)*9,Math.round(prop5 + interval),(DIM_WIDTH/30), Math.round(graphic - correctionFactor - prop5));
        g.drawString(name[4],(DIM_WIDTH/10)*9,interval*20);
        g.drawString(top5 + "",(DIM_WIDTH/10)*9,(interval*20)+15);

    }
}

