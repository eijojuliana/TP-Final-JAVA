package PackageContenedores;
import PackageJSON.JSONUtiles;
import PackageModelo.Aldeano;
import PackageModelo.Animal;
import PackageModelo.Entidad;
import PackageModelo.Player;

public class Aldea {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    /// todo.Atributos///

    protected AlmacenamientoNPC<Animal> ArrayAnimales;
    protected AlmacenamientoNPC<Aldeano> ArrayAldeanos;
    protected AlmacenamientoNPC<Player> jugadores;
    protected Carcel carcel;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    ///todo.Constructores///

    public Aldea() {
        ArrayAnimales = new AlmacenamientoNPC<>();
        ArrayAldeanos = new AlmacenamientoNPC<>();
        jugadores = new AlmacenamientoNPC<>();
        carcel=new Carcel();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    /// todo.Metodos

    @Override
    public String toString() {
        return "Aldea{" +
                "ArrayAnimales=" + ArrayAnimales +
                ", ArrayAldeanos=" + ArrayAldeanos +
                ", Jugadores=" + jugadores +
                ", Carcel=" + carcel +
                '}';
    }


    // public boolean agregarAldeano(){

    public boolean agregarPlayer(Player p){
        boolean exito = false;

        exito = jugadores.agregar(p);

        JSONUtiles.grabarUnJson(jugadores.toJSON(),"ArchivoUsuarios");

        return exito;
    }















}
