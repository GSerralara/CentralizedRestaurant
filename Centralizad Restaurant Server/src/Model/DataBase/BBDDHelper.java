package Model.DataBase;

import Model.DataBase.BBDDConnector;
import Model.Model;

import java.sql.ResultSet;

public class BBDDHelper {
    private static BBDDHelper instance;
    private BBDDConnector conn;
    private Configuration settings;
    public BBDDHelper() {
        this.settings = new Configuration();
        this.conn = new BBDDConnector( settings.getuserBBDD(), settings.getPassBBDD(),
                settings.getnameBBDD(), settings.getBBDDPort(),settings.getDirIpBBDD());
        conn.connect();
    }
    public void insertRegister(String query){
        conn.ddlQuery(query);
    }
    public ResultSet selectTable(String query){
        return this.conn.selectQuery(query);
    }
    public void deleteRegister(String query){
        conn.ddlQuery(query);
    }
    public void disconnectBBDD(){
        conn.disconnect();
    }

    public int getCommuncicationPort(){
        return settings.getClientPort();
    }

    public static synchronized BBDDHelper getInstance( ){
        if(instance == null){
            instance = new BBDDHelper();
        }
        return instance;
    }
}
