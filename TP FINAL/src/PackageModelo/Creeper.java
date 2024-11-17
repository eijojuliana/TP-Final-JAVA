package PackageModelo;

import PackageInterfaces.IAtacar;
import PackageInterfaces.IConversionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Creeper extends Mob implements IAtacar, IConversionJSON {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo ATRIBUTOS
    protected boolean esElectrico;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo CONSTRUCTORES

    public Creeper(String nombre, double vida, double danio, boolean esBebe, boolean esElectrico) {
        super(nombre, vida, danio, Creeper.class.getName(), esBebe);
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
    public String emitirSonido() {
        return "Allahu akbar fss... *explota*";
    }

    @Override
    public String ataque(int IDMob) {
        return "Le explota en toda la cabeza al mod" + IDMob + ", quitándole " + danio;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON

    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("esElectrico",esElectrico);

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
