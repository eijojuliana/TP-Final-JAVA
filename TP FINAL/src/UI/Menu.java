package UI;

import PackageContenedores.Aldea;
import PackageContenedores.AlmacenamientoNPC;
import PackageEnum.Gen_Panda;
import PackageEnum.TipoZombie;
import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Formato_no_valido_Exception;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageJSON.JSONUtiles;
import PackageModelo.*;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public void iniciarMenu(String tipoUsuario){
        boolean bucle = true;
        Scanner s = new Scanner(System.in);

        int menu1 = 0, menu2 = 0;

        Aldea aldea = new Aldea();

        aldea.leerArchivos();


        Creeper c = new Creeper("Creeper1",10,5,false,false);
        Creeper c2 = new Creeper("Creeper2",10,5,false,false);
        Creeper c3 = new Creeper("Creeper3",10,5,false,false);




        while(bucle) {

            printMenu();
            menu1 = s.nextInt();
            System.out.println();

            if ( menu1 != 0 ){
                printMenu2(menu1);
                menu2 = s.nextInt();
                System.out.println();
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
                            try{
                                System.out.print("[1] Creeper \n[2] Zombie \nIngrese un mob: ");
                                Mob m = crearMobHostil(s.nextInt());
                                aldea.agregarHostil(m);
                                aldea.guardarCambios("ArchivoHostiles");
                            } catch (Valor_de_atributo_no_valido_Exception e){
                                System.out.println(e.getMessage());
                            }

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
                            Player p = crearPlayer();
                            if ( aldea.agregarPlayer(p) ){
                                System.out.println("Se agregó el jugador correctamente.");
                                aldea.guardarCambios("ArchivoUsuarios");
                            }
                            else System.out.println("No se pudo agregar el jugador.");
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
        System.out.print("""   
            
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
    //Aldeano
    private Aldeano crearAldeano(){
        Aldeano a = new Aldeano();
        Scanner s = new Scanner(System.in);

        String nombre, tipoAldeano;
        int opcion;

        System.out.print("Ingrese el nombre del aldeano: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        a.setNombre(nombre);

        System.out.println("El Aldeano es bebé?: \n 1-Si  \n2-No.");
        opcion=s.nextInt();
        if (opcion == 1) a.setEsBebe(true);
        else if (opcion == 0) a.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.print("""
            - DESEMPLEADO
            - VAGO,
            - ALBANIL
            - HERRERO
            - BIBLIOTECARIO
            - CARNICERO
            - CARTOGRAFO
            - CLERIGO
            - FLECHERO
            - GRANJERO
            - PASTOR
            - PELETERO
            - PESCADOR
            Ingrese la profesión:""");
        System.out.print(" ");
        tipoAldeano = s.nextLine();
        a.setProfesion(tipoAldeano);

        //Valores x defecto
        a.setVida(10);
        a.setDanio(0);
        //No tiene drops

        return a;
    }

    //Mobs
    /// @param tipo
    /// [1] Creeper
    /// [2] Zombie
    private Mob crearMobHostil(int tipo) throws Valor_de_atributo_no_valido_Exception{
        Mob m;

        switch (tipo){
            case 1:{
                m = crearCreeper();
                break;
            }
            case 2:{
                m = crearZombie();
                break;
            }
            default:{
                throw new Valor_de_atributo_no_valido_Exception("Opcion ingresada incorrecta.");
            }
        }

        return m;
    }
    private Creeper crearCreeper(){
        Creeper c = new Creeper();
        Scanner s = new Scanner(System.in);

        String nombre, opcion;

        System.out.print("Ingrese el nombre del creeper: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        c.setNombre(nombre);

        System.out.print("Está cargado? [Y - N]: ");
        opcion = s.next().toUpperCase();
        if(opcion.charAt(0) == 'Y') c.setEsElectrico(true);
        else if (opcion.charAt(0) == 'N') c.setEsElectrico(false);
        else throw new Formato_no_valido_Exception("Caracter ingresado incorrecto.");

        c.setEsBebe(false); //No pueden ser bebes.

        //Valores x defecto
        ArrayList<String> drops = new ArrayList<>();
        drops.add("Polvora");
        c.setDrops(drops);

        c.setVida(10);
        c.setDanio(21.5);

        return c;
    }
    private Zombie crearZombie(){
        Zombie z = new Zombie();
        Scanner s = new Scanner(System.in);

        String nombre, opcion, tipoZombie;

        System.out.print("Ingrese el nombre del zombie: ");
        nombre = s.nextLine();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        z.setNombre(nombre);

        System.out.print("Ingrese el tipo de zombie [Chiquito,Ahogado,Momificado,Comun,Aldeano]: ");
        tipoZombie = s.nextLine();
        z.setTipoZombie(tipoZombie);


        //Valores x defecto
        z.setEsBebe(false);
        z.setVida(10);
        z.setDanio(2.5);
        ArrayList<String> drops = new ArrayList<>();
        drops.add("Carne podrida");
        z.setDrops(drops);

        return z;
    }

    //Animales
    /// @param tipo
    /// [1] Oveja
    /// [2] Panda
    /// [3] Lobo
    private Animal crearAnimal(int tipo) throws Valor_de_atributo_no_valido_Exception {
        Animal a;

        switch (tipo){
            case 1:{
                a = crearOveja();
                break;
            }
            case 2:{
                a = crearPanda();
                break;
            }
            case 3:{
                a = crearLobo();
                break;
            }
            default:{
                throw new Valor_de_atributo_no_valido_Exception("Opcion ingresada incorrecta.");
            }
        }

        return a;
    }
    private Panda crearPanda(){
        Panda p=new Panda();
        Scanner s=new Scanner(System.in);

        String nombre, tipoAlimentacion;
        int opcion;

        System.out.print("Ingrese el nombre del panda: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        p.setNombre(nombre);

        System.out.println("El lobo es bebé?: \n 1-SIIII :3 \n2-No. Está grande ya.");
        opcion=s.nextInt();
        if (opcion == 1) p.setEsBebe(true);
        else if (opcion == 0) p.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.print("Ingrese el tipo de alimentacion que tiene su vaca: ");
        tipoAlimentacion = s.next();
        if (tipoAlimentacion.isBlank()) throw new Atributo_vacio_Exception("El tipo de alimentación está vacio");
        p.setTipoAlimentacion(tipoAlimentacion);

        System.out.print("Ingrese el tipo de alimentacion que tiene su panda: ");
        String tipoalimentacion=s.next();
        p.setTipoAlimentacion(tipoalimentacion);

        int num = (int) (Math.random() * 8);//Un numero aleatorio entre 0 y 7. Esto porq en el juego el "caracter" del panda es alatorio.
        Gen_Panda[] genPandas = Gen_Panda.values();
        p.setGen( genPandas[num] );


        //Valores x defecto
        p.setVida(20);
        if (p.getGen() == Gen_Panda.AGRESIVO) p.setDanio(3);
        else p.setDanio(0);
        //No dropean nada

        return p;
    }
    private Lobo crearLobo(){
        Lobo l= new Lobo();
        Scanner s=new Scanner(System.in);

        String nombre, tipoAlimentacion;
        int opcion;

        System.out.print("Ingrese el nombre del lobo: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        l.setNombre(nombre);

        System.out.println("El lobo es bebé?: \n 1-SIIII :3 \n2-No. Está grande ya.");
        opcion=s.nextInt();
        if (opcion == 1) l.setEsBebe(true);
        else if (opcion == 0) l.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.print("Ingrese el tipo de alimentacion que tiene su lobo: ");
        tipoAlimentacion = s.next();
        if (tipoAlimentacion.isBlank()) throw new Atributo_vacio_Exception("El tipo de alimentación está vacio");
        l.setTipoAlimentacion(tipoAlimentacion);

        System.out.println("Desea domesticar al lobo? : \n 1-SIP \n 0-NOP ");
        int decision =s.nextInt();

        if (decision ==1 ){
            System.out.println("Ingrese su id :");
            int id=s.nextInt();
            String mensaje =l.domesticarLobo(id);
            System.out.println(mensaje);
        } else {
            l.setDomesticado(false);
            l.setIDduenio(0);
        }

        //Valor por defecto
        if (l.isDomesticado()) l.setVida(10);
        else l.setVida(4);
        l.setDanio(4);
        //No tiene drops

        return l;
    }
    private Oveja crearOveja() throws Atributo_vacio_Exception, Valor_de_atributo_no_valido_Exception{
        Oveja o= new Oveja();
        Scanner s=new Scanner(System.in);

        String mensaje, nombre, color, tipoAlimentacion;
        int opcion;

        System.out.print("Ingrese el nombre de la oveja: ");
        nombre = s.next();
        if (s.next().isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        o.setNombre(nombre);

        System.out.print("Ingrese el tipo de alimentacion que tiene su oveja: ");
        tipoAlimentacion = s.next();
        if (tipoAlimentacion.isBlank()) throw new Atributo_vacio_Exception("El tipo de alimentación está vacio");
        o.setTipoAlimentacion(tipoAlimentacion);

        System.out.print("Ingrese el color: ");
        color = s.next();
        if (color.isBlank()) throw new Atributo_vacio_Exception("El tipo de alimentación está vacio");
        o.setColor(color);

        System.out.println("La oveja es bebé?: \n 1-SIIII :3 \n2-No. Está grande ya");
        opcion=s.nextInt();
        if (opcion == 1) o.setEsBebe(true);
        else if (opcion == 0) o.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.println("Desea esquilar a la ovejita?: \n 1-SIP \n 0-NOP");
        opcion=s.nextInt();

        if (opcion==1){
            mensaje=o.esquilar();
            System.out.println(mensaje);
        }
        else if (opcion == 0){
            mensaje=o.crecerLana();
            System.out.println(mensaje);
        } else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        //Valores por defecto
        o.setVida(4);
        o.setDanio(0);
        ArrayList<String> drops = new ArrayList<>();
        drops.add("Lana");
        o.setDrops(drops);

        return o;
    }

    //Jugador
    private Player crearPlayer(){
        Player p = new Player();
        Scanner s = new Scanner(System.in);

        String nombre, contrasenia;
        char eleccion; // Y o N

        System.out.print("Ingrese el nombre del usuario: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        p.setNombre(nombre);

        System.out.print("Ingrese la contrasenia: ");
        contrasenia = s.next();
        if (contrasenia.isBlank()) throw new Atributo_vacio_Exception("La contrasenia esta vacia");
        p.setContrasenia(contrasenia);

        System.out.print("Es premium? [Y-N]: ");
        eleccion = s.next().charAt(0);
        if(eleccion == 'Y' || eleccion == 'y') p.setEsPremium(true);
        else if (eleccion == 'N' || eleccion == 'n') p.setEsPremium(true);
        else throw new Formato_no_valido_Exception("Carácter ingresado no válido.");

        System.out.print("Ingrese el gamemode [Survival-Creativo-Espectador-OP]: ");
        if ( !p.setTipoPlayer(s.next()) ) throw new Valor_de_atributo_no_valido_Exception("Tipo de player no valido.");

        //Valores por defecto del juego
        p.setVida(10);
        p.setDanio(2);

        return p;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    public static String ZombiesToTable(ArrayList<Zombie> zombies) throws Atributo_vacio_Exception {
        // Verificar si el ArrayList está vacío
        if (zombies == null || zombies.isEmpty()) throw new Atributo_vacio_Exception("La lista de zombies está vacía.");

        // Crear los datos para la tabla
        String[][] data = new String[zombies.size() + 1][6];
        data[0] = new String[]{"ID","Nombre", "Vida", "Daño", "¿Es bebé?", "Tipo Zombie"};

        for (int i = 0; i < zombies.size(); i++) {
            Zombie z = zombies.get(i);
            data[i + 1] = z.aFila();
        }
        return AsciiTable.getTable(data);
    }



}
