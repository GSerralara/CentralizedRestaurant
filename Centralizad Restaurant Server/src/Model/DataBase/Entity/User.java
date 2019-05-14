package Model.DataBase.Entity;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String mail;

    public User(String userName,String password, String mail) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
    }
}
