package PackageModelo;
import PackageEnum.TipoAlimentacion;
import PackageEnum.TipoZombie;
import PackageInterfaces.IConversionJSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Animal extends Mob implements IConversionJSON {
    ///todo.ATRIBUTOS///
    protected TipoAlimentacion tipoAlimentacion;

    ///todo.CONSTRUCTORES///
    public Animal(String nombre, double vida, double danio, String tipo, boolean esBebe, TipoAlimentacion tipoAlimentacion) {
        super(nombre, vida, danio, tipo , esBebe);
        this.tipoAlimentacion = tipoAlimentacion;
    }
    public Animal() {
    }

    ///todo.GETS Y SETS///
    public TipoAlimentacion getTipoAlimentacion() {
        return tipoAlimentacion;
    }
    public void setTipoAlimentacion(String tipoAlimentacion) {
        if (
                tipoAlimentacion.equals(TipoAlimentacion.CARNIVORO.name()) ||
                        tipoAlimentacion.equals(TipoAlimentacion.HERBIVORO.name()) ||
                        tipoAlimentacion.equals(TipoAlimentacion.OMNIVORO.name())
        ) this.tipoAlimentacion = TipoAlimentacion.valueOf(tipoAlimentacion);
    }

    ///todo.METODOS///
    @Override
    public String toString() {
        return "Animal{" +
                "tipoAlimentacion=" + tipoAlimentacion +
                "} " + super.toString();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("tipoAlimentacion", tipoAlimentacion);

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
            setTipoAlimentacion(j.getString("tipoAlimentacion"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
