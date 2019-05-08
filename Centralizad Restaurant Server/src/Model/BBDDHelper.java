package Model;

public class BBDDHelper {
    private BBDDConnector conn;
    private Configuration settings;
    public BBDDHelper() {
        this.settings = new Configuration();
        this.conn = new BBDDConnector( settings.getuserBBDD(), settings.getPassBBDD(),
                settings.getnameBBDD(), settings.getBBDDPort(),settings.getDirIpBBDD());
        conn.connect();
    }
    public void disconnectBBDD(){
        conn.disconnect();
    }

    public int getCommuncicationPort(){
        return settings.getClientPort();
    }
}
