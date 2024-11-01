package PackageModelo;

import PackageInterfaces.IAtacar;

import java.util.ArrayList;

public class Creeper extends Mob implements IAtacar {
    //todo.ATRIBUTOS
    protected boolean esElectrico;

    //todo.CONSTRUCTORES

    public Creeper(String nombre, double vida, double danio, ArrayList<String> drops, boolean esBebe, boolean esElectrico) {
        super(nombre, vida, danio, drops, esBebe);
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
