package Model;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Settings {
    private JsonObject configuration = null;
    Settings() {
        Gson g = new GsonBuilder().create();
        try{
            configuration = g.fromJson(new FileReader("data/config.json"), JsonObject.class);
        }catch(JsonSyntaxException | JsonIOException | FileNotFoundException | NullPointerException  e){
            e.printStackTrace();
        }
    }
    public int getPort(){
        return configuration.get("Port").getAsInt();
    }
    public String getIp(){
        return configuration.get("IP").getAsString();
    }
}
