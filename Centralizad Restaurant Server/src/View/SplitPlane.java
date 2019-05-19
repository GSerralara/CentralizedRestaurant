package View;

import javax.swing.*;
import java.awt.*;

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

    public void changeCurrentView(JPanel newView){
        splitPane.remove(currentView);
        this.currentView = newView;
        splitPane.add(this.currentView);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0);
    }
    public void setSplitSize(int x, int y){
        splitPane.setPreferredSize(new Dimension(x, y));
    }
}
