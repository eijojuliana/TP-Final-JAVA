package PackageModelo;
import PackageEnum.TipoAlimentacion;

import java.util.ArrayList;

public abstract class Animal extends Mob{
    ///todo.ATRIBUTOS///
    protected TipoAlimentacion tipoAlimentacion;

    ///todo.CONSTRUCTORES///
    public Animal(String nombre, double vida, double danio, String tipo, boolean esBebe, TipoAlimentacion tipoAlimentacion) {
        super(nombre, vida, danio, tipo , esBebe);
        this.tipoAlimentacion = tipoAlimentacion;
    }
    public Animal() {
    }

    ///todo.GETS Y SETS///
    public TipoAlimentacion getTipoAlimentacion() {
        return tipoAlimentacion;
    }
    public void setTipoAlimentacion(TipoAlimentacion tipoAlimentacion) {
        this.tipoAlimentacion = tipoAlimentacion;
    }
    ///todo.METODOS///
    @Override
    public String toString() {
        return "Animal{" +
                "tipoAlimentacion=" + tipoAlimentacion +
                "} " + super.toString();
    }
}
