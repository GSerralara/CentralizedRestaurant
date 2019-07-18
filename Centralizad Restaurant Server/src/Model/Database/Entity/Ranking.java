package Model.Database.Entity;

public class Ranking {
    private String name;
    private int served;

    public Ranking(String name, int served) {
        this.name = name;
        this.served = served;
    }

    public String getName() {
        return name;
    }

    public int getServed() {
        return served;
    }
}
