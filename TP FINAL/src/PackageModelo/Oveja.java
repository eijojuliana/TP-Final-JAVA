package PackageModelo;

import PackageEnum.TipoAlimentacion;
import PackageInterfaces.IConversionJSON;
import org.json.JSONException;
import org.json.JSONObject;

public class Oveja extends Animal implements IConversionJSON {
    ///todo.ATRIBUTO
    public String color;
    public boolean tieneLana;

    public Oveja(String nombre, double vida, double danio, boolean esBebe, TipoAlimentacion tipoAlimentacion, String color, boolean tieneLana) {
        super(nombre, vida, danio, Oveja.class.getSimpleName(), esBebe, tipoAlimentacion);
        this.color = color;
        this.tieneLana = tieneLana;
    }

    ///todo.CONSTRUCTOR
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

    @Override
    public String toString() {
        return "Oveja{" +
                "color='" + color + '\'' +
                ", tieneLana=" + tieneLana +
                "} " + super.toString();
    }

    ///todo.METODOS


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

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("color", color);
            j.put("tieneLana", tieneLana);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return j;
    }

    @Override
    public boolean fromJSON(JSONObject j) {
        boolean exito = false;
        try {
            super.fromJSON(j);
            setColor(j.getString("color"));
            setTieneLana(j.getBoolean("tieneLana"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
