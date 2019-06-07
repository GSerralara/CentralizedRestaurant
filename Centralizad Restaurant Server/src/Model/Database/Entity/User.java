package Model.Database.Entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reserve;
    private String user;
    private String password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
        this.reserve = "";
    }

    public String getUser() {
        return user;
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
