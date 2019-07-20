package Model.Database.Entity;

import java.io.Serializable;

/**
 * Reserve class.
 */

public class Reserve  implements Serializable {
    private User user;
    private int BookNumber;
    private String ReserveName;

    /**
     * Default constructor of the class.
     * @param user Username.
     * @param bookNumber number of book.
     * @param reserveName String.
     */

    public Reserve(User user, int bookNumber, String reserveName) {
        this.user = user;
        BookNumber = bookNumber;
        ReserveName = reserveName;
    }

    /**
     * Getter a User.
     * @return a user.
     */

    public User getUser() {
        return user;
    }

    /**
     * Getter a book Number
     * @return a integer.
     */
    public int getBookNumber() {
        return BookNumber;
    }

    /**
     * Get a Reserve name,
     */
    public String getReserveName() {
        return ReserveName;
    }
}