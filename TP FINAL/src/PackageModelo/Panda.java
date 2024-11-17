package PackageModelo;

import PackageEnum.Gen_Panda;
import PackageEnum.TipoAlimentacion;
import PackageEnum.TipoZombie;
import PackageInterfaces.IConversionJSON;
import org.json.JSONException;
import org.json.JSONObject;

public class Panda extends Animal implements IConversionJSON {
    ///todo.ATRIBUTOS
    protected Gen_Panda gen;
    ///todo.CONSTRUCTOR

    public Panda(String nombre, double vida, double danio, boolean esBebe, TipoAlimentacion tipoAlimentacion, Gen_Panda gen ) {
        super(nombre, vida, danio, Panda.class.getSimpleName(), esBebe, tipoAlimentacion);
        this.gen = gen;
    }

    public Panda(){}

    ///todo.GET Y SET
    public Gen_Panda getGen() {
        return gen;
    }
    public void setGen(String gen) {
        if (
                gen.equals(Gen_Panda.NORMAL.name()) ||
                        gen.equals(Gen_Panda.AGRESIVO.name()) ||
                        gen.equals(Gen_Panda.PEREZOSO.name()) ||
                        gen.equals(Gen_Panda.ASUSTADIZO.name()) ||
                        gen.equals(Gen_Panda.JUGUETON.name()) ||
                        gen.equals(Gen_Panda.DEBIL.name()) ||
                        gen.equals(Gen_Panda.MARRON.name())
        ) this.gen = Gen_Panda.valueOf(gen);
    }


    @Override
    public String toString() {
        return "Panda{" +
                "gen=" + gen +
                "} " + super.toString();
    }
    ///todo.METODO///

    public String Rodar(){
        return "*Giro ,me caigo, me levanto, vuelvo a girar*";
    }

    @Override
    public String emitirSonido() {
        return "nom nom nom *comiendo bambu modo panda*";
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("gen", gen);

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
            setGen(j.getString("gen"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
