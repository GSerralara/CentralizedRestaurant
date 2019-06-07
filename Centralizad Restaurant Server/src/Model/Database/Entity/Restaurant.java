package Model.Database.Entity;

public class Restaurant {


    private String username;
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

    public boolean same(Restaurant obj){
        if(obj.getUser().equals("")){
            return obj.getMail().equals(this.mail) && obj.password.equals(this.password);
        }
        return obj.getUser().equals(this.username) && obj.password.equals(this.password);
    }

}
