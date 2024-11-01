package PackageModelo;

import PackageEnum.TipoZombie;
import PackageInterfaces.IAtacar;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Zombie extends Mob implements IAtacar {
    ///todo.ATRIBUTOS///
    protected TipoZombie tipoZombie;

    ///todo.CONSTRUCTORES///

    public Zombie(String nombre, double vida, double danio, ArrayList<String> drops, boolean esBebe, TipoZombie tipoZombie) {
        super(nombre, vida, danio, drops, esBebe);
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
            zombie.setNombre(jsonObject.getString("nombre"));
            zombie.setVida(jsonObject.getDouble("vida"));
            zombie.setDanio(jsonObject.getDouble("danio"));
            //zombie.setDrops(jsonObject.getString("drops"));
            zombie.setEsBebe(jsonObject.getBoolean("esBebe"));
            //zombie.setTipoZombie(jsonObject.get("tipoZombie"));
        } catch (JSONException exc) {
            exc.printStackTrace();
        }

        return zombie;
    }
}
