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
        System.out.println(query);
        BBDDHelper.getInstance().insertData(query);
    }
    public LinkedList<Table> getAllTables(){
        LinkedList<Table> tables = new LinkedList<>();
        String query = "SELECT * FROM tables;";
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
    public void deleteTable(){
        String query = "";
        BBDDHelper.getInstance().deleteRegister(query);
    }
}
