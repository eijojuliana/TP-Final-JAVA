package PackageModelo;

import PackageEnum.Profesion;
import PackageEnum.TipoZombie;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.IFila;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class Aldeano extends Mob implements IConversionJSON, ITabla, IFila {
    //todo.ATRIBUTOS
    private Profesion profesion;

    //todo.CONSTRUCTOR

    public Aldeano(String nombre, double vida, double danio, boolean esBebe, Profesion profesion) {
        super(nombre, 10.0, 0.0, Aldeano.class.getSimpleName(), esBebe);
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
                profesion.equalsIgnoreCase(Profesion.DESEMPLEADO.name()) ||
                profesion.equalsIgnoreCase(Profesion.VAGO.name()) ||
                profesion.equalsIgnoreCase(Profesion.ALBANIL.name()) ||
                profesion.equalsIgnoreCase(Profesion.HERRERO.name()) ||
                profesion.equalsIgnoreCase(Profesion.BIBLIOTECARIO.name()) ||
                profesion.equalsIgnoreCase(Profesion.CARNICERO.name()) ||
                profesion.equalsIgnoreCase(Profesion.CARTOGRAFO.name()) ||
                profesion.equalsIgnoreCase(Profesion.CLERIGO.name()) ||
                profesion.equalsIgnoreCase(Profesion.FLECHERO.name()) ||
                profesion.equalsIgnoreCase(Profesion.GRANJERO.name()) ||
                profesion.equalsIgnoreCase(Profesion.PASTOR.name()) ||
                profesion.equalsIgnoreCase(Profesion.PELETERO.name()) ||
                profesion.equalsIgnoreCase(Profesion.PESCADOR.name())
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
    public String aTabla() {
        return AsciiTable.getTable(new String[][] {
                {"Mob", getTipo() },
                {"ID", String.format("%d" ,getId()) },
                {"Nombre", getNombre()},
                {"Vida", String.format("%.2f", getVida())},
                {"Daño", String.format("%.2f", getDanio())},
                {"¿Es bebé?", esBebe ? "Sí" : "No"},
                {"Profesion", profesion.name()}
        });
    }

    @Override
    public String[] aFila() {
        return new String[]{
                String.format("%d" ,getId()),
                getNombre(),
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
                isEsBebe()? "Sí" : "No",
                getProfesion().name()};
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("profesion",profesion);
            j.put("tipo","Aldeano");
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
