package Model.DataBase;

import Model.Model;

import java.sql.*;

public class BBDDConnector {
    static String userName;
    static String password;
    static String db;
    static int port;
    static String url = "jdbc:mysql://";
    static Connection conn;
    static Statement s;

    public BBDDConnector(String usr, String pass, String db, int port, String dirIp) {
       this.userName = usr;
       this.password = pass;
       this.db = db;
       this.port = port;
       this.url += dirIp;
       this.url += ":"+port+"/";
       this.url += db;
    }
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
}
