package Model.Database.Entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public String reserve;
    private String user;//It can be username or mail
    private String mail;
    private String password;

    public User(String user, String mail, String password) {
        this.user = user;
        this.mail = mail;
        this.reserve = "";
        this.password = password;
    }
    public User(String user, String password) {
        this.user = user;
        this.mail = "";
        this.reserve = "";
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
