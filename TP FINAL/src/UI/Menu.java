package UI;

import PackageContenedores.AlmacenamientoNPC;
import PackageEnum.Gen_Panda;
import PackageEnum.TipoZombie;
import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Formato_no_valido_Exception;
import PackageModelo.*;
import com.github.freva.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public void iniciarMenu(String tipoUsuario){
        boolean bucle = true;
        Scanner s = new Scanner(System.in);

        int menu1 = 0, menu2 = 0;

        Creeper c = new Creeper("Creeper1",10,5,false,false);
        Creeper c2 = new Creeper("Creeper2",10,5,false,false);
        Creeper c3 = new Creeper("Creeper3",10,5,false,false);
        Zombie z = new Zombie ("Zombie1", 10.6, 5.0, false, TipoZombie.ALDEANO);
        Zombie z2 = new Zombie ("Zombieasda2", 10.6, 5.0, false, TipoZombie.AHOGADO);
        Zombie z3 = new Zombie ("Zombie3aaaaaaaaa", 10.6, 5.0, false, TipoZombie.CHIQUITO);

        AlmacenamientoNPC<Entidad> carcel = new AlmacenamientoNPC<>();
        //Probandop
        ArrayList<Zombie> zombies = new ArrayList<>();
        zombies.add(z);
        zombies.add(z2);
        zombies.add(z3);

        /**/
        System.out.println(z.aTabla());
        System.out.printf("\n\n");/*
        System.out.println(ZombiesToTable(zombies));
        */
        /*
        carcel.agregar(c);
        carcel.agregar(c2);
        carcel.agregar(c3);

        JSONUtiles.grabarUnJson(carcel.toJSON(),"carcel");

        try {
            JSONArray jsonArray = new JSONArray(JSONUtiles.leer("carcel"));
            for ( int i=0; i< jsonArray.length() ; i++ ){
                JSONObject jObject = jsonArray.getJSONObject(i);

                Entidad e;

                if (jObject.getString("tipo").equals("Creeper")){
                    e = new Creeper();
                    e.fromJSON(jObject);
                } else {
                    e = null;
                }
                carcel.agregar( e );
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);

        } finally {
            System.out.println("Cárcel:\n"+carcel);
        }
         */


        while(bucle) {

            printMenu();
            menu1 = s.nextInt();
            System.out.println();

            if ( menu1 != 0 ){
                printMenu2(menu1);
                menu2 = s.nextInt();
            }

            switch (menu1) {
                //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                case 1:{
                    switch (menu2){
                        case 1:{
                            //cargarAldeano();
                            break;
                        }
                        case 2:{
                            //verAldeanos();
                            break;
                        }
                        case 3:{
                            //modificarAldeano();
                            break;
                        }
                        case 4:{
                            //eliminarAldeano
                            break;
                        }
                        case 5:{
                            //Buscar por
                            break;
                        }
                    }
                    break;} //ALDEANOS
                //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                case 2:{
                    switch (menu2){
                        case 1:{
                            //cargarAnimal();
                            break;
                        }
                        case 2:{
                            //verAnimales();
                            break;
                        }
                        case 3:{
                            //modificarAnimal();
                            break;
                        }
                        case 4:{
                            //eliminarAnimal()
                            break;
                        }
                        case 5:{
                            //Buscar por
                            break;
                        }
                    }
                    break;} //ANIMALES
                //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                case 3:{
                    switch (menu2){
                        case 1:{
                            //cargarMob();
                            break;
                        }
                        case 2:{
                            //verCarcel();
                            break;
                        }
                        case 3:{
                            //modificarMob();
                            break;
                        }
                        case 4:{
                            //eliminarMob
                            break;
                        }
                        case 5:{
                            //Buscar por
                            break;
                        }
                    }
                    break;} //CARCEL
                //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                case 4:{
                    switch (menu2){
                        case 1:{
                            //agregarJugador();
                            break;
                        }
                        case 2:{
                            //verJugadores();
                            break;
                        }
                        case 3:{
                            //modificarJugador();
                            break;
                        }
                        case 4:{
                            //banearJugador
                            break;
                        }
                        case 5:{
                            //Buscar por
                            break;
                        }
                    }
                    break;} //JUGADORES
                //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                case 5:{
                    //mostrarAldeanos();
                    //------
                    //mostrarAnimales();
                    //------
                    //mostrarCarcel();
                    break;} //Mostrar todos los mobs
                //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                case 6:{
                    //buscar por() (general);
                    break;} //Buscar por
                //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════






                case 0: {
                    System.out.println("Saliendo...");
                    bucle = false;
                    break;
                }
                default: {
                    System.out.println("Error al cargar una opción intente nuevamente.");
                    s.nextLine();
                    break;
                }
            }
            if ( menu1 != 0 ){ //Si no eligió salir...
                System.out.println();
                pausa();
                System.out.println("\n");
            }
        }

    }
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo MENU
    private void printMenu(){
        System.out.print("""
                GESTIÓN MINECRAFT:
                
                --  ALDEA  --
                [1] Aldeanos
                [2] Animales
                [3] Cárcel
        
                -- USUARIOS --
                [4] Jugadores
                
                --  TODOS  --
                [5] Mostrar todos los mobs.
                [6] Buscar mob por id/nombre.
                
                -- SISTEMA --
                [0] Salir
                
                ¿Qué registro desea modificar? Ingrese una opción:""");
        System.out.print(" ");
    }
    private void printMenu2(int menu1){

        System.out.print(" GESTIÓN ");
        switch (menu1){
            case 1: System.out.println("ALDEANOS:"); break;
            case 2: System.out.println("ANIMALES:"); break;
            case 3: System.out.println("CÁRCEL:"); break;
            case 4: System.out.println("JUGADORES:"); break;
        }
        System.out.println("""   
            
                [1] Cargar elemento
                [2] Ver todos
                [3] Modificar un elemento
                [4] Eliminar una entidad
                [5] Buscar por id/nombre
                
                [0] Salir
                
                Ingrese una opción:""");
        System.out.print(" ");
    }
    private void pausa(){
        Scanner s = new Scanner(System.in);
        System.out.print("Ingrese un caracter para continuar... ");
        s.nextLine();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo CARGAR

    private Entidad crearEntidad() {
        Entidad e = new Entidad();
        Scanner s = new Scanner(System.in);


        System.out.print("Ingrese un nombre: ");
        String nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("No se puede cargar un atributo vacio");
        else e.setNombre(nombre);

        e.setVida(10);
        e.setDanio(0);

        return e;
    }
    //Mobs
    private Creeper crearCreeper(){
        Creeper c;
        Scanner s = new Scanner(System.in);

        c = (Creeper) crearEntidad();

        c.setEsBebe(false);
        ArrayList<String> drops = new ArrayList<>();
        drops.add("Polvora");
        c.setDrops(drops);
        c.setVida(10);
        c.setDanio(21.5);

        System.out.print("Está cargado? [Y - N]: ");
        if(s.next().charAt(0) == 'Y') c.setEsElectrico(true);
        else if (s.next().charAt(0) == 'N') c.setEsElectrico(false);
        else throw new Formato_no_valido_Exception("Caracter ingresado incorrecto.");

        return c;
    }

    //Animales
    private Animal crearAnimal(){
        Animal a = null;
        //Un switch de cada crear animal
        //case 1: a = crearOveja()
        return a;
    }
    private Panda crearPanda(){
      Panda p=new Panda();
      Scanner s=new Scanner(System.in);

         crearEntidad();

        System.out.print("Ingrese el tipo de alimentacion que tiene su panda: ");
        String tipoalimentacion=s.next();
        p.setTipoAlimentacion(tipoalimentacion);

        p.setVida(20);

        if (p.getGen() == Gen_Panda.AGRESIVO){
            p.setDanio(6);
        }
        else {
            p.setDanio(0);
        }

          return p;
    }
    private Lobo crearLobo(){
    Lobo l= new Lobo();
    Scanner s=new Scanner(System.in);

    crearEntidad();
    System.out.println("Desea domesticar al lobo? : \n 1-SIP \n 0-NOP ");
    int decision =s.nextInt();

    if (decision ==1 ){
        System.out.println("Ingrese su id :");
        int id=s.nextInt();
        String mensaje =l.domesticarLobo(id);
        System.out.println(mensaje);
    }
      return l;
    }
    private Oveja crearOveja(){
     Oveja o= new Oveja();
     Scanner s=new Scanner(System.in);
     String mensaje;

     crearEntidad();
        System.out.println("Desea esquilar a la ovejita?: \n 1-SIP \n 0-NOP");
        int esquilar=s.nextInt();

        if (esquilar==1){
            mensaje=o.esquilar();
            System.out.println(mensaje);
        }
        else{
            mensaje=o.crecerLana();
            System.out.println(mensaje);
        }
        return o;
    }


    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    public static String ZombiesToTable(ArrayList<Zombie> zombies) throws Atributo_vacio_Exception {
        // Verificar si el ArrayList está vacío
        if (zombies == null || zombies.isEmpty()) throw new Atributo_vacio_Exception("La lista de zombies está vacía.");

        // Crear los datos para la tabla
        String[][] data = new String[zombies.size() + 1][6];
        data[0] = new String[]{"Nombre", "Vida", "Daño", "¿Es bebé?", "Tipo Zombie"};

        for (int i = 0; i < zombies.size(); i++) {
            Zombie z = zombies.get(i);
            data[i + 1] = new String[]{
                    z.getNombre(),
                    String.format("%.2f", z.getVida()),
                    String.format("%.2f", z.getDanio()),
                    z.isEsBebe()? "Sí" : "No",
                    z.getTipoZombie().name()
            };
        }
        return AsciiTable.getTable(data);
    }




}
