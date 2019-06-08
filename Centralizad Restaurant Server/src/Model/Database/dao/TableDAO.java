package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class TableDAO {

    public void addTable(Table table){
        String query = "INSERT INTO tables(usernameRestaurant, emailRestaurant, number_client) " +
                "VALUES ('"+table.getRestaurantName()+"','"+table.getRestaurantMail()+"',"+table.getNumberClients()+");";
        BBDDHelper.getInstance().insertData(query);
    }
    public LinkedList<Table> getAllTables(String restaurantName){
        LinkedList<Table> tables = new LinkedList<>();
        String query = "SELECT * FROM tables " +
                "WHERE usernameRestaurant LIKE '"+restaurantName+"';";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                Table t = new Table(resultat.getInt("number_client"),resultat.getInt("id_table"));
                tables.add(t);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tables;
    }
    public void deleteTable(Table delelte){
        String query = "DELETE FROM tables " +
                "WHERE id_table = "+delelte.getIdTable()+";";
        BBDDHelper.getInstance().deleteRegister(query);
    }
}
