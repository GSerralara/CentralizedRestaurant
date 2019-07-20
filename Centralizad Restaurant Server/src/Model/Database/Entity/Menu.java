package Model.Database.Entity;

/**
 * Menu class.
 */

public class Menu {
    private int id;
    private String name;
    private String usernameRestaurant;
    private String emailRestaurant;

    public Menu(String name) {
        this.name = name;
    }

    /**
     * Default constructor.
     * @param id Id
     * @param name Name
     * @param usernameRestaurant User Owner
     * @param emailRestaurant email.
     */
    public Menu(int id, String name, String usernameRestaurant, String emailRestaurant) {
        this.id = id;
        this.name = name;
        this.usernameRestaurant = usernameRestaurant;
        this.emailRestaurant = emailRestaurant;
    }


    /**
     * Setter a Username.
     * @param usernameRestaurant Username.
     */
    public void setUsernameRestaurant(String usernameRestaurant) {
        this.usernameRestaurant = usernameRestaurant;
    }

    public void setEmailRestaurant(String emailRestaurant) {
        this.emailRestaurant = emailRestaurant;
    }

    /**
     * Getter id
     * @return a integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter a name
     * @return a String.
     */
    public String getName() {
        return name;
    }


    public String getUsernameRestaurant() {
        return usernameRestaurant;
    }

    public String getEmailRestaurant() {
        return emailRestaurant;
    }
}
