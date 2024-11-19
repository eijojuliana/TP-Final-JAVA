package PackageModelo;

import PackageInterfaces.IConversionJSON;
import PackageModelo.Entidad;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Mob extends Entidad implements IConversionJSON {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.ATRIBUTOS//
    protected ArrayList<String> drops;
    protected boolean esBebe;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.CONSTRUCTORES//
    public Mob(String nombre, double vida, double danio, String tipo, boolean esBebe) {
        super(nombre, vida, danio, tipo);
        this.drops = new ArrayList<>();
        this.esBebe = esBebe;
    }
    public Mob() {
        drops = new ArrayList<>();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.GETTER AND SETTER//
    public ArrayList<String> getDrops() {
        return drops;
    }
    public void setDrops(ArrayList<String> drops) {
        this.drops = drops;
    }
    public boolean isEsBebe() {
        return esBebe;
    }
    public void setEsBebe(boolean esBebe) {
        this.esBebe = esBebe;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.Metodos//
    @Override
    public String toString() {
        return "Mob{" +
                "drops=" + drops +
                ", esBebe=" + esBebe +
                "} " + super.toString();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;
        try {
            j = super.toJSON();
            j.put("drops",drops);
            j.put("esBebe",esBebe);

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
            setEsBebe(j.getBoolean("esBebe"));

            JSONArray jsonArray = j.getJSONArray("drops");
            for (int i=0; i< jsonArray.length(); i++){
                drops.add(jsonArray.getString(i));
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }
}
