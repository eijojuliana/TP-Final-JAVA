package PackageModelo;

public class Oveja extends Animal{
    ///todo.ATRIBUTO
    public String color;

    public boolean tieneLana;

    ///todo.CONSTRUCTOR

    public Oveja(String color, boolean tieneLana) {
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

    @Override
    public String emitirSonido() {
        return "MEEE (Traduccion automatica a ingles lanudo:yoyoyoyoyo)";
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
