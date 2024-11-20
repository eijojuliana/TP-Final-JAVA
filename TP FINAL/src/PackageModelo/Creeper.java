package PackageModelo;

import PackageInterfaces.IConversionJSON;
import PackageInterfaces.IFila;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

public final class Creeper extends Mob implements IConversionJSON, ITabla, IFila {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo ATRIBUTOS
    private boolean esElectrico;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo CONSTRUCTORES

    public Creeper(String nombre, double vida, double danio, boolean esBebe, boolean esElectrico) {
        super(nombre, vida, danio, Creeper.class.getSimpleName(), esBebe);
        this.esElectrico = esElectrico;
    }
    public Creeper() {}

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo GETTER AND SETTER
    public boolean isEsElectrico() {
        return esElectrico;
    }
    public void setEsElectrico(boolean esElectrico) {
        this.esElectrico = esElectrico;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo SOBREESCRITURA
    @Override
    public String toString() {
        return  "Creeper { " +
                super.toString() +
                ", esElectrico=" + esElectrico +
                " } " ;
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
                {"¿Es eléctrico?", esElectrico ? "Sí" : "No"}
        });
    }

    @Override
    public String[] aFila() {
        return new String[]{
                String.format("%d" ,getId()),
                getNombre(),
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
                isEsBebe()? "Sí" : "No"};
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON

    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("esElectrico",esElectrico);
            j.put("tipo","Creeper");

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
            setEsElectrico(j.getBoolean("esElectrico"));

            exito = true; //Si llegó hasta acá sin tirar una Exception funcionó

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }

}
