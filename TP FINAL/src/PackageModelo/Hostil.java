package PackageModelo;

import PackageInterfaces.IAtacar;

public abstract class Hostil extends Mob implements IAtacar {
    //Todo.ATRIBUTOS//
    protected boolean nocturno;

    //todo.CONSTRUCTORES///
    public Hostil(String nombre, double vida, double danio, boolean nocturno) {
        super(nombre, vida, danio);
        this.nocturno = nocturno;
    }
    public Hostil() {
    }
    ///todo.GETTERS Y SETTERS///
    public boolean isNocturno() {
        return nocturno;
    }
    public void setNocturno(boolean nocturno) {
        this.nocturno = nocturno;
    }
    ///todo.METODOS///
    @Override
    public String toString() {
        return "Hostil{" +
                "nocturno=" + nocturno +
                "} " + super.toString();
    }
    @Override
    public abstract String emitirSonido();
    @Override
    public abstract String ataque(int IDMob);
}
