package PackageModelo;

public class Lobo extends Neutro{
    ///todo.ATRIBUTOS///
    protected boolean domesticado;

    ///todo.CONSTRUCTORES///
    public Lobo(String nombre, double vida, double danio, boolean fueAtacado, boolean domesticado) {
        super(nombre, vida, danio, fueAtacado);
        this.domesticado = domesticado;
    }
    public Lobo() {
    }

    ///todo.GETS Y SETS///
    public boolean isDomesticado() {
        return domesticado;
    }
    public void setDomesticado(boolean domesticado) {
        this.domesticado = domesticado;
    }
    ///todo.TO STRING///
    @Override
    public String toString() {
        return "Lobo{" +
                "domesticado=" + domesticado +
                "} " + super.toString();
    }
    ///todo.METODOS ABSTRACTOS///
    @Override
    public String emitirSonido() {
        return "*AULLANDO COMO LOBA*AUUUUU";
    }
}
