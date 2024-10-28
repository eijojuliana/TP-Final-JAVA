package PackageModelo;

public class Oveja extends Pasivo{
    ///todo.ATRIBUTO
    public String color;

    public boolean tieneLana;

    ///todo.CONSTRUCTOR


    public Oveja(String nombre, double vida, double danio, boolean esBebe, String color, boolean tieneLana) {
        super(nombre, vida, danio, esBebe);
        this.color = color;
        this.tieneLana = tieneLana;
    }

    public Oveja() {}


    ///todo.GET Y SET

    ///todo.METODOS
    public String esquilar(){
        tieneLana = false;
        return "shuck shuck *cae la lana*";
    }

    public String crecerLana(){
        tieneLana = true;
        return "Tiene lanita crecida";
    }
}
