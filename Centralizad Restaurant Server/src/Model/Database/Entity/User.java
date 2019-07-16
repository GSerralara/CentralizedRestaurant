package Model.Database.Entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String user;
    private String password;
    private boolean regiser;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
        regiser = false;
    }

    public String getUser() {
        return user;
    }


    public String getPassword() {
        return password;
    }

    public void isRegister(){
        regiser=true;
    }

    public boolean getRegiser() {
        return regiser;
    }

}
