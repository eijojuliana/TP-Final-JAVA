package PackageContenedores;
import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Entidad_inexistente_Exception;
import PackageExceptions.Entidad_repetida_Exception;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.ITabla;
import PackageJSON.JSONUtiles;
import PackageModelo.*;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

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

    //set
    public void setCarcel(Carcel carcel) {
        this.carcel = carcel;
    } //Para el from json


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

    protected static String tabla_modificada(String asciiTable) {
        return asciiTable.replace("+", "╬")
                .replace("-", "═")
                .replace("|", "║")
                .replace("┐", "╖")
                .replace("└", "╙")
                .replace("┘", "╜")
                .replace("┌", "╓")
                .replace("┼", "╪")
                .replace("╥", "╬")
                .replace("╨", "╡");
    }

    public boolean agregarAnimal (Animal a) throws Atributo_vacio_Exception, Entidad_inexistente_Exception {
        return animales.agregar(a);
    }
    public Animal buscarAnimal (int id) throws Entidad_inexistente_Exception, Valor_de_atributo_no_valido_Exception {
        return animales.buscarXid(id);
    }

    public boolean domesticarLobito(int idLobo, int idDuenio) throws Entidad_inexistente_Exception, Valor_de_atributo_no_valido_Exception, Entidad_repetida_Exception {
        boolean domesticado=false;

        Lobo lobo = (Lobo) animales.buscarXid(idLobo);
        domesticado=lobo.domesticarLobo(idDuenio);

        return domesticado;
    }


    public boolean agregarAldeano(Aldeano a) throws Atributo_vacio_Exception, Entidad_repetida_Exception {
        return aldeanos.agregar(a);
    }
    public Aldeano buscarAldeano(int id) throws Entidad_inexistente_Exception, Valor_de_atributo_no_valido_Exception {
        return aldeanos.buscarXid(id);
    }

    public boolean agregarPlayer(Player p) throws Atributo_vacio_Exception, Entidad_repetida_Exception {
        return jugadores.agregar(p);
    }
    public Player buscarPlayer(int id) throws Entidad_inexistente_Exception, Valor_de_atributo_no_valido_Exception {
        return jugadores.buscarXid(id);
    }
    public static boolean existePlayerEnArchivo(int id) throws Entidad_inexistente_Exception {
        try {
            JSONArray jugadores = new JSONArray(JSONUtiles.leer2("ArchivoUsuarios"));

            for (int i=0; i<jugadores.length() ; i++) {
                if ( jugadores.getJSONObject(i).getInt("id") == id) return true;
            }
            throw new Entidad_inexistente_Exception("No se encontró el jugador.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean agregarHostil(Mob m) throws Atributo_vacio_Exception, Entidad_repetida_Exception {
        return hostiles.agregar(m);
    }
    public Mob buscarMobHostil(int id) throws Entidad_repetida_Exception, Valor_de_atributo_no_valido_Exception {
        return hostiles.buscarXid(id);
    }

    public Entidad buscarEntidad(int id) throws Valor_de_atributo_no_valido_Exception, Entidad_inexistente_Exception {

        if (id<0) throw new Valor_de_atributo_no_valido_Exception("ID negativo.");

        ArrayList<Entidad> todos = new ArrayList<>();
        for (int i = 0; i<aldeanos.size() ; i++) todos.add( aldeanos.get(i));
        for (int i = 0; i<animales.size() ; i++) todos.add(animales.get(i));
        for (int i = 0; i<jugadores.size() ; i++) todos.add(jugadores.get(i));
        for (int i = 0; i<hostiles.size() ; i++) todos.add(hostiles.get(i));

        for (int i=0; i<todos.size() ; i++) {
            Entidad e = todos.get(i);
            if (e.getId() == id) return e;
        }
        throw new Entidad_inexistente_Exception("La entidad no fue encontrada.");
    }


    /// ToTable ARRAYS
    public String AnimalesToTable() throws Atributo_vacio_Exception {
        if (animales == null || animales.isEmpty()) {
            throw new Atributo_vacio_Exception("La lista de animales está vacía.");
        }

        String[][] data = new String[animales.size() + 1][7];
        data[0] = new String[]{"Mob", "ID","Nombre", "Vida", "Daño", "¿Es bebé?", "Tipo Alimentacion"};

        for (int i = 0; i < animales.size(); i++) {
            Animal animal = animales.get(i);
            data[i + 1] = animal.aFila();
        }

        return tabla_modificada(AsciiTable.getTable(data));
    }

    public String AldeanosToTable() throws Atributo_vacio_Exception {
        if (aldeanos == null || aldeanos.isEmpty()) {
            throw new Atributo_vacio_Exception("La lista de aldeanos está vacía.");
        }

        String[][] data = new String[aldeanos.size() + 1][6];
        data[0] = new String[]{"ID", "Nombre", "Vida", "Daño", "¿Es bebé?", "Profesion"};

        for (int i = 0; i < aldeanos.size(); i++) {
            Aldeano a = aldeanos.get(i);
            data[i + 1] = a.aFila();
        }

        return tabla_modificada(AsciiTable.getTable(data));
    }

    public String JugadoresToTable() throws Atributo_vacio_Exception {
        if (jugadores == null || jugadores.isEmpty()) {
            throw new Atributo_vacio_Exception("La lista de jugadores está vacía.");
        }

        String[][] data = new String[jugadores.size() + 1][5];
        data[0] = new String[]{"ID", "Nombre", "Premium", "Vida", "Daño"};

        for (int i = 0; i < jugadores.size(); i++) {
            Player p = jugadores.get(i);
            data[i + 1] = p.aFila();
        }

        return tabla_modificada(AsciiTable.getTable(data));
    }

    public String HostilesToTable() throws Atributo_vacio_Exception {
        if (hostiles == null || hostiles.isEmpty()) {
            throw new Atributo_vacio_Exception("La lista de hostiles está vacía.");
        }

        String[][] data = new String[hostiles.size() + 1][6];
        data[0] = new String[]{"Mob", "ID", "Nombre", "Vida", "Daño", "¿Es bebé?"}; //Pero no pueden ser bebe ninguno de los dos

        for (int i = 0; i < hostiles.size(); i++) {
            if(hostiles.get(i) instanceof Zombie) {
                Zombie z = (Zombie) hostiles.get(i);
                data[i + 1] = z.aFila();
            } else {
                Creeper c = (Creeper) hostiles.get(i);
                data[i + 1] = c.aFila();
            }
        }

        return tabla_modificada(AsciiTable.getTable(data));
    }

    public String todoToTable() throws Atributo_vacio_Exception {
        if (hostiles.isEmpty() && jugadores.isEmpty() && aldeanos.isEmpty() && animales.isEmpty())
            throw new Atributo_vacio_Exception("Listas sin valores.");

        ArrayList<Entidad> todos = new ArrayList<>();

        //Agregamos los elementos a un unico array para desp ordenarlos en el array final
        for (int i = 0; i<aldeanos.size() ; i++) todos.add( aldeanos.get(i));
        for (int i = 0; i<animales.size() ; i++) todos.add(animales.get(i));
        for (int i = 0; i<jugadores.size() ; i++) todos.add(jugadores.get(i));
        for (int i = 0; i<hostiles.size() ; i++) todos.add(hostiles.get(i));

        Comparator<Entidad> comparator = new Comparator<Entidad>() {
            @Override
            public int compare(Entidad o1, Entidad o2) {
                return Integer.compare(o1.getId(),o2.getId());
            }
        };
        Collections.sort(todos,comparator);

        String[][] data = new String[ todos.size() + 1][5];
        data[0] = new String[]{"ID", "NOMBRE", "VIDA", "DAÑO","TIPO"};

        for (int i=0; i<todos.size(); i++){
            Entidad e = todos.get(i);
            data[i + 1] = Entidad.aFila(e);
        }

        return tabla_modificada(AsciiTable.getTable(data));
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
            JSONUtiles.grabarUnJson(new JSONArray(),"ArchivoUsuarios");
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
            JSONUtiles.grabarUnJson(new JSONArray(),"ArchivoAnimales");
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
            JSONUtiles.grabarUnJson(new JSONArray(),"ArchivoAldeanos");
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
            JSONUtiles.grabarUnJson(new JSONArray(),"ArchivoHostiles");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JSONObject Jcarcel = new JSONObject(JSONUtiles.leer2("ArchivoCarcel"));
            Carcel carcel = new Carcel();
            carcel.fromJSON(Jcarcel);
            setCarcel(carcel);

        } catch (NoSuchFileException e){
            System.out.println("creando archivo carcel...");
            JSONUtiles.grabarUnJson(new Carcel().toJSON(),"ArchivoCarcel");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        actualizarIDIncremental();
    }
    public void guardarCambios(String archivo) throws Valor_de_atributo_no_valido_Exception {
        switch (archivo) {
            case "ArchivoUsuarios" -> JSONUtiles.grabarUnJson(jugadores.toJSON(), archivo);
            case "ArchivoAnimales" -> JSONUtiles.grabarUnJson(animales.toJSON(), archivo);
            case "ArchivoAldeanos" -> JSONUtiles.grabarUnJson(aldeanos.toJSON(), archivo);
            case "ArchivoHostiles" -> JSONUtiles.grabarUnJson(hostiles.toJSON(), archivo);
            case "ArchivoCarcel" -> JSONUtiles.grabarUnJson(carcel.toJSON(), archivo);
            default -> throw new Valor_de_atributo_no_valido_Exception("Archivo no existente.");
        }
    }

    private void actualizarIDIncremental(){
        ArrayList<Entidad> todos = new ArrayList<>();

        for (int i = 0; i<aldeanos.size() ; i++) todos.add( aldeanos.get(i));
        for (int i = 0; i<animales.size() ; i++) todos.add(animales.get(i));
        for (int i = 0; i<jugadores.size() ; i++) todos.add(jugadores.get(i));
        for (int i = 0; i<hostiles.size() ; i++) todos.add(hostiles.get(i));

        Comparator<Entidad> comparator = new Comparator<Entidad>() {
            @Override
            public int compare(Entidad o1, Entidad o2) {
                return Integer.compare(o1.getId(),o2.getId());
            }
        };
        Collections.sort(todos,comparator);

        try {
            Entidad.setAutoincremental(todos.getLast().getId());
        } catch (Exception e){
            Entidad.setAutoincremental(0);
        }

        /* Esté metodo lo creamos porque al eliminar un mob del JSON genera que al reiniciar el programa,
        el idIncremental esté desincronizado al ultimo id. Entonces genera que al intentar crear otro mob se rompa el programa. */
    }

    /// todo.METODOS CARCEL
    public boolean encarcelar(int id, LocalDate fechaEntrada, LocalDate fechaSalida) throws Entidad_inexistente_Exception, Valor_de_atributo_no_valido_Exception, Atributo_vacio_Exception, Entidad_repetida_Exception{
        boolean encarcelado;
        Entidad entidad = buscarEntidad(id);

        encarcelado=carcel.encarcelar(entidad,fechaEntrada,fechaSalida,id);
        return encarcelado;
    }

    public boolean liberaMob (int numeroCelda) throws Valor_de_atributo_no_valido_Exception {
        boolean liberado;
        liberado=carcel.liberarMob(numeroCelda);

        return liberado;
    }
    public boolean liberacionAnticipada(int decision,int numeroCelda){
        boolean liberado;
        liberado=carcel.liberacionAnticipada(decision,numeroCelda);

        return liberado;
    }

    public String carcelToTable() throws Atributo_vacio_Exception {
        return carcel.carcelToTable();
    }


    public String infoCelda(int numeroCelda){
        return carcel.obtenerInfoCelda(numeroCelda).aTabla();
    }

    public int cantCeldasDesocupadas(){
        int cantCeldasDesocupadas;

        cantCeldasDesocupadas=carcel.contarCeldasDesocupadas();

        return cantCeldasDesocupadas;
    }

    public ArrayList<Integer> celdasDesocupadas(){
        return carcel.obtenerCeldasDesocupadas();
    }

    /// todo.METODOS DE ELIMINACION//
   public boolean eliminarAldeano(int id){
       boolean eliminado=false;
       eliminado=aldeanos.eliminar(id);

       return eliminado;
   }

    public  boolean eliminarHostiles( int id) {
      boolean eliminado=false;

      eliminado=hostiles.eliminar(id);

      return eliminado;
    }

    public boolean eliminarPlayer(int id){
       boolean eliminado=false;

       eliminado= jugadores.eliminar(id);

       return eliminado;
    }

    public boolean eliminarAnimal(int id ){
       boolean eliminado=false;

       eliminado=animales.eliminar(id);

       return eliminado;
    }

}
