package PackageModelo;
public abstract class Hostil extends Mob{
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
}
