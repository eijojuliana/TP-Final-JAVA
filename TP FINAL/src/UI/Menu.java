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

        while(bucle) {

            printMenu();
            menu1 = s.nextInt();
            System.out.println();

            if ( menu1 != 0 ){
                printMenu2(menu1, tipoUsuario);
                menu2 = s.nextInt();
                System.out.println();
            }

            switch (menu1) {
                //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                case 1:{
                    switch (menu2){
                        case 1:{
                            /// CARGAR ALDEANO
                            Aldeano a = crearAldeano();
                            if ( aldea.agregarAldeano(a) ){
                                System.out.println("Se agregó al aldeanito correctamente.");
                                aldea.guardarCambios("ArchivoAldeanos");
                            }
                            else System.out.println("No se pudo agregar al aldeano.");
                            break;
                        }
                        case 2:{
                            //verAldeanos();
                            System.out.println(aldea.AldeanosToTable());
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
                            /// CARGAR ANIMAL
                            try{
                                System.out.print("[1] Oveja \n[2] Panda \n[2] Lobo\nIngrese un mob: ");
                                Animal a = crearAnimal(s.nextInt());
                                aldea.agregarAnimal(a);
                                aldea.guardarCambios("ArchivoAnimales");
                            } catch (Valor_de_atributo_no_valido_Exception e){
                                System.out.println(e.getMessage());
                            }

                            break;
                        }
                        case 2:{
                            //verAnimales();
                            System.out.println(aldea.AnimalesToTable());
                            break;
                        }
                        case 3:{
                            //eliminarAnimal()
                            break;
                        }
                        case 4:{
                            //Buscar por
                            break;
                        }
                        case 5:{
                            //buscar por
                            //if intance of lobo
                            //Domesticar lobo
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
                            System.out.println(aldea.HostilesToTable());
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
                            System.out.println(aldea.JugadoresToTable());
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

            if ( menu1 != 0 && menu2 != 0){ //Si no eligió salir...
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
                [3] Hostiles
                [4] Carcel
                
                -- USUARIOS --
                [5] Jugadores
                
                --  TODOS  --
                [7] Mostrar todos los mobs.
                [8] Buscar mob por id/nombre.
                
                -- SISTEMA --
                [0] Salir
                
                Ingrese una opción:""");
        System.out.print(" ");
    }
    private void printMenu2(int menu1, String tipoUsuario){

        System.out.print(" GESTIÓN ");
        switch (menu1){
            case 1: {
                System.out.println("ALDEANOS:");
                if (tipoUsuario.equalsIgnoreCase("Espectador")){
                    System.out.print("""   
            
                        [̶̶̶1̶̶̶]̶̶̶ ̶̶̶C̶̶̶a̶̶̶r̶̶̶g̶̶̶a̶̶̶r̶̶̶ ̶̶̶u̶n̶ ̶a̶l̶d̶e̶a̶n̶o̶
                        [2] Ver todos los aldeanos
                        [̶3̶]̶ ̶E̶l̶i̶m̶i̶n̶a̶r̶ ̶u̶n̶ ̶a̶l̶d̶e̶a̶n̶o̶ ̶p̶o̶r̶ ̶i̶d̶
                        [4] Buscar por id
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("Survival")) {
                    System.out.print("""   
                            
                            [̶̶̶1̶̶̶]̶̶̶ ̶̶̶C̶̶̶a̶̶̶r̶̶̶g̶̶̶a̶̶̶r̶̶̶ ̶̶̶u̶n̶ ̶a̶l̶d̶e̶a̶n̶o̶
                            [2] Ver todos los aldeanos
                            [3] Eliminar un aldeano por id
                            [4] Buscar por id
                            
                            """);
                } else if ( tipoUsuario.equalsIgnoreCase("Creativo") ||
                            tipoUsuario.equalsIgnoreCase("OP") ) {
                    System.out.print("""   
                            
                            [1] Cargar un aldeano
                            [2] Ver todos los aldeanos
                            [3] Eliminar un aldeano por id
                            [4] Buscar por id
                            
                            """);
                }
                break;
            }
            case 2: {
                System.out.println("ANIMALES:");
                if (tipoUsuario.equalsIgnoreCase("Espectador")){
                    System.out.print("""   
            
                        [̶̶̶1̶̶̶]̶ ̶̶̶C̶̶̶a̶̶̶r̶̶̶g̶̶̶a̶̶̶r̶̶̶ ̶̶̶u̶n̶ ̶a̶n̶i̶m̶a̶l̶
                        [2] Ver todos los animales
                        [̶3̶]̶ ̶E̶l̶i̶m̶i̶n̶a̶r̶ ̶u̶n̶ ̶a̶n̶i̶m̶a̶l̶ ̶p̶o̶r̶ ̶i̶d̶
                        [4] Buscar por id
                        [̶5̶]̶ ̶D̶o̶m̶e̶s̶t̶i̶c̶a̶r̶ ̶l̶o̶b̶o̶
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("Survival")){
                    System.out.print("""   
            
                        [̶̶̶1̶̶̶]̶ ̶̶̶C̶̶̶a̶̶̶r̶̶̶g̶̶̶a̶̶̶r̶̶̶ ̶̶̶u̶n̶ ̶a̶n̶i̶m̶a̶l̶
                        [2] Ver todos los animales
                        [3] Eliminar un animal por id
                        [4] Buscar por id
                        [5] Domesticar lobo
                        
                        """);
                } else if ( tipoUsuario.equalsIgnoreCase("Creativo") ||
                            tipoUsuario.equalsIgnoreCase("OP") ) {
                    System.out.print("""   
                            
                            [1] Cargar un animal
                            [2] Ver todos los animales
                            [3] Eliminar un animal por id
                            [4] Buscar por id
                            [5] Domesticar lobo
                            
                            """);
                }
                break;
            }
            case 3: {
                System.out.println("HOSTILES:");
                if (tipoUsuario.equalsIgnoreCase("Espectador")){
                    System.out.print("""   
            
                        [̶̶̶1̶̶̶]̶̶̶ ̶̶̶C̶a̶r̶g̶a̶r̶ ̶u̶n̶ ̶m̶o̶b̶ ̶h̶o̶s̶t̶i̶l̶
                        [2] Ver todos los mobs hostiles
                        [̶3̶]̶ ̶E̶l̶i̶m̶i̶n̶a̶r̶ ̶u̶n̶ ̶m̶o̶b̶ ̶p̶o̶r̶ ̶i̶d̶
                        [4] Buscar por id
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("Survival")){
                    System.out.print("""   
            
                        [̶̶̶1̶̶̶]̶̶̶ ̶̶̶C̶a̶r̶g̶a̶r̶ ̶u̶n̶ ̶m̶o̶b̶ ̶h̶o̶s̶t̶i̶l̶
                        [2] Ver todos los mob hostiles
                        [3] Eliminar un mob por id
                        [4] Buscar por id
                        
                        """);
                } else if ( tipoUsuario.equalsIgnoreCase("Creativo") ||
                            tipoUsuario.equalsIgnoreCase("OP") ) {
                    System.out.print("""   
                            
                            [1] Cargar un mob hostil
                            [2] Ver todos los mob hostiles
                            [3] Eliminar un mob por id
                            [4] Buscar por id
                            
                            """);
                }
                break;
            }
            case 4: {
                System.out.println("CARCEL:");
                if (tipoUsuario.equalsIgnoreCase("Espectador")){
                    System.out.print("""   
            
                        [̶1̶]̶ ̶A̶g̶r̶e̶g̶a̶r̶ ̶m̶o̶b̶ ̶a̶ ̶l̶a̶ ̶c̶a̶r̶c̶e̶l̶
                        [2] Ver todas las celdas
                        [3] Ver mobs encarcelados
                        [4] Ver celdas libres
                        [̶5̶]̶̶ ̶L̶i̶b̶e̶r̶a̶r̶ ̶m̶o̶b̶
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("Survival")){
                    System.out.print("""   
                
                            [̶1̶]̶ ̶A̶g̶r̶e̶g̶a̶r̶ ̶m̶o̶b̶ ̶a̶ ̶l̶a̶ ̶c̶a̶r̶c̶e̶l̶
                            [2] Ver todas las celdas
                            [3] Ver mobs encarcelados
                            [4] Ver celdas libres
                            [̶5̶]̶̶ ̶L̶i̶b̶e̶r̶a̶r̶ ̶m̶o̶b̶
                            
                            """);
                } else if ( tipoUsuario.equalsIgnoreCase("Creativo") ||
                        tipoUsuario.equalsIgnoreCase("OP") ) {
                    System.out.print("""   
                            
                            [1] Agregar un mob a la carcel
                            [2] Ver todas las celdas
                            [3] Ver mobs encarcelados
                            [4] Ver celdas libres
                            [5] Liberar mob
                            
                            """);
                }
                break;
            }
            case 5:{
                System.out.println("JUGADORES:");
                if (tipoUsuario.equalsIgnoreCase("Espectador") ||
                    tipoUsuario.equalsIgnoreCase("Survival") ||
                    tipoUsuario.equalsIgnoreCase("Creativo") ){
                    System.out.print("""   
            
                        [̶1̶]̶ ̶A̶g̶r̶e̶g̶a̶r̶ ̶j̶u̶g̶a̶d̶o̶r̶ ̶a̶ ̶l̶a̶ ̶w̶h̶i̶t̶e̶l̶i̶s̶t̶
                        [2] Ver todos los jugadores.
                        [̶3̶]̶ ̶E̶l̶i̶m̶i̶n̶a̶r̶ ̶j̶u̶g̶a̶d̶o̶r̶ ̶d̶e̶ ̶l̶a̶ ̶w̶h̶i̶t̶e̶l̶i̶s̶t̶
                        [̶̶̶4̶]̶̶ ̶̶̶B̶u̶s̶c̶a̶r̶ ̶p̶o̶r̶ ̶i̶d̶
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("OP")){
                    System.out.print("""   
            
                        [1] Agregar jugador a la whitelist
                        [2] Ver todos los jugadores.
                        [3] Eliminar jugador de la whitelist
                        [4] Buscar por id
                        
                        """);
                }
                break;
            }
        }
        System.out.print("[0] Volver \n \n" + "Ingrese una opción: ");
    }

    private void pausa(){
        Scanner s = new Scanner(System.in);
        System.out.print("Presione enter para continuar... ");
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
