package Model.Database;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Read Json.
 */
public class Configuration {
    private Gson g;
    private JsonObject configuration = null;
    /**
     * This method serves to setup the configuration of our BBDD.
     * Also read the File "data/config.json" to start.
     */

    public Configuration() {
        g = new GsonBuilder().create();
        try{
            configuration = g.fromJson(new FileReader("data/config.json"), JsonObject.class);
        }catch(JsonSyntaxException | JsonIOException | FileNotFoundException | NullPointerException  e){
            e.printStackTrace();
        }
    }
    /**
     *
     * @return This method return de BBDD port.
     */
    public int getBBDDPort(){
        return configuration.get("PortBBDD").getAsInt();
    }
    /**
     *
     * @return This method return de IP.
     */
    public String getDirIpBBDD(){
        return configuration.get("DireccioIPBBDD").getAsString();
    }
    /**
     *
     * @return This method return the name of our BBDD.
     */
    public String getnameBBDD(){
        return configuration.get("NomBBDD").getAsString();
    }
    /**
     *
     * @return This method return the user of our BBDD.
     */
    public String getuserBBDD(){
        return configuration.get("UsuariBBDD").getAsString();
    }
    /**
     *
     * @return This method return the password of our BBDD.
     */
    public String getPassBBDD(){
        return configuration.get("ContrasenyaBBDD").getAsString();
    }
    /**
     *
     * @return This method return the Client Port.
     */
    public int getClientPort(){
        return configuration.get("PortClients").getAsInt();
    }
}
