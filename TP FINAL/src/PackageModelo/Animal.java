package PackageModelo;
import PackageEnum.TipoAlimentacion;
import PackageEnum.TipoZombie;
import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.IFila;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Animal extends Mob implements IConversionJSON, IFila {
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
    public void setTipoAlimentacion(String tipoAlimentacion) throws Atributo_vacio_Exception{
        if (tipoAlimentacion == null)
        {
            throw new Atributo_vacio_Exception("El tipo de alimentación no puede estar vacío.");
        }

        if (    tipoAlimentacion.equalsIgnoreCase(TipoAlimentacion.CARNIVORO.name()) ||
                tipoAlimentacion.equalsIgnoreCase(TipoAlimentacion.HERBIVORO.name()) ||
                tipoAlimentacion.equalsIgnoreCase(TipoAlimentacion.OMNIVORO.name())
        ) this.tipoAlimentacion = TipoAlimentacion.valueOf(tipoAlimentacion);
    }

    ///todo.METODOS///
    @Override
    public String toString() {
        return "Animal{" +
                "tipoAlimentacion=" + tipoAlimentacion +
                "} " + super.toString();
    }

    @Override
    public String[] aFila() {
        return new String[]{
                String.format("%d" ,getId()),
                getNombre(),
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
                isEsBebe()? "Sí" : "No",
                getTipoAlimentacion().name()};
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
