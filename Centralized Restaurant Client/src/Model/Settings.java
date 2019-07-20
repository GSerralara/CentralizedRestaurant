package Model;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 * Seeting class.
 */
public class Settings {
    private JsonObject configuration = null;
    /**
     *  This function will read the Json and remove the port and the corresponding IP.
     */
    Settings() {
        Gson g = new GsonBuilder().create();
        try{
            configuration = g.fromJson(new FileReader("data/config.json"), JsonObject.class);
        }catch(JsonSyntaxException | JsonIOException | FileNotFoundException | NullPointerException  e){
            e.printStackTrace();
        }
    }

    /**
     * Getter a Port
     * @return a integer.
     */
    public int getPort(){
        return configuration.get("Port").getAsInt();
    }

    /**
     * Getter a Ip
     * @return a String.
     */
    public String getIp(){
        return configuration.get("IP").getAsString();
    }
}
