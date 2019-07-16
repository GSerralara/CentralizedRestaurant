package Model.Database.Entity;

import java.io.Serializable;

public class Reserve  implements Serializable {
    private User user;
    private int BookNumber;
    private String ReserveName;

    public Reserve(User user, int bookNumber, String reserveName) {
        this.user = user;
        BookNumber = bookNumber;
        ReserveName = reserveName;
    }

    public User getUser() {
        return user;
    }

    public int getBookNumber() {
        return BookNumber;
    }

    public String getReserveName() {
        return ReserveName;
    }
}