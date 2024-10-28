package PackageModelo;

import java.util.ArrayList;
import java.util.Objects;

public class Entidad {
    //todo.ATRIBUTOS//
    protected String nombre;
    protected double vida;
    protected double danio;
    protected static int autoincremental=1;
    protected int id;
    protected ArrayList<String>drops;

    //todo.CONSTRUCTORES//
    public Entidad(String nombre, double vida, double danio) {
        this.nombre = nombre;
        this.vida = vida;
        this.danio = danio;
        this.id = autoincremental++;
        this.drops=new ArrayList<>();
    }
    public Entidad() {
        this.drops =new ArrayList<>();
    }
    ///todo.GETS///
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
    public ArrayList<String> getDrops() {
        return drops;
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
        return "PackageModelo.Entidad{" +
                "nombre='" + nombre + '\'' +
                ", vida=" + vida +
                ", danio=" + danio +
                ", id=" + id +
                ", drops=" + drops +
                '}';
    }
}
