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
    public String getColor() {
        return color;
    }
    public boolean isTieneLana() {
        return tieneLana;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public void setTieneLana(boolean tieneLana) {
        this.tieneLana = tieneLana;
    }

    ///todo.METODOS
    @Override
    public String toString() {
        return "Oveja{" +
                "color='" + color + '\'' +
                ", tieneLana=" + tieneLana +
                "} " + super.toString();
    }

    public String esquilar(){
        tieneLana = false;
        return "shuck shuck *cae la lana*";
    }
    public String crecerLana(){
        tieneLana = true;
        return "Tiene lanita crecida";
    }
}
