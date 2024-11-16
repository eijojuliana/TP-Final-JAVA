package PackageModelo;

import PackageEnum.TipoZombie;
import PackageInterfaces.IAtacar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Zombie extends Mob implements IAtacar {
    ///todo.ATRIBUTOS///
    protected TipoZombie tipoZombie;

    ///todo.CONSTRUCTORES///

    public Zombie(String nombre, double vida, double danio, boolean esBebe, TipoZombie tipoZombie) {
        super(nombre, vida, danio, esBebe);
        this.tipoZombie = tipoZombie;
    }

    public Zombie() {
    }

    ///todo.GETS Y SETS///
    public TipoZombie getTipoZombie() {
        return tipoZombie;
    }
    public void setTipoZombie(TipoZombie tipoZombie) {
        this.tipoZombie = tipoZombie;
    }
    public void setTipoZombie(String tipoZombie) {
        if (
                tipoZombie.equals(TipoZombie.COMUN.name()) ||
                tipoZombie.equals(TipoZombie.MOMIFICADO.name()) ||
                tipoZombie.equals(TipoZombie.CHIQUITO.name()) ||
                tipoZombie.equals(TipoZombie.ALDEANO.name()) ||
                tipoZombie.equals(TipoZombie.AHOGADO.name())
        ) this.tipoZombie = TipoZombie.valueOf(tipoZombie);
    }

    ///todo.TO STRING///
    @Override
    public String toString() {
        return "Zombie{" +
                "tipoZombie=" + tipoZombie +
                "} " + super.toString();
    }

    @Override
    public String emitirSonido() {
        return "ROOAWR ROOAWR";
    }

    @Override
    public String ataque(int IDMob) {
        return "Golpea al mob "+IDMob+", quitandole"+danio+"puntos de vida";
    }

    ///todo.JSON///
    public JSONObject convertirAJSON () {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("nombre", nombre);
            jsonObject.put("vida", vida);
            jsonObject.put("danio", danio);
            jsonObject.put("drops", drops);
            jsonObject.put("esBebe", esBebe);
            jsonObject.put("tipoZombie", tipoZombie);
        } catch (JSONException exc) {
            exc.printStackTrace();
        }

        return jsonObject;
    }

    public Zombie convertirAZombie (JSONObject jsonObject) {
        Zombie zombie = new Zombie();

        try {
            JSONArray jsonArray = new JSONArray();
            ArrayList<String> dropsAux = new ArrayList<>();

            for (int i=0; i<jsonArray.length();i++){
                dropsAux.add(jsonArray.getString(i));
            }
            
            zombie.setDrops(dropsAux);
            zombie.setNombre(jsonObject.getString("nombre"));
            zombie.setVida(jsonObject.getDouble("vida"));
            zombie.setDanio(jsonObject.getDouble("danio"));
            zombie.setEsBebe(jsonObject.getBoolean("esBebe"));
            zombie.setTipoZombie(jsonObject.getString("tipoZombie"));
        } catch (JSONException exc) {
            exc.printStackTrace();
        }

        return zombie;
    }
}
