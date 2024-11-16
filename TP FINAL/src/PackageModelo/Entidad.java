package PackageModelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Entidad {

    //todo.ATRIBUTOS//
    protected String nombre;
    protected double vida;
    protected double danio;
    protected static int autoincremental=1;
    protected int id;

    //todo.CONSTRUCTORES//
    public Entidad(String nombre, double vida, double danio) {
        this.nombre = nombre;
        this.vida = vida;
        this.danio = danio;
        this.id = autoincremental++;
    }
    public Entidad() {
    }



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

    //todo.JSON
    public JSONObject toJSON(){
        JSONObject j = new JSONObject();

        try {
            j.put("nombre", nombre);
            j.put("vida", vida);
            j.put("danio", danio);
            j.put("id", id);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return j;
    }
}
