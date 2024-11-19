package PackageModelo;

import PackageEnum.TipoAlimentacion;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

public class Oveja extends Animal implements IConversionJSON, ITabla {
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

    ///══TO STRING══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    @Override
    public String toString() {
        return "Oveja{" +
                "color='" + color + '\'' +
                ", tieneLana=" + tieneLana +
                "} " + super.toString();
    }

    @Override
    public String aTabla() {
        return AsciiTable.getTable(new String[][] {
                {"Mob", getTipo() },
                {"ID", String.format("%d" ,getId()) },
                {"Nombre", getNombre()},
                {"Vida", String.format("%.2f", getVida())},
                {"Daño", String.format("%.2f", getDanio())},
                {"¿Es bebé?", esBebe ? "Sí" : "No"},
                {"Tipo alimentación", tipoAlimentacion.name()},
                {"¿Tiene lana?", tieneLana ? "Sí" : "No"},
                {"Color", getColor()}
        });
    }

    @Override
    public String[] aFila() {
        return new String[]{
                String.format("%d" ,getId()),
                getNombre(),
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
                isEsBebe()? "Sí" : "No",
                getTipoAlimentacion().name(),
                isTieneLana()? "Sí" : "No",
                getColor()};
    }

    ///══MÉTODOS══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    public String esquilar(){
        tieneLana = false;
        return "shuck shuck *cae la lana*";
    }
    public String crecerLana(){
        tieneLana = true;
        return "Tiene lanita crecida";
    }

    ///══JSON══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
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
