package PackageModelo;

import PackageEnum.TipoEsqueleto;

public class Esqueleto extends Hostil {

    //todo.ATRIBUTO
    protected TipoEsqueleto tipo;

    //todo.CONSTRUCTOR
    public Esqueleto(String nombre, double vida, double danio, boolean nocturno, TipoEsqueleto tipo) {
        super(nombre, vida, danio, nocturno);
        this.tipo = tipo;
    }
    public Esqueleto() {
    }
    //todo.GET Y SET//
    public TipoEsqueleto getTipo() {
        return tipo;
    }
    public void setTipo(TipoEsqueleto tipo) {
        this.tipo = tipo;
    }

    //todo.METODOS
    @Override
    public String emitirSonido() {
        return "*sonido de huesos*";
    }

    @Override
    public String ataque(int IDMob) {
        return "Le tira una flecha al mob "+IDMob +" quitandole " +danio +" puntos de vida.";
    }

}
