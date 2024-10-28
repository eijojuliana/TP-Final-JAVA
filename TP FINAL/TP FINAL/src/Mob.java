public abstract class Mob extends Entidad{

    //todo.CONSTRUCTORES//
    public Mob(String nombre, double vida, double danio) {
        super(nombre, vida, danio);
    }
    public Mob() {
    }

    //todo.Metodos//
    @Override
    public String toString() {
        return "Mob{} " + super.toString();
    }

    public abstract String emitirSonido();
}
