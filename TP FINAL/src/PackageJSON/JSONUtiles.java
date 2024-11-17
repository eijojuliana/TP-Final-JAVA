package PackageJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONUtiles {

    public static void grabarUnJson(JSONArray jsonArray, String archivo){
        try {
            FileWriter file = new FileWriter(archivo+".json");
            file.write(jsonArray.toString(4));
            file.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static void grabarUnJson(JSONObject j, String archivo){
        try {
            FileWriter file = new FileWriter(archivo+".json");
            file.write(j.toString(4));
            file.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static String leer(String archivo)
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(Paths.get(archivo+".json")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contenido;
    }
}
