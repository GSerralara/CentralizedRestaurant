package Controller;

import Model.Database.Entity.Ranking;
import View.PostService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * PostServiceController class
 */
public class PostServiceController implements ActionListener {
    private FormController listener;
    private PostService postService;

    /**
     * * Constructor by default of the class.
     * @param listener it's a FormController that the class will use to move to other views
     */

    public PostServiceController(FormController listener) {
        this.listener = listener;
    }

    /**
     * view setter
     * @param postService
     */
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /**
     * override function
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * add a panel
     * @param name name
     * @param ranking list
     */
    public void addPanel(String name, LinkedList<Ranking> ranking){
        postService.addTab(name,ranking);
    }
}
