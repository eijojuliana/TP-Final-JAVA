package PackageModelo;

import PackageEnum.Profesion;
import PackageEnum.TipoZombie;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageInterfaces.IConversionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Aldeano extends Mob implements IConversionJSON {
    //todo.ATRIBUTOS
    protected Profesion profesion;

    //todo.CONSTRUCTOR

    public Aldeano(String nombre, double vida, double danio, boolean esBebe, Profesion profesion) {
        super(nombre, vida, danio, Aldeano.class.getSimpleName(), esBebe);
        this.profesion = profesion;
    }

    public Aldeano() {
    }
    //todo.GET Y SET
    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        if (
                profesion.equals(Profesion.DESEMPLEADO.name()) ||
                        profesion.equals(Profesion.VAGO.name()) ||
                        profesion.equals(Profesion.ALBANIL.name()) ||
                        profesion.equals(Profesion.HERRERO.name()) ||
                        profesion.equals(Profesion.BIBLIOTECARIO.name()) ||
                        profesion.equals(Profesion.CARNICERO.name()) ||
                        profesion.equals(Profesion.CARTOGRAFO.name()) ||
                        profesion.equals(Profesion.CLERIGO.name()) ||
                        profesion.equals(Profesion.FLECHERO.name()) ||
                        profesion.equals(Profesion.GRANJERO.name()) ||
                        profesion.equals(Profesion.PASTOR.name()) ||
                        profesion.equals(Profesion.PELETERO.name()) ||
                        profesion.equals(Profesion.PESCADOR.name())
        ) this.profesion = Profesion.valueOf(profesion);
        else throw new Valor_de_atributo_no_valido_Exception("Profesión ingresada inválida.");
    }

    //todo.METODOS
    @Override
    public String toString() {
        return "Aldeano{" +
                super.toString() +
                "profesion=" + profesion +
                "} ";
    }

    @Override
    public String emitirSonido() {
        return "MHHH (Te critica en aldeano)";
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("profesion",profesion);

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
            setProfesion(j.getString("profesion"));
            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }

}
