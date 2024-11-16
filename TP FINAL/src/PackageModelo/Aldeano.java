package PackageModelo;

import PackageEnum.Profesion;

import java.util.ArrayList;

public class Aldeano extends Mob{
    //todo.ATRIBUTOS
    protected Profesion profesion;
    protected ArrayList<String> tradeos;
    protected int nivel;

    //todo.CONSTRUCTOR

    public Aldeano(String nombre, double vida, double danio, boolean esBebe, Profesion profesion, ArrayList<String> tradeos, int nivel) {
        super(nombre, vida, danio, esBebe);
        this.profesion = profesion;
        this.tradeos = tradeos;
        this.nivel = nivel;
    }

    public Aldeano() {
    }
    //todo.GET Y SET
    public Profesion getProfesion() {
        return profesion;
    }
    public ArrayList<String> getTradeos() {
        return tradeos;
    }
    public int getNivel() {
        return nivel;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }
    public void setTradeos(ArrayList<String> tradeos) {
        this.tradeos = tradeos;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    //todo.METODOS
    @Override
    public String toString() {
        return "Aldeano{" +
                "profesion=" + profesion +
                ", tradeos=" + tradeos +
                ", nivel=" + nivel +
                "} " + super.toString();
    }

    //Habria que rellenarla y hacer otra en donde entre por parametro un string (item)
    public String tradea(int esmeraldas, int libro){
        return " ";
    }

    @Override
    public String emitirSonido() {
        return "MHHH (Te critica en aldeano)";
    }
}
