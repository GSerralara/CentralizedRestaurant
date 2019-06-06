package Model.Database.Entity;

public class Restaurant {


    private String username;//It can be username or mail
    private String mail;
    private String password;

    public Restaurant(String user, String mail, String password) {
        this.username = user;
        this.mail = mail;
        this.password = password;
    }
    public Restaurant(String identification, String password) {
        if(identification.contains("@")){
            this.mail = identification;
            this.username = "";
        }else {
            this.username =identification;
            this.mail = "";
        }
        this.password = password;
    }
    public String getUser() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

}
