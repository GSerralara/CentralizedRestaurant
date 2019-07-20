package Model.Database.Entity;

import java.io.Serializable;

/**
 * User class.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String user;
    private String password;
    private boolean regiser;

    /**
     * Default Constructor.
     * @param user name.
     * @param password password.
     */

    public User(String user, String password) {
        this.user = user;
        this.password = password;
        regiser = false;
    }

    /**
     * Getter of name
     * @return a name in one String.
     */
    public String getUser() {
        return user;
    }


    public String getPassword() {
        return password;
    }
    /**
     * tells if its a register.
     */
    public void isRegister(){
        regiser=true;
    }

    public boolean getRegiser() {
        return regiser;
    }
}
