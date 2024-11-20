package PackageModelo;

import PackageEnum.TipoAlimentacion;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class Oveja extends Animal implements IConversionJSON, ITabla {
    ///todo.ATRIBUTO
    private String color;
    private boolean tieneLana;

    ///todo.CONSTRUCTOR
    public Oveja(String nombre, boolean esBebe, TipoAlimentacion tipoAlimentacion, String color) {
        super(nombre, 4.0, 0.0, Oveja.class.getSimpleName(), esBebe, tipoAlimentacion);
        this.color = color;
        tieneLana = true;
        inicializar_drops();
    }
    public Oveja() {
        setVida(4);
        setDanio(0);
    }


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

    private void inicializar_drops() {
        ArrayList<String> drops = new ArrayList<>();
        drops.add("Lana");
        drops.add("Carne");
        setDrops(drops);

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
            j.put("tipo","Oveja");

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
