package PackageModelo;

public class Esqueleto extends Hostil {

    //todo.CONSTRUCTOR
    public Esqueleto(String nombre, double vida, double danio, boolean nocturno) {
        super(nombre, vida, danio, nocturno);
    }
    public Esqueleto() {
    }

    //todo.METODOS
    @Override
    public String emitirSonido() {
        return "*sonido de huesos*";
    }

    @Override
    public String ataque(int IDMob) {
        return "Le tira una flecha al mob "+IDMob +" quitandole " +danio +".";
    }

}
