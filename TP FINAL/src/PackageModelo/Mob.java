package PackageModelo;

import PackageModelo.Entidad;

import java.util.ArrayList;

public abstract class Mob extends Entidad {

    //todo.ATRIBUTOS//
    protected ArrayList<String> drops;
    protected boolean esBebe;

    //todo.CONSTRUCTORES//
    public Mob(String nombre, double vida, double danio, ArrayList<String> drops, boolean esBebe) {
        super(nombre, vida, danio);
        this.drops = drops;
        this.esBebe = esBebe;
    }

    public Mob() {
        drops = new ArrayList<>();
    }

    //todo.Metodos//
    @Override
    public String toString() {
        return "Mob{" +
                "drops=" + drops +
                "} " + super.toString();
    }

    public abstract String emitirSonido();
}
