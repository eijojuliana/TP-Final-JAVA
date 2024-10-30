package PackageModelo;

import PackageEnum.TipoHabitat;

public class Animal_Herbivoro extends Pasivo {

    //todo.ATRIBUTOS
    protected TipoHabitat tipo;

    //todo.CONSTRUCTOR
    public Animal_Herbivoro(String nombre, double vida, double danio, boolean esBebe, TipoHabitat tipo) {
        super(nombre, vida, danio, esBebe);
        this.tipo = tipo;
    }
    public Animal_Herbivoro() {
    }
    //todo.GET Y SET
    public TipoHabitat getTipo() {
        return tipo;
    }
    public void setTipo(TipoHabitat tipo) {
        this.tipo = tipo;
    }

    //todo.Metodos
    @Override
    public String toString() {
        return "Animal_Herbivoro{" +
                "tipo=" + tipo +
                "} " + super.toString();
    }

}
