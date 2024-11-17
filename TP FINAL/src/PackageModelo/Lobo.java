package PackageModelo;

import PackageEnum.TipoAlimentacion;
import PackageInterfaces.IConversionJSON;
import org.json.JSONException;
import org.json.JSONObject;

import javax.management.MalformedObjectNameException;
import java.util.ArrayList;

public class Lobo extends Animal implements IConversionJSON {
    ///todo.ATRIBUTOS///
    protected boolean domesticado;
    protected int IDduenio;

    ///todo.CONSTRUCTORES///
    public Lobo(String nombre, double vida, double danio, boolean esBebe, TipoAlimentacion tipoAlimentacion, boolean domesticado, int IDduenio) {
        super(nombre, vida, danio, Lobo.class.getSimpleName(), esBebe, tipoAlimentacion);
        this.domesticado = domesticado;
        this.IDduenio = IDduenio;
    }

    public Lobo() {
    }

    ///todo.GETS Y SETS///
    public boolean isDomesticado() {
        return domesticado;
    }
    public int getIDduenio() {
        return IDduenio;
    }

    public void setDomesticado(boolean domesticado) {
        this.domesticado = domesticado;
    }
    public void setIDduenio(int IDduenio) {
        this.IDduenio = IDduenio;
    }

    ///todo.Metodoss///
    @Override
    public String toString() {
        return "Lobo{" +
                "domesticado=" + domesticado +
                ", IDduenio=" + IDduenio +
                "} " + super.toString();
    }

    public String domesticarLobo(int id){
         String mensaje;
            setDomesticado(true);
            setIDduenio(id);
            mensaje="FELICIDADES!! A DOMESTICADO A SU NUEVO LOBO";
        return mensaje;
    }

    ///todo.METODOS ABSTRACTOS///
    @Override
    public String emitirSonido() {
        return "*AULLANDO COMO LOBA*AUUUUU";
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("domesticado", domesticado);
            j.put("IDduenio", IDduenio);

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
            setDomesticado(j.getBoolean("domesticado"));
            setIDduenio(j.getInt("IDduenio"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
