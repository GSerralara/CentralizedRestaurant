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

/**
 *
 * This function will manage customer data.
 */
public class ModelClient {
    private String login;
    private User user;
    private Settings settings;

    /**
     * Default Constructor.
     */
    ModelClient() {
        this.login = "LogOut";
        settings = new Settings();
    }

    /**
     * Getter a Seetings.
     * @return a Strings
     */

    Settings getSettings() {
        return settings;
    }

    /**
     * Setter a User
     * @param user User
     */
    public void setUser(User user) {
        this.user = user;
        setLogin(user.getUser());
    }

    /**
     * Close
     */
    public void close(){
        setLogin("LogOut");
        user = null;
    }

    /**
     * Setter a Login
     * @param login Login
     */
    private void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter a User
     * @return a User
     */

    public User getUser() {
        return user;
    }
}
