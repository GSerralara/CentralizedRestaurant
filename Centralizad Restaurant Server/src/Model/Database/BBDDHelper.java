package Model.Database;


import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 * Abstracts bbdd acces
 */
public class BBDDHelper {
    private static BBDDHelper instance;
    private BBDDConnector conn;
    private Configuration settings;
    /**
     * * Constructor by default of the class.
     */
    public BBDDHelper() {
        this.settings = new Configuration();
        this.conn = new BBDDConnector( settings.getuserBBDD(), settings.getPassBBDD(),
                settings.getnameBBDD(), settings.getBBDDPort(),settings.getDirIpBBDD());
        conn.connect();
    }

    /**
     * specific it's connected.
     * @return a boolean.
     */
    public boolean isConnected(){
        return conn.isConnected();
    }

    /**
     * Function that add a register.
     * @param table Table.
     * @param obj Anything Object.
     * @return a integer.
     */
    public int insertRegister(String table, Object obj){
        return conn.callProcedure(table,obj);
    }

    /**
     * Insert a data.
     * @param query Query.
     */
    public void insertData(String query){
        conn.ddlQuery(query);
    }

    /**
     * Call a procedure.
     * @param id_d id of dish.
     * @param id_t id table.
     * @param q quantity.
     */
    public void callAddDish(int id_d,int id_t,int q){
        conn.addDish(id_d,id_t,q);
    }

    /**
     * Select a table.
     * @param query query.
     * @return a ResultSet
     */
    public ResultSet selectTable(String query){
        return this.conn.selectQuery(query);
    }

    /**
     * This method is based in delete the different Register in BBDD.
     * @param query it's a parameter the type of String
     */

    public void deleteRegister(String query){
        conn.ddlQuery(query);
    }
    public void disconnectBBDD(){
        conn.disconnect();
    }

    /**
     * This method return in the specific port of BBDD.
     */

    public int getCommuncicationPort(){
        return settings.getClientPort();
    }

    /**
     * Make an update
     * @param query query.
     */
    public void updateQuery(String query){
        conn.ddlQuery(query);
    }

    /**
     * Create an instance of the class.
     * @return instance.
     */
    public static synchronized BBDDHelper getInstance( ){
        if(instance == null){
            instance = new BBDDHelper();
        }
        return instance;
    }
}
