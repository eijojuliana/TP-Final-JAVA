package PackageModelo;
public abstract class Neutro extends Mob {
    ///todo.ATRIBUTOS///
    protected boolean fueAtacado;

    ///todo.CONSTRUCTORES///
    public Neutro(String nombre, double vida, double danio, boolean fueAtacado) {
        super(nombre, vida, danio);
        this.fueAtacado = fueAtacado;
    }
    public Neutro() {
    }

    ///todo.GETS Y SETS///
    public boolean isFueAtacado() {
        return fueAtacado;
    }
    public void setFueAtacado(boolean fueAtacado) {
        this.fueAtacado = fueAtacado;
    }

    ///todo.TO STRING///
    @Override
    public String toString() {
        return "Neutro{" +
                "fueAtacado=" + fueAtacado +
                "} " + super.toString();
    }

    ///todo.METODOS ABSTRACTOS///
    @Override
    public abstract String emitirSonido();
}
