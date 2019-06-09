package Model.Database.Entity;

public class Menu {
    private int id;
    private String name;
    private String usernameRestaurant;
    private String emailRestaurant;

    public Menu(String name) {
        this.name = name;
    }

    public Menu(int id, String name, String usernameRestaurant, String emailRestaurant) {
        this.id = id;
        this.name = name;
        this.usernameRestaurant = usernameRestaurant;
        this.emailRestaurant = emailRestaurant;
    }



    public void setUsernameRestaurant(String usernameRestaurant) {
        this.usernameRestaurant = usernameRestaurant;
    }

    public void setEmailRestaurant(String emailRestaurant) {
        this.emailRestaurant = emailRestaurant;
    }

    public int getId() {
        return id;
    }

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
