package View;

import javax.swing.*;
import java.awt.*;

public class SplitPlane extends JPanel {
    private JPanel sidebar;
    private JPanel currentView;
    private JSplitPane splitPane;
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
