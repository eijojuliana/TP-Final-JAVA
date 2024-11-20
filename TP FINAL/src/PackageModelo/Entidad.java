package PackageModelo;

import PackageInterfaces.IConversionJSON;
import PackageInterfaces.IFila;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Entidad implements IConversionJSON, ITabla, IFila {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.ATRIBUTOS//

    protected String nombre;
    protected double vida;
    protected double danio;
    protected static int autoincremental=0;
    protected int id;
    protected String tipo;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.CONSTRUCTORES//

    public Entidad(String nombre, double vida, double danio, String tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.danio = danio;
        this.id = ++autoincremental;
        this.tipo = tipo;
    }
    public Entidad() {
        id = ++autoincremental;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    ///todo.GETS AND SETS///

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setVida(double vida) {
        this.vida = vida;
    }
    public void setDanio(double danio) {
        this.danio = danio;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }
    public double getVida() {
        return vida;
    }
    public double getDanio() {
        return danio;
    }
    public int getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    ///todo.Metodos///
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidad entidad)) return false;
        return getId() == entidad.getId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return  "nombre='" + nombre + '\'' +
                ", vida=" + vida +
                ", danio=" + danio +
                ", id=" + id ;
    }

    @Override
    public String aTabla() {
        return AsciiTable.getTable(new String[][]{
                {"ID", String.format("%d" ,getId()) },
                {"Nombre", getNombre()},
                {"Vida", String.format("%.2f", getVida())},
                {"Daño", String.format("%.2f", getDanio())},
                {"Mob", getTipo() },
        });
    }

    @Override
    public String[] aFila() {
        return new String[]{
                String.format("%d" ,getId()),
                getNombre(),
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
                getTipo() };
    }
    public static String[] aFila(Entidad e) {
        return new String[]{
                String.format("%d" ,e.getId()),
                e.getNombre(),
                String.format("%.2f", e.getVida()),
                String.format("%.2f", e.getDanio()),
                e.getTipo() };
    }


    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.JSON

    @Override
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();

        try {
            j.put("nombre", nombre);
            j.put("vida", vida);
            j.put("danio", danio);
            j.put("id", id);
            j.put("tipo", tipo);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return j;
    }

    @Override
    public boolean fromJSON(JSONObject j) {
        boolean exito = false;

        try {
            setNombre(j.getString("nombre"));
            setVida(j.getDouble("vida"));
            setDanio(j.getDouble("danio"));
            setId(j.getInt("id"));
            setTipo(j.getString("tipo"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }
}
