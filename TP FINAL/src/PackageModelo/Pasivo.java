package PackageModelo;

public class Pasivo extends Mob{
    //ATRIBUTOS
    protected boolean esBebe;

    //CONSTRUCTORES

    public Pasivo(String nombre, double vida, double danio, boolean esBebe) {
        super(nombre, vida, danio);
        this.esBebe = esBebe;
    }

    public Pasivo() {

    }

    //GETTER AND SETTER

    //SOBREESCRITURA - MÉTODOS
    @Override
    public String emitirSonido() {
        return null;
    }
}
