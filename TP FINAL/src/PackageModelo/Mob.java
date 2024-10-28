package PackageModelo;

import PackageModelo.Entidad;

public abstract class Mob extends Entidad {

    //todo.CONSTRUCTORES//
    public Mob(String nombre, double vida, double danio) {
        super(nombre, vida, danio);
    }
    public Mob() {
    }

    //todo.Metodos//
    @Override
    public String toString() {
        return "PackageModelo.Mob{} " + super.toString();
    }

    public abstract String emitirSonido();
}
