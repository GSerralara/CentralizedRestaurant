package Model.Database;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration {
    private Gson g;
    private JsonObject configuration = null;
    public Configuration() {
        g = new GsonBuilder().create();
        try{
            configuration = g.fromJson(new FileReader("data/config.json"), JsonObject.class);
        }catch(JsonSyntaxException | JsonIOException | FileNotFoundException | NullPointerException  e){
            e.printStackTrace();
        }
    }
    public int getBBDDPort(){
        return configuration.get("PortBBDD").getAsInt();
    }
    public String getDirIpBBDD(){
        return configuration.get("DireccioIPBBDD").getAsString();
    }
    public String getnameBBDD(){
        return configuration.get("NomBBDD").getAsString();
    }
    public String getuserBBDD(){
        return configuration.get("UsuariBBDD").getAsString();
    }
    public String getPassBBDD(){
        return configuration.get("ContrasenyaBBDD").getAsString();
    }
    public int getClientPort(){
        return configuration.get("PortClients").getAsInt();
    }
}
