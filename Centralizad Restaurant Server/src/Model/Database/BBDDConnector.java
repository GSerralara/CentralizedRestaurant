package Model.Database;

import Model.Database.Entity.Restaurant;

import java.sql.*;

/**
 * BBDD Conector class.
 */
public class BBDDConnector {
    static String userName;
    static String password;
    static String db;
    static int port;
    static String url = "jdbc:mysql://";
    static Connection conn;
    static Statement s;

    /**
     * This method is used to conect our BBDD in our System.
     * @param usr it's a parameter the type of String.
     * @param pass it's a parameter the type of String.
     * @param db it's a parameter the type of String.
     * @param port it's a parameter the type of Integer.
     * @param dirIp it's a parameter the type of String.
     */

    public BBDDConnector(String usr, String pass, String db, int port, String dirIp) {
       this.userName = usr;
       this.password = pass;
       this.db = db;
       this.port = port;
       this.url += dirIp;
       this.url += ":"+port+"/";
       this.url += db;
    }

    /**
     * This method is based in connect the BBDD and if it detects any connectivity problem it will launch an error message thanks to try catch.
     */

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            //characterEncoding=latin1&
            //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Connection conn = new Connection();
            url += "?characterEncoding=latin1&useConfigs=maxPerformance&useSSL=false";
            conn = DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("Established connection to "+url+" Database");
            }
        }
        catch(SQLException e) {
            throw new IllegalStateException("Cannot connect the database "+url, e);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean isConnected(){
        return conn != null;
    }

    /**
     * This method is based in disconnect the BBDD and if it detects any connectivity problem it will launch an error message thanks to try catch.
     */

    public void disconnect(){
        try {
            if(conn!=null){
                conn.close();
                System.out.println("Closed connection to the Database");
            }else{
                System.out.println("Not closed connection to the Database");
            }
        } catch (SQLException e) {
            System.out.println("ERROR, Cannot close the connection" + e.getSQLState());
        }
        catch (NullPointerException e){
            System.out.println("ERROR, Not Created previous connection to the database");
            e.getStackTrace();
        }
    }
    /**
     *  DDL (Data Definition Language)
     *  CREATE, ALTER, DROP, TRUNCATE, COMMENT, RENAME
     **/
    public void ddlQuery(String query){
        try {
            s = conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("Error trying to create or modify the database structure " + e.getSQLState());
            e.printStackTrace();
        }
    }
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);

        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
        return rs;
    }
    public int callProcedure(String tableTargetAction,Object obj){
        CallableStatement cStmt = null;
        ResultSet rs = null;
        int ok = 0;
        int pos = 0;
        try {
            switch (tableTargetAction){
                case "Restaurant-New":
                    Restaurant res = (Restaurant)obj;
                    cStmt = conn.prepareCall("{call newRestaurant(?,?,?,?)}");
                    cStmt.setString(1,res.getMail());
                    cStmt.setString(2,res.getUser());
                    cStmt.setString(3,res.getPassword());
                    cStmt.registerOutParameter(4, Types.INTEGER);
                    pos=4;
                    break;
                case "Restaurant-Log":
                    Restaurant reslog = (Restaurant)obj;
                    cStmt = conn.prepareCall("{call loginRestaurant(?,?,?)}");
                    if(reslog.getUser().equals("")){
                        cStmt.setString(1,reslog.getMail());
                    }else{
                        cStmt.setString(1,reslog.getUser());
                    }
                    cStmt.setString(2,reslog.getPassword());
                    cStmt.registerOutParameter(3,Types.INTEGER);
                    pos = 3;
                    break;
            }
            cStmt.execute();
            ok = cStmt.getInt(pos);


        }catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
        return ok;
    }
    public void addDish(int id_d,int id_t,int q){
        System.out.println("table "+id_t+" gets "+id_d+" per x"+q);
        CallableStatement cStmt = null;
        try {
            cStmt = conn.prepareCall("{call orderDish(?,?,?)}");
            cStmt.setInt(1,id_t);
            cStmt.setInt(2,id_d);
            cStmt.setInt(3,q);
            cStmt.execute();
        }catch (SQLException e){
            System.out.println("Problema al Recuperar les dades --> " + e.getSQLState());
        }
    }
}
