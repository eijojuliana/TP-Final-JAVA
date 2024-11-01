package PackageModelo;

import PackageEnum.Gen_Panda;

public class Panda extends Pasivo{
    ///todo.ATRIBUTOS
    protected Gen_Panda gen;
    protected String color;
    ///todo.CONSTRUCTOR
    public Panda(String nombre, double vida, double danio, boolean esBebe, String color, Gen_Panda gen) {
        super(nombre, vida, danio, esBebe);
        this.color = color;
        this.gen = gen;
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
