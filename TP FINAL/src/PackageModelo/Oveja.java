package PackageModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Oveja extends Animal{
    ///todo.ATRIBUTO
    public String color;

    public boolean tieneLana;

    ///todo.CONSTRUCTOR

    public Oveja(String color, boolean tieneLana) {
        this.color = color;
        this.tieneLana = tieneLana;
    }

    public Oveja() {}

    ///todo.GET Y SET
    public String getColor() {
        return color;
    }
    public boolean isTieneLana() {
        return tieneLana;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public void setTieneLana(boolean tieneLana) {
        this.tieneLana = tieneLana;
    }

    ///todo.METODOS
    @Override
    public String toString() {
        return "Oveja{" +
                "color='" + color + '\'' +
                ", tieneLana=" + tieneLana +
                "} " + super.toString();
    }

    @Override
    public String emitirSonido() {
        return "MEEE (Traduccion automatica a ingles lanudo:yoyoyoyoyo)";
    }

    public String esquilar(){
        tieneLana = false;
        return "shuck shuck *cae la lana*";
    }
    public String crecerLana(){
        tieneLana = true;
        return "Tiene lanita crecida";
    }

    //todo.METODOS JSON//
    public JSONObject convertirAJSON () {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("nombre", nombre);
            jsonObject.put("vida", vida);
            jsonObject.put("danio", danio);
            jsonObject.put("drops", drops);
            jsonObject.put("esBebe", esBebe);
            jsonObject.put("tipoAlimentacion", tipoAlimentacion);
            jsonObject.put("color", color);
            jsonObject.put("tieneLana", tieneLana);

        } catch (JSONException exc) {
            exc.printStackTrace();
        }
        return jsonObject;
    }

    public Oveja convertirAOveja (JSONObject jsonObject) {
        Oveja oveja = new Oveja();

        try {
            JSONArray jsonArray = new JSONArray();
            ArrayList<String> dropsAux = new ArrayList<>();

            for (int i=0; i<jsonArray.length();i++){dropsAux.add(jsonArray.getString(i));}

            oveja.setDrops(dropsAux);
            oveja.setNombre(jsonObject.getString("nombre"));
            oveja.setVida(jsonObject.getDouble("vida"));
            oveja.setDanio(jsonObject.getDouble("danio"));
            oveja.setEsBebe(jsonObject.getBoolean("esBebe"));
            //oveja.setTipoAlimentacion();
            oveja.setColor(jsonObject.getString("color"));
            oveja.setTieneLana(jsonObject.getBoolean("tieneLana"));
        } catch (JSONException exc) {
            exc.printStackTrace();
        }

        return oveja;
    }
}
