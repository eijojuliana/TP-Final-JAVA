package PackageModelo;

import PackageEnum.Profesion;
import PackageEnum.TipoZombie;
import PackageInterfaces.IConversionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Aldeano extends Mob implements IConversionJSON {
    //todo.ATRIBUTOS
    protected Profesion profesion;
    protected ArrayList<String> tradeos;
    protected int nivel;

    //todo.CONSTRUCTOR

    public Aldeano(String nombre, double vida, double danio, boolean esBebe, Profesion profesion, ArrayList<String> tradeos, int nivel) {
        super(nombre, vida, danio, Aldeano.class.getSimpleName(), esBebe);
        this.profesion = profesion;
        this.tradeos = tradeos;
        this.nivel = nivel;
    }

    public Aldeano() {
    }
    //todo.GET Y SET
    public Profesion getProfesion() {
        return profesion;
    }
    public ArrayList<String> getTradeos() {
        return tradeos;
    }
    public int getNivel() {
        return nivel;
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
    }
    public void setTradeos(ArrayList<String> tradeos) {
        this.tradeos = tradeos;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }


    //todo.METODOS
    @Override
    public String toString() {
        return "Aldeano{" +
                "profesion=" + profesion +
                ", tradeos=" + tradeos +
                ", nivel=" + nivel +
                "} " + super.toString();
    }


    //Habria que rellenarla y hacer otra en donde entre por parametro un string (item)
    public String tradea(int esmeraldas, int libro){
        return " ";
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
            j.put("tradeos",tradeos);
            j.put("nivel",nivel);

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
            JSONArray jsonArray = j.getJSONArray("tradeos");
            for (int i=0; i< jsonArray.length(); i++){
                tradeos.add(jsonArray.getString(i));
            }
            setNivel(j.getInt("nivel"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }

}
