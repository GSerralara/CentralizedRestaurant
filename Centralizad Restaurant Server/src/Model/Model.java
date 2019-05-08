package Model;

public class Model {
    private BBDDHelper database;
    private String serverState;
    public Model(){
        this.serverState = "END";
    }
    public void setServerState(String state){
        this.serverState = state;
        regulateConnection();
    }

    private void regulateConnection(){
        switch (this.serverState){
            case "INIT":
                this.database = new BBDDHelper();
                break;
            case "END":
                this.database.disconnectBBDD();
                break;
        }
    }

    public String getServerState() {
        return serverState;
    }
    public int getCommuncicationPort(){
        return database.getCommuncicationPort();
    }
}
