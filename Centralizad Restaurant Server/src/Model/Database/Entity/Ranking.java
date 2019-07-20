package Model.Database.Entity;

/**
 * Ranking class.
 */

public class Ranking {
    private String name;
    private int served;

    /**
     * Default constructor.
     * @param name name.
     * @param served value.
     */

    public Ranking(String name, int served) {
        this.name = name;
        this.served = served;
    }

    /**
     * Get a String name.
     * @return a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Get a value.
     * @return a integer.
     */

    public int getServed() {
        return served;
    }
}
