package Model;

import Model.Database.Entity.User;

/**
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 * @version 1.0
 * Model of the program.
 */
public class ModelClient {
    private String login;
    private User user;
    private Settings settings;
    ModelClient() {
        this.login = "LogOut";
        settings = new Settings();
    }

    Settings getSettings() {
        return settings;
    }



    public void setUser(User user) {
        this.user = user;
        setLogin(user.getUser());
    }
    public void close(){
        setLogin("LogOut");
        user = null;
    }
    private void setLogin(String login) {
        this.login = login;
    }

    public User getUser() {
        return user;
    }
}
