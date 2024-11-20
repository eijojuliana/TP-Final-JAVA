package UI;

import PackageContenedores.Aldea;
import PackageEnum.Gen_Panda;
import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Formato_no_valido_Exception;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;

import PackageModelo.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void iniciarMenu(String tipoUsuario){
        boolean bucle = true;
        Scanner s = new Scanner(System.in);

        int menu1 = 0, menu2 = 0;

        Aldea aldea = new Aldea();

        aldea.leerArchivos();

        MINECRAFT();
        while(bucle) {
            printMenu();
            while (true){
                try{
                    menu1 = s.nextInt();
                    break;

                } catch (InputMismatchException e){
                    System.out.println("Solo numeros.");
                    s.nextLine();
                }
            }

            System.out.println();

            if ( menu1 > 0 && menu1 < 6 ){
                printMenu2(menu1, tipoUsuario);
                while (true){
                    try {
                        menu2 = s.nextInt();
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println("Solo numeros.");
                        s.nextLine();
                    }
                }
                System.out.println();
            }
            try {
                switch (menu1) {
                    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                    case 1: {
                        switch (menu2) {
                            case 1: {
                                if (tipoUsuario.equalsIgnoreCase("Creativo") ||
                                    tipoUsuario.equalsIgnoreCase("OP")) {
                                        // CARGAR ALDEANO
                                        Aldeano a = crearAldeano();
                                        if (aldea.agregarAldeano(a)) {
                                            System.out.println("Se agregó al aldeanito correctamente.");
                                            aldea.guardarCambios("ArchivoAldeanos");
                                        } else System.out.println("No se pudo agregar al aldeano.");

                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 2: {
                                System.out.println(aldea.AldeanosToTable());
                                break;
                            }
                            case 3: {
                                if (!tipoUsuario.equalsIgnoreCase("Espectador")) {
                                    int id;
                                    System.out.print("Ingrese el id del aldeano a eliminar: ");
                                    id = s.nextInt();
                                    if (aldea.eliminarAldeano(id)){
                                        System.out.println("Se eliminó el aldeanito :c .");
                                        aldea.guardarCambios("ArchivoAldeanos");
                                    }
                                    else System.out.println("No se pudo eliminar el aldeano.");

                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 4: {
                                int id;
                                System.out.print("Ingrese el id del aldeano a buscar: ");
                                id = s.nextInt();
                                Aldeano a = aldea.buscarAldeano(id);
                                System.out.println("\nSe encontró el aldeano:");
                                System.out.println(a.aTabla());
                                break;
                            }
                        }
                        break;
                    } //ALDEANOS
                    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                    case 2: {
                        switch (menu2) {
                            case 1: {
                                if (tipoUsuario.equalsIgnoreCase("Creativo") ||
                                    tipoUsuario.equalsIgnoreCase("OP")) {
                                        // CARGAR ANIMAL
                                        System.out.print("[1] Oveja \n[2] Panda \n[2] Lobo\nIngrese un mob: ");
                                        Animal a = crearAnimal(s.nextInt());
                                        if (aldea.agregarAnimal(a)) {
                                            System.out.println("Se agregó al animalito correctamente.");
                                            aldea.guardarCambios("ArchivoAnimales");
                                        } else System.out.println("No se pudo agregar al animal.");

                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 2: {
                                System.out.println(aldea.AnimalesToTable());
                                break;
                            }
                            case 3: {
                                if (!tipoUsuario.equalsIgnoreCase("Espectador")) {
                                    int id;
                                    System.out.print("Ingrese el id del animalito a eliminar: ");
                                    id = s.nextInt();
                                    if (aldea.eliminarAnimal(id)){
                                        System.out.println("Se eliminó el animalito :c .");
                                        aldea.guardarCambios("ArchivoAnimales");
                                    }
                                    else System.out.println("No se pudo eliminar el animal.");

                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 4: {
                                int id;
                                System.out.print("Ingrese el id del animal a buscar: ");
                                id = s.nextInt();
                                Animal a = aldea.buscarAnimal(id);
                                System.out.println("\nSe encontró el animal:");
                                System.out.println(a.aTabla());
                                break;
                            }
                            case 5: {
                                if (!tipoUsuario.equalsIgnoreCase("Espectador")) {
                                    int id;
                                    System.out.print("Ingrese el id del lobo a buscar: ");
                                    id = s.nextInt();
                                    System.out.print("Ingrese su id: ");
                                    if ( aldea.domesticarLobito(id, s.nextInt()) ) System.out.println("HA ADOPTADO AL LOBO!!.");
                                    else System.out.println("No se pudo domesticar al lobo.");

                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                        }
                        break;
                    } //ANIMALES
                    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                    case 3: {
                        switch (menu2) {
                            case 1: {
                                if (tipoUsuario.equalsIgnoreCase("Creativo") ||
                                    tipoUsuario.equalsIgnoreCase("OP")) {
                                        //AGREGAR MOB
                                        System.out.print("[1] Creeper \n[2] Zombie \nIngrese un mob: ");
                                        Mob m = crearMobHostil(s.nextInt());
                                        if (aldea.agregarHostil(m)) {
                                            System.out.println("Se agregó al mob correctamente.");
                                            aldea.guardarCambios("ArchivoHostiles");
                                        }
                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 2: {
                                System.out.println(aldea.HostilesToTable());
                                break;
                            }
                            case 3: {
                                if (!tipoUsuario.equalsIgnoreCase("Espectador")) {
                                    int id;
                                    System.out.print("Ingrese el id del mob a eliminar: ");
                                    id = s.nextInt();
                                    if (aldea.eliminarHostiles(id)) {
                                        System.out.println("Se eliminó el mob.");
                                        aldea.guardarCambios("ArchivoHostiles");
                                    }
                                    else System.out.println("No se pudo eliminar el mob.");

                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 4: {
                                int id;
                                System.out.print("Ingrese el id del mob a buscar: ");
                                id = s.nextInt();
                                Mob m = aldea.buscarMobHostil(id);
                                System.out.println("\nSe encontró el mob:");
                                System.out.println(m.aTabla());
                                break;
                            }
                        }
                        break;
                    } //HOSTILES
                    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                    case 4: {
                        switch (menu2) {
                            case 1: {
                                if (tipoUsuario.equalsIgnoreCase("Creativo") ||
                                    tipoUsuario.equalsIgnoreCase("OP")) {
                                        System.out.print("Ingrese el id del mob que desea meter preso: ");
                                        int id = s.nextInt();
                                        System.out.print("Ingrese la fecha de liberación (dd/MM/yyyy): ");
                                        LocalDate fechaSalida = Celda.stringToLocalDate(s.next());

                                        if ( aldea.encarcelar(id, LocalDate.now(), fechaSalida ) ){
                                            System.out.println("El mob se fue en cana.");
                                            aldea.guardarCambios("ArchivoCarcel");
                                        }
                                        else System.out.println("No se pudo encarcelar el mob.");

                                        break;
                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 2: {
                                System.out.println(aldea.verCarcel());
                                break;
                            }
                            case 3: {
                                System.out.println(aldea.verEncarcelados());
                                break;
                            }
                            case 4: {
                                System.out.println(aldea.celdasDesocupadas());
                                break;
                            }
                            case 5: {
                                if (tipoUsuario.equalsIgnoreCase("Creativo") ||
                                    tipoUsuario.equalsIgnoreCase("OP")) {
                                        System.out.print("Ingrese el id de la celda: ");
                                        int idCelda = s.nextInt();

                                        if ( aldea.liberaMob(idCelda) ) {
                                            System.out.println("Se liberó el mob correctamente!");
                                            aldea.guardarCambios("ArchivoCarcel");
                                        }
                                        else System.out.println("No se pudo liberar el mob.");


                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                        }
                        break;
                    } //CARCEL
                    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                    case 5: {
                        switch (menu2) {
                            case 1: {
                                if (tipoUsuario.equalsIgnoreCase("OP")) {
                                    Player p = crearPlayer();
                                    if (aldea.agregarPlayer(p)) {
                                        System.out.println("Se agregó el jugador correctamente.");
                                        aldea.guardarCambios("ArchivoUsuarios");
                                    } else System.out.println("No se pudo agregar el jugador.");
                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 2: {
                                System.out.println(aldea.JugadoresToTable());
                                break;
                            }
                            case 3: {
                                if (tipoUsuario.equalsIgnoreCase("OP")) {
                                    System.out.print("Ingrese el id del jugador a eliminar: ");
                                    int id = s.nextInt();
                                    if ( aldea.eliminarPlayer(id) ) {
                                        System.out.println("Se eliminó el jugador de la whitelist.");
                                        aldea.guardarCambios("ArchivoUsuarios");
                                    }
                                    else System.out.println("No se pudo eliminar el jugador de la whitelist.");

                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                            case 4: {
                                if (tipoUsuario.equalsIgnoreCase("OP")) {
                                    System.out.print("Ingrese el id del jugador: ");
                                    int id = s.nextInt();
                                    System.out.println(aldea.buscarPlayer(id).aTabla());

                                } else System.out.println("Opción no permitida.");
                                break;
                            }
                        }
                        break;
                    } //JUGADORES
                    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                    case 6: {
                        System.out.println(aldea.todoToTable());
                        break;
                    } //Mostrar todos los mobs
                    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
                    case 7: {
                        System.out.print("Ingrese el id a buscar: ");
                        int id;
                        id = s.nextInt();
                        Entidad e = aldea.buscarEntidad(id);
                        String tabla = e.aTabla();
                        System.out.println(tabla);

                        break;
                    } //Buscar por id general
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
            } catch (InputMismatchException e){
                System.out.println("Error. Ingresar numeros.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            s.nextLine();
            if ( menu1 != 0 ){ //Si no eligió salir...
                System.out.println();
                pausa();
                System.out.println("\n");
            }

        }
        s.close();
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
                [6] Mostrar todo
                [7] Buscar por id
                
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
                if (tipoUsuario.equalsIgnoreCase("Espectador") ||
                    tipoUsuario.equalsIgnoreCase("Survival")){
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

    private void MINECRAFT() {
        String bloque = "\033[37;47m█\033[0m";
        String espacio = "\033[30;40m█\033[0m";
        System.out.println(espacio.repeat(144));
        System.out.println(espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(8) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(4) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(2) + bloque.repeat(16) + espacio.repeat(2) + bloque.repeat(14) + espacio.repeat(2) + bloque.repeat(14) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(2) + bloque.repeat(14) + espacio.repeat(2));
        System.out.println(espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(8) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(4) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(2) + bloque.repeat(16) + espacio.repeat(2) + bloque.repeat(14) + espacio.repeat(2) + bloque.repeat(14) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(2) + bloque.repeat(14) + espacio.repeat(2));
        System.out.println(espacio.repeat(2) + bloque.repeat(8) + espacio.repeat(4) + bloque.repeat(8) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(8) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(8) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(4) + espacio.repeat(2) + bloque.repeat(2) + espacio.repeat(2) + bloque.repeat(4) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(6));
        System.out.println(espacio.repeat(2) + bloque.repeat(8) + espacio.repeat(4) + bloque.repeat(8) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(8) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(8) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2)  + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(6));
        System.out.println(espacio.repeat(2) + bloque.repeat(20) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(16) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(14) + espacio.repeat(2) + bloque.repeat(4) + espacio.repeat(6) + bloque.repeat(4) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(6) + bloque.repeat(6) + espacio.repeat(6));
        System.out.println(espacio.repeat(2) + bloque.repeat(20) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(16) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(12) + espacio.repeat(4) + bloque.repeat(4) + espacio.repeat(2) + bloque.repeat(2) + espacio.repeat(2) + bloque.repeat(4) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(6) + bloque.repeat(6) + espacio.repeat(6));
        System.out.println(espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(4) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(8) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(8) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(14) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(6));
        System.out.println(espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(4) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(8) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(8) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(6));
        System.out.println(espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(8) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(4) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(2) + bloque.repeat(16) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(6));
        System.out.println(espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(8) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(4) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(12) + espacio.repeat(2) + bloque.repeat(16) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(2) + bloque.repeat(6) + espacio.repeat(12) + bloque.repeat(6) + espacio.repeat(6));
        System.out.println(espacio.repeat(144));
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo CARGAR
    //Aldeano
    private Aldeano crearAldeano() throws Atributo_vacio_Exception, Valor_de_atributo_no_valido_Exception, InputMismatchException{
        Aldeano a = new Aldeano();
        Scanner s = new Scanner(System.in);

        String nombre, tipoAldeano;
        int opcion;

        System.out.print("Ingrese el nombre del aldeano: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        a.setNombre(nombre);

        System.out.print("El Aldeano es bebé?: 1-Si  2-No: ");
        opcion=s.nextInt();
        if (opcion == 1) a.setEsBebe(true);
        else if (opcion == 2) a.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.print("""
            - DESEMPLEADO
            - VAGO
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
        tipoAldeano = s.next().toUpperCase();
        a.setProfesion(tipoAldeano);

        return a;
    }

    //Mobs
    /// @param tipo
    /// [1] Creeper
    /// [2] Zombie
    private Mob crearMobHostil(int tipo) throws Valor_de_atributo_no_valido_Exception, Formato_no_valido_Exception, Atributo_vacio_Exception{
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
                throw new Formato_no_valido_Exception("Opcion ingresada incorrecta.");
            }
        }

        return m;
    }
    private Creeper crearCreeper() throws Atributo_vacio_Exception, Formato_no_valido_Exception {
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

        return c;
    }
    private Zombie crearZombie() throws Atributo_vacio_Exception, Valor_de_atributo_no_valido_Exception{
        Zombie z = new Zombie();
        Scanner s = new Scanner(System.in);

        String nombre, opcion, tipoZombie;

        System.out.print("Ingrese el nombre del zombie: ");
        nombre = s.nextLine();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        z.setNombre(nombre);

        System.out.print("Ingrese el tipo de zombie [Chiquito,Ahogado,Momificado,Comun,Aldeano]: ");
        tipoZombie = s.nextLine().toUpperCase();
        z.setTipoZombie(tipoZombie);

        return z;
    }

    //Animales
    /// @param tipo
    /// [1] Oveja
    /// [2] Panda
    /// [3] Lobo
    private Animal crearAnimal(int tipo) throws Valor_de_atributo_no_valido_Exception, Atributo_vacio_Exception, InputMismatchException {
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
    private Panda crearPanda() throws Atributo_vacio_Exception, Valor_de_atributo_no_valido_Exception, InputMismatchException {
        Panda p=new Panda();
        Scanner s=new Scanner(System.in);

        String nombre, tipoAlimentacion;
        int opcion;

        System.out.print("Ingrese el nombre del panda: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        p.setNombre(nombre);

        System.out.print("El panda es bebé? [1] SIIII :3 ,[2]-No. Está grande ya: ");
        opcion=s.nextInt();
        if (opcion == 1) p.setEsBebe(true);
        else if (opcion == 0) p.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.print("Ingrese el tipo de alimentación que tiene su panda [CARNIVORO, HERVIVORO, OMNIVORO]: ");
        tipoAlimentacion = s.next().toUpperCase();
        p.setTipoAlimentacion(tipoAlimentacion);

        int num = (int) (Math.random() * 8);//Un numero aleatorio entre 0 y 7. Esto porq en el juego el "caracter" del panda es alatorio.
        Gen_Panda[] genPandas = Gen_Panda.values();
        p.setGen( genPandas[num] );

        return p;
    }
    private Lobo crearLobo() throws Atributo_vacio_Exception, Valor_de_atributo_no_valido_Exception, InputMismatchException {
        Lobo l= new Lobo();
        Scanner s=new Scanner(System.in);

        String nombre, tipoAlimentacion;
        int opcion;

        System.out.print("Ingrese el nombre del lobo: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        l.setNombre(nombre);

        System.out.print("El panda es bebé? [1] SIIII :3 ,[2]-No. Está grande ya: ");
        opcion=s.nextInt();
        if (opcion == 1) l.setEsBebe(true);
        else if (opcion == 0) l.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.print("Ingrese el tipo de alimentación que tiene su lobo [CARNIVORO, HERVIVORO, OMNIVORO]: ");
        tipoAlimentacion = s.next();
        l.setTipoAlimentacion(tipoAlimentacion);

        System.out.println("Desea domesticar al lobo? : \n 1-SIP \n 0-NOP ");
        int decision =s.nextInt();

        if (decision ==1 ){
            System.out.println("Ingrese su id :");
            int id=s.nextInt();
            if (l.domesticarLobo(id)) System.out.println("HA DOMESTICADO A SU LOBO!!");
            else System.out.println("No se pudo domesticar el lobo.");
        } else {
            l.setDomesticado(false);
            l.setIDduenio(0);
        }

        return l;
    }
    private Oveja crearOveja() throws Atributo_vacio_Exception, Valor_de_atributo_no_valido_Exception, InputMismatchException {
        Oveja o= new Oveja();
        Scanner s=new Scanner(System.in);

        String mensaje, nombre, color, tipoAlimentacion;
        int opcion;

        System.out.print("Ingrese el nombre de la oveja [CARNIVORO, HERVIVORO, OMNIVORO]: ");
        nombre = s.next();
        if (s.next().isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        o.setNombre(nombre);


        System.out.print("Ingrese el tipo de alimentación que tiene su panda: ");
        tipoAlimentacion = s.next();

        o.setTipoAlimentacion(tipoAlimentacion);
        System.out.println("El tipo de alimentación fue asignado correctamente.");

        System.out.print("Ingrese el color: ");
        color = s.next();
        if (color.isBlank()) throw new Atributo_vacio_Exception("El color está vacio");
        o.setColor(color);

        System.out.print("El panda es bebé? [1] SIIII :3 ,[2]-No. Está grande ya: ");
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

        return o;
    }

    //Jugador
    private Player crearPlayer() throws Atributo_vacio_Exception, Valor_de_atributo_no_valido_Exception, InputMismatchException {
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
        else if (eleccion == 'N' || eleccion == 'n') p.setEsPremium(false);
        else throw new Formato_no_valido_Exception("Carácter ingresado no válido.");

        System.out.print("Ingrese el gamemode [Survival-Creativo-Espectador-OP]: ");
        if ( !p.setTipoPlayer(s.next()) ) throw new Valor_de_atributo_no_valido_Exception("Tipo de player no valido.");

        return p;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════




}
