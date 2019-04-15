package Controller;

import View.Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookController implements ActionListener {


    private Book book;
    private FormController listener;


    public BookController(FormController listener) {
        this.listener = listener;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        book.goToWindow(e.getActionCommand());
    }
}
