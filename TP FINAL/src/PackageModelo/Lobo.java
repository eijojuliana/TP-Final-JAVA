package PackageModelo;

import PackageEnum.TipoAlimentacion;

import java.util.ArrayList;

public class Lobo extends Animal{
    ///todo.ATRIBUTOS///
    protected boolean domesticado;
    protected int IDduenio;

    ///todo.CONSTRUCTORES///
    public Lobo(String nombre, double vida, double danio, boolean esBebe, TipoAlimentacion tipoAlimentacion, boolean domesticado, int IDduenio) {
        super(nombre, vida, danio, esBebe, tipoAlimentacion);
        this.domesticado = domesticado;
        this.IDduenio = IDduenio;
    }

    public Lobo() {
    }

    ///todo.GETS Y SETS///
    public boolean isDomesticado() {
        return domesticado;
    }
    public int getIDduenio() {
        return IDduenio;
    }

    public void setDomesticado(boolean domesticado) {
        this.domesticado = domesticado;
    }
    public void setIDduenio(int IDduenio) {
        this.IDduenio = IDduenio;
    }

    ///todo.TO STRING///
    @Override
    public String toString() {
        return "Lobo{" +
                "domesticado=" + domesticado +
                ", IDduenio=" + IDduenio +
                "} " + super.toString();
    }

    ///todo.METODOS ABSTRACTOS///
    @Override
    public String emitirSonido() {
        return "*AULLANDO COMO LOBA*AUUUUU";
    }
}
