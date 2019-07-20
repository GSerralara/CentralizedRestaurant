package Model.Database.Entity;

/**
 * Restaurant class.
 */
public class Restaurant {


    private String username;
    private String mail;
    private String password;

    /**
     * Default class constructor.
     * @param user User.
     * @param mail Mail.
     * @param password Password.
     */

    public Restaurant(String user, String mail, String password) {
        this.username = user;
        this.mail = mail;
        this.password = password;

    }

    /**
     * Second class constructor.
     * @param identification id.
     * @param password Password.
     */
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

    /**
     * Getter of Username.
     * @return a string.
     */
    public String getUser() {
        return username;
    }

    /**
     * Return an email.
     * @return a String.
     */

    public String getMail() {
        return mail;
    }

    /**
     * Getter a password.
     * @return a String.
     */

    public String getPassword() {
        return password;
    }

    /**
     * Set a Username.
     * @param username name.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set an email.
     * @param mail String.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Set a password.
     * @param password Password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Override of equals.
     * @param obj Object
     * @return a boolean.
     */
    public boolean same(Restaurant obj){
        //case not login existing log
        if(this.mail.equals("") && this.getUser().equals("")){
            return false;
        }
        if(obj.getUser().equals("")){
            return obj.getMail().equals(this.mail) && obj.password.equals(this.password);
        }
        return obj.getUser().equals(this.username) && obj.password.equals(this.password);
    }

}
