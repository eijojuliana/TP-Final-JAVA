package PackageModelo;

import PackageInterfaces.IConversionJSON;
import PackageInterfaces.IFila;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class Creeper extends Mob implements IConversionJSON, ITabla, IFila {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo ATRIBUTOS
    private boolean esElectrico;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo CONSTRUCTORES
    public Creeper(String nombre, boolean esBebe, boolean esElectrico) {
        super(nombre, 10.0, 21.5, Creeper.class.getSimpleName(), false);
        inicializar_drops();
        this.esElectrico = esElectrico;
    }
    public Creeper() {
        setVida(10);
        setDanio(21.5);
        setTipo(Creeper.class.getSimpleName());
    }

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
                getTipo(),
                getNombre(),
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
                isEsBebe()? "Sí" : "No"};
    }

    private void inicializar_drops() {
        ArrayList<String> drops = new ArrayList<>();
        drops.add("Pólvora");
        setDrops(drops);
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
