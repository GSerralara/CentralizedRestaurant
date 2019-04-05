package Model;
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

    public ModelClient() {
        login = "LogOut";
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
