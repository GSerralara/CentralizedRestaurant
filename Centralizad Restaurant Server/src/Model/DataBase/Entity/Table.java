package Model.DataBase.Entity;

public class Table {
    private int idTable;
    private int numberClients;

    public Table(int numberClients, int id) {
        this.numberClients = numberClients;
        this.idTable = id;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getNumberClients() {
        return numberClients;
    }

    public void setNumberClients(int numberClients) {
        this.numberClients = numberClients;
    }
}
