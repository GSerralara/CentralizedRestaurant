package View;

import javax.swing.*;
import java.awt.*;

/**
 * SplitPlane Class
 * extends JPanel
 * serves as side menu of the view
 */

public class SplitPlane extends JPanel {
    private JPanel sidebar;
    private JPanel currentView;
    private JSplitPane splitPane;
    /**
     * Constructor by default of the class. This method make the line that separate the different JPanel of Server.
     * @param sidebar it's a JPanel variable.
     * @param currentView it's the other JPanel that have the Server program.
     */
    public SplitPlane(JPanel sidebar,JPanel currentView) {
        this.currentView = currentView;
        this.sidebar = sidebar;
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                sidebar, currentView);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0);
        add(splitPane);
    }

    /**
     * most important function that allow us to be switching to view to other view
     * without loosing the side panel
     * @param newView that will appear alongside with the view
     */
    public void changeCurrentView(JPanel newView){
        splitPane.remove(currentView);
        this.currentView = newView;
        splitPane.add(this.currentView);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0);
    }

    /**
     * sets the dimension of the view
     * @param x dimension
     * @param y dimension
     */
    public void setSplitSize(int x, int y){
        splitPane.setPreferredSize(new Dimension(x, y));
    }
}
