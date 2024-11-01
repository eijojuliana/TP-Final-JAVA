package PackageModelo;

import PackageEnum.TipoZombie;
import PackageInterfaces.IAtacar;

import java.util.ArrayList;

public class Zombie extends Mob implements IAtacar {
    ///todo.ATRIBUTOS///
    protected TipoZombie tipoZombie;

    ///todo.CONSTRUCTORES///

    public Zombie(String nombre, double vida, double danio, ArrayList<String> drops, boolean esBebe, TipoZombie tipoZombie) {
        super(nombre, vida, danio, drops, esBebe);
        this.tipoZombie = tipoZombie;
    }

    public Zombie() {
    }

    ///todo.GETS Y SETS///
    public TipoZombie getTipoZombie() {
        return tipoZombie;
    }

    public void setTipoZombie(TipoZombie tipoZombie) {
        this.tipoZombie = tipoZombie;
    }

    ///todo.TO STRING///
    @Override
    public String toString() {
        return "Zombie{" +
                "tipoZombie=" + tipoZombie +
                "} " + super.toString();
    }

    @Override
    public String emitirSonido() {
        return "ROOAWR ROOAWR";
    }

    @Override
    public String ataque(int IDMob) {
        return "Golpea al mob "+IDMob+", quitandole"+danio+"puntos de vida";
    }
}
