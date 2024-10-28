package PackageModelo;

import PackageInterfaces.IAtacar;

public class Creeper extends Hostil implements IAtacar {
    //todo.ATRIBUTOS
    protected boolean esElectrico;

    //todo.CONSTRUCTORES
    public Creeper(String nombre, double vida, double danio, boolean nocturno, boolean esElectrico) {
        super(nombre, vida, danio, nocturno);
        this.esElectrico = esElectrico;
    }
    public Creeper() {}

    //GETTER AND SETTER

    //SOBREESCRITURA
    @Override
    public String toString() {
        return "Creeper{" +
                "esElectrico=" + esElectrico +
                "} " + super.toString();
    }

    @Override
    public String emitirSonido() {
        return "Allahu akbar fss... *explota*";
    }

    @Override
    public String ataque(int IDMob) {
        return "Le explota en toda la cabeza al mod" + IDMob + ", quitándole " + danio;
    }
}
