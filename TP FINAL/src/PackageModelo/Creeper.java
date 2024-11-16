package PackageModelo;

import PackageInterfaces.IAtacar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Creeper extends Mob implements IAtacar {
    //todo.ATRIBUTOS
    protected boolean esElectrico;

    //todo.CONSTRUCTORES

    public Creeper(String nombre, double vida, double danio, ArrayList<String> drops, boolean esBebe, boolean esElectrico) {
        super(nombre, vida, danio, drops, esBebe);
        this.esElectrico = esElectrico;
    }
    public Creeper() {}

    //GETTER AND SETTER
    public boolean isEsElectrico() {
        return esElectrico;
    }
    public void setEsElectrico(boolean esElectrico) {
        this.esElectrico = esElectrico;
    }

    //SOBREESCRITURA
    @Override
    public String toString() {
        return "Creeper{" +
                "esElectrico=" + esElectrico +
                "} " + super.toString();
    }

    @Override
    public String emitirSonido() {
        return "Allahu akbar fss... *explota*";
    }

    @Override
    public String ataque(int IDMob) {
        return "Le explota en toda la cabeza al mod" + IDMob + ", quitándole " + danio;
    }

    //todo.JSON
    public JSONObject toJSON(){
        JSONObject j = new JSONObject();

        try {
            j.put("esElectrico",esElectrico);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return j;
    }

    public static Creeper JSONtoCreeper(JSONObject j){
        Creeper c = new Creeper();

        try{
            c.setNombre(j.getString("nombre"));
            c.setVida(j.getDouble("vida"));
            c.setDanio(j.getDouble("danio"));
            c.setEsBebe(j.getBoolean("esBebe"));
            c.setEsElectrico(j.getBoolean("esElectrico"));

            JSONArray jsonArray = j.getJSONArray("drops");
            ArrayList<String> drops = new ArrayList<>();
            for (int i=0; i< jsonArray.length(); i++){
                drops.add(jsonArray.getString(i));
            }
            c.setDrops(drops);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return c;
    }

}
