package PackageModelo;

import PackageEnum.Gen_Panda;
import PackageEnum.TipoAlimentacion;

import java.util.ArrayList;

public class Panda extends Animal{
    ///todo.ATRIBUTOS
    protected Gen_Panda gen;
    protected String color;
    ///todo.CONSTRUCTOR

    public Panda(String nombre, double vida, double danio, ArrayList<String> drops, boolean esBebe, TipoAlimentacion tipoAlimentacion, Gen_Panda gen, String color) {
        super(nombre, vida, danio, drops, esBebe, tipoAlimentacion);
        this.gen = gen;
        this.color = color;
    }

    public Panda(){}

    ///todo.GET Y SET
    public Gen_Panda getGen() {
        return gen;
    }
    public String getColor() {
        return color;
    }

    public void setGen(Gen_Panda gen) {
        this.gen = gen;
    }
    public void setColor(String color) {
        this.color = color;
    }

    ///todo.METODO
    public String Rodar(){
        return "*Giro ,me caigo, me levanto, vuelvo a girar*";
    }

    @Override
    public String emitirSonido() {
        return "nom nom nom *comiendo bambu modo panda*";
    }
}
