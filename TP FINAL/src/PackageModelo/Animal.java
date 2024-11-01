package PackageModelo;
import PackageEnum.TipoAlimentacion;
import PackageEnum.TipoHabitat;

import java.util.ArrayList;

public abstract class Animal extends Mob{
    ///todo.ATRIBUTOS///
    protected TipoHabitat tipoHabitat;
    protected TipoAlimentacion tipoAlimentacion;

    ///todo.CONSTRUCTORES///
    public Animal(String nombre, double vida, double danio, ArrayList<String> drops, boolean esBebe, TipoHabitat tipoHabitat, TipoAlimentacion tipoAlimentacion) {
        super(nombre, vida, danio, drops, esBebe);
        this.tipoHabitat = tipoHabitat;
        this.tipoAlimentacion = tipoAlimentacion;
    }
    public Animal() {
    }

    ///todo.GETS Y SETS///
    public TipoHabitat getTipoHabitat() {
        return tipoHabitat;
    }

    public void setTipoHabitat(TipoHabitat tipoHabitat) {
        this.tipoHabitat = tipoHabitat;
    }

    public TipoAlimentacion getTipoAlimentacion() {
        return tipoAlimentacion;
    }

    public void setTipoAlimentacion(TipoAlimentacion tipoAlimentacion) {
        this.tipoAlimentacion = tipoAlimentacion;
    }
}
