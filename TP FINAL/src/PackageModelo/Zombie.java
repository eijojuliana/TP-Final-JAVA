package PackageModelo;

import PackageEnum.TipoZombie;
public class Zombie extends Hostil{
    ///todo.ATRIBUTOS///
    protected TipoZombie tipoZombie;

    ///todo.CONSTRUCTORES///
    public Zombie(String nombre, double vida, double danio, boolean nocturno, TipoZombie tipoZombie) {
        super(nombre, vida, danio, nocturno);
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
