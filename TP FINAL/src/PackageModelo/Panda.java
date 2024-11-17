package PackageModelo;

import PackageEnum.Gen_Panda;
import PackageEnum.TipoAlimentacion;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Panda extends Animal{
    ///todo.ATRIBUTOS
    protected Gen_Panda gen;
    ///todo.CONSTRUCTOR

    public Panda(String nombre, double vida, double danio, boolean esBebe, TipoAlimentacion tipoAlimentacion, Gen_Panda gen ) {
        super(nombre, vida, danio, Panda.class.getName(), esBebe, tipoAlimentacion);
        this.gen = gen;
    }

    public Panda(){}

    ///todo.GET Y SET
    public Gen_Panda getGen() {
        return gen;
    }
    public void setGen(Gen_Panda gen) {
        this.gen = gen;
    }


    ///todo.METODO
    public String Rodar(){
        return "*Giro ,me caigo, me levanto, vuelvo a girar*";
    }

    @Override
    public String emitirSonido() {
        return "nom nom nom *comiendo bambu modo panda*";
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
            jsonObject.put("gen",gen);

        } catch (JSONException exc) {
            exc.printStackTrace();
        }
        return jsonObject;
    }

    public Panda convertirAZombie (JSONObject jsonObject) {
        Panda p = new Panda();

        try {
            p.setNombre(jsonObject.getString("nombre"));
            p.setVida(jsonObject.getDouble("vida"));
            p.setDanio(jsonObject.getDouble("danio"));
            //p.setDrops(jsonObject.getString("drops"));
            p.setEsBebe(jsonObject.getBoolean("esBebe"));
            //p.setGen();
        } catch (JSONException exc) {
            exc.printStackTrace();
        }

        return p;
    }
}
