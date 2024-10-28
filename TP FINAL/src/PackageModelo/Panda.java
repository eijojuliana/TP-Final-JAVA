package PackageModelo;

public class Panda extends Pasivo{
    ///todo.ATRIBUTOS
    public String color;
    ///todo.CONSTRUCTOR


    public Panda(String nombre, double vida, double danio, boolean esBebe, String color) {
        super(nombre, vida, danio, esBebe);
        this.color = color;
    }

    public Panda(){}

    ///todo.GET Y SET

    ///todo.METODO
    public String Rodar(){
        return "*Giro ,me caigo, me levanto, vuelvo a girar*";
    }

    @Override
    public String emitirSonido() {
        return "nom nom nom *comiendo bambu modo panda*";
    }
}
