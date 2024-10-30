package PackageModelo;

import PackageModelo.Entidad;

import java.util.ArrayList;

public abstract class Mob extends Entidad {

    //todo.ATRIBUTOS//
    protected ArrayList<String> drops;

    //todo.CONSTRUCTORES//
    public Mob(String nombre, double vida, double danio) {
        super(nombre, vida, danio);
        drops = new ArrayList<>();
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
