package Model.Database.dao;

import Model.Database.BBDDHelper;
import Model.Database.Entity.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class TableDAO {

    public void addTable(Table table){
        String query = "";
        BBDDHelper.getInstance().insertRegister(query);
    }
    public LinkedList<Table> getAllTables(){
        LinkedList<Table> tables = new LinkedList<>();
        String query = "";
        ResultSet resultat = BBDDHelper.getInstance().selectTable(query);
        try {
            while (resultat.next()) {
                //add
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
