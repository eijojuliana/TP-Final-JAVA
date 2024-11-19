package PackageContenedores;
import PackageInterfaces.IConversionJSON;
import PackageJSON.JSONUtiles;
import PackageModelo.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Aldea {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    /// todo.Atributos///

    protected AlmacenamientoNPC<Animal> animales;
    protected AlmacenamientoNPC<Aldeano> aldeanos;
    protected AlmacenamientoNPC<Player> jugadores;
    protected AlmacenamientoNPC<Mob> hostiles;
    protected Carcel carcel;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    ///todo.Constructores///

    public Aldea() {
        animales = new AlmacenamientoNPC<>();
        aldeanos = new AlmacenamientoNPC<>();
        hostiles = new AlmacenamientoNPC<>();
        jugadores = new AlmacenamientoNPC<>();
        carcel=new Carcel();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    public AlmacenamientoNPC<Animal> getAnimales() {
        return animales;
    }
    public AlmacenamientoNPC<Aldeano> getAldeanos() {
        return aldeanos;
    }
    public AlmacenamientoNPC<Player> getJugadores() {
        return jugadores;
    }
    public AlmacenamientoNPC<Mob> getHostiles() {
        return hostiles;
    }
    public Carcel getCarcel() {
        return carcel;
    }

    public void setAnimales(AlmacenamientoNPC<Animal> animales) {
        this.animales = animales;
    }
    public void setAldeanos(AlmacenamientoNPC<Aldeano> aldeanos) {
        this.aldeanos = aldeanos;
    }
    public void setJugadores(AlmacenamientoNPC<Player> jugadores) {
        this.jugadores = jugadores;
    }
    public void setHostiles(AlmacenamientoNPC<Mob> hostiles) {
        this.hostiles = hostiles;
    }
    public void setCarcel(Carcel carcel) {
        this.carcel = carcel;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    /// todo.Metodos

    @Override
    public String toString() {
        return "Aldea{" +
                " Animales=" + animales +
                ", Aldeanos =" + aldeanos +
                ", Jugadores=" + jugadores +
                ", Hostiles =" + hostiles +
                ", Carcel=" + carcel +
                '}';
    }


    // public boolean agregarAldeano(){

    public boolean agregarPlayer(Player p){
        return jugadores.agregar(p);
    }


    // todo.JSON

    public void leerArchivos() {
        try {
            JSONArray Jjugadores = new JSONArray(JSONUtiles.leer2("ArchivoUsuarios"));

            for (int i=0; i<Jjugadores.length(); i++){
                JSONObject Jjugador = Jjugadores.getJSONObject(i);
                Player p = new Player();
                p.fromJSON(Jjugador);
                jugadores.agregar(p);
            }
        } catch (NoSuchFileException e){
            System.out.println("creando archivo jugadores...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JSONArray Janimales = new JSONArray(JSONUtiles.leer2("ArchivoAnimales"));

            for (int i=0; i<Janimales.length(); i++){
                JSONObject Janimal = Janimales.getJSONObject(i);
                String tipoAnimal = Janimal.getString("tipo");

                if ( tipoAnimal.equalsIgnoreCase("oveja") ){
                    Oveja o = new Oveja();
                    o.fromJSON(Janimal);
                    animales.agregar(o);

                } else if ( tipoAnimal.equalsIgnoreCase("panda") ) {
                    Panda p = new Panda();
                    p.fromJSON(Janimal);
                    animales.agregar(p);

                } else if ( tipoAnimal.equalsIgnoreCase("lobo") ) {
                    Lobo l = new Lobo();
                    l.fromJSON(Janimal);
                    animales.agregar(l);

                }
            }
        } catch (NoSuchFileException e){
            System.out.println("creando archivo animales...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JSONArray Jaldeanos = new JSONArray(JSONUtiles.leer2("ArchivoAldeanos"));

            for (int i=0; i<Jaldeanos.length(); i++){
                JSONObject Jaldeano = Jaldeanos.getJSONObject(i);
                Aldeano aldeano = new Aldeano();
                if ( aldeano.fromJSON(Jaldeano) ) aldeanos.agregar(aldeano);;
            }
        } catch (NoSuchFileException e){
            System.out.println("creando archivo aldeanos...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JSONArray Jhostiles = new JSONArray(JSONUtiles.leer2("ArchivoHostiles"));

            for (int i=0; i<Jhostiles.length(); i++){
                JSONObject Jhostil = Jhostiles.getJSONObject(i);
                String tipo = Jhostil.getString("tipo");

                if (tipo.equalsIgnoreCase("zombie")){
                    Zombie z = new Zombie();
                    z.fromJSON(Jhostil);
                    hostiles.agregar(z);

                } else if (tipo.equalsIgnoreCase("creeper")){
                    Creeper c = new Creeper();
                    c.fromJSON(Jhostil);
                    hostiles.agregar(c);

                }
            }
        } catch (NoSuchFileException e){
            System.out.println("creando archivo hostiles...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JSONObject Jcarcel = new JSONObject(JSONUtiles.leer2("ArchivoCarcel"));
            Carcel carcel = new Carcel();
            //carcel.fromJSON(Jcarcel)

        } catch (NoSuchFileException e){
            System.out.println("creando archivo carcel...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
