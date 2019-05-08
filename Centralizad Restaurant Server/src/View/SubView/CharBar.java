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
        g.drawLine(0,(DIM_HEIGHT/20)*19,DIM_WIDTH,(DIM_HEIGHT/20)*19);
        g.drawLine((DIM_WIDTH/20),0,(DIM_WIDTH/20),DIM_HEIGHT);
        g.drawRect((DIM_WIDTH/10)*7,(DIM_HEIGHT/20),(DIM_WIDTH/30),(DIM_HEIGHT/20)*18);
    }
}
