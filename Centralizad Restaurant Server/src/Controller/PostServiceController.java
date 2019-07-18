package Controller;

import Model.Database.Entity.Ranking;
import View.PostService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PostServiceController implements ActionListener {
    private FormController listener;
    private PostService postService;

    public PostServiceController(FormController listener) {
        this.listener = listener;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void addPanel(String name, LinkedList<Ranking> ranking){
        postService.addTab(name,ranking);
    }
}
