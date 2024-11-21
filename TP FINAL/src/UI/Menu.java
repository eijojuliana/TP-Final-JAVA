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
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

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
                    System.out.println(ANSI_RED + "Solo numeros." + ANSI_RESET );
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
                                        System.out.print("[1] Oveja \n[2] Panda \n[3] Lobo\nIngrese un mob: ");
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
                                System.out.println(aldea.carcelToTable());
                                break;
                            }
                            case 3: {
                                System.out.print("Ingrese el id de la celda: ");
                                int id = s.nextInt();
                                System.out.println(aldea.infoCelda(id));
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
                            case 6:{
                                System.out.println("Cantidad de celdas desocupadas: " + aldea.cantCeldasDesocupadas() );
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
                //e.printStackTrace();
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
                ██▓▒░⡷⠂𝙶𝚎𝚜𝚝𝚒𝚘𝚗 𝚍𝚎 𝙼𝚒𝚗𝚎𝚌𝚛𝚊𝚏𝚝⠐⢾░▒▓██
                
                𓆩--  𝔸𝕃𝔻𝔼𝔸  --𓆪
                [𝟙] 𝔸𝕝𝕕𝕖𝕒𝕟𝕠𝕤
                [𝟚] 𝔸𝕟𝕚𝕞𝕒𝕝𝕖𝕤
                [𝟛] ℍ𝕠𝕤𝕥𝕚𝕝𝕖𝕤
                [𝟜] ℂ𝕒𝕣𝕔𝕖𝕝
                
                𓆩-- 𝕌𝕊𝕌𝔸ℝ𝕀𝕆𝕊 --𓆪
                [𝟝] 𝕁𝕦𝕘𝕒𝕕𝕠𝕣𝕖𝕤
                
                𓆩--  𝕋𝕆𝔻𝕆𝕊  --𓆪
                [𝟞] 𝕄𝕠𝕤𝕥𝕣𝕒𝕣 𝕥𝕠𝕕𝕠
                [𝟟] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                
                𓆩--- 𝕊𝕀𝕊𝕋𝔼𝕄𝔸 --𓆪
                [𝟘] 𝕊𝕒𝕝𝕚𝕣
                
                𝕀𝕟𝕘𝕣𝕖𝕤𝕖 𝕦𝕟𝕒 𝕠𝕡𝕔𝕚ó𝕟:""");
        System.out.print(" ");
    }
    private void printMenu2(int menu1, String tipoUsuario){

        System.out.print(" ██▓▒░⡷⠂𝙶𝚎𝚜𝚝𝚒𝚘𝚗⠐⢾░▒▓██");
        switch (menu1){
            case 1: {
                System.out.println(""" 
                                      \n𓆩𝔸𝕝𝕕𝕖𝕒𝕟𝕠𝕤𓆪""");
                if (tipoUsuario.equalsIgnoreCase("Espectador")){
                    System.out.print("""   
            
                            [̶̶̶𝟙]̶̶̶ ̶̶̶ℂ̶̶̶𝕒̶̶̶𝕣̶̶̶𝕘̶̶̶𝕒̶̶̶𝕣̶̶̶ ̶̶̶𝕦̶𝕟̶ ̶𝕒̶𝕝̶𝕕̶𝕖̶𝕒̶𝕟̶𝕠̶
                            [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕒𝕝𝕕𝕖𝕒𝕟𝕠𝕤
                            [̶𝟛]̶ ̶𝔼̶𝕝̶𝕚̶𝕞̶𝕚̶𝕟̶𝕒̶𝕣̶ ̶𝕦̶𝕟̶ ̶𝕒̶𝕝̶𝕕̶𝕖̶𝕒̶𝕟̶𝕠̶ ̶𝕡̶𝕠̶𝕣̶ ̶𝕚̶𝕕̶
                            [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("Survival")) {
                    System.out.print("""   
                            
                            [̶̶̶𝟙]̶̶̶ ̶̶̶ℂ̶̶̶𝕒̶̶̶𝕣̶̶̶𝕘̶̶̶𝕒̶̶̶𝕣̶̶̶ ̶̶̶𝕦̶𝕟̶ ̶𝕒̶𝕝̶𝕕̶𝕖̶𝕒̶𝕟̶𝕠̶
                            [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕒𝕝𝕕𝕖𝕒𝕟𝕠𝕤
                            [𝟛] 𝔼𝕝𝕚𝕞𝕚𝕟𝕒𝕣 𝕦𝕟 𝕒𝕝𝕕𝕖𝕒𝕟𝕠 𝕡𝕠𝕣 𝕚𝕕
                            [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                            
                            """);
                } else if ( tipoUsuario.equalsIgnoreCase("Creativo") ||
                        tipoUsuario.equalsIgnoreCase("OP") ) {
                    System.out.print("""   
                            
                            [𝟙] ℂ𝕒𝕣𝕘𝕒𝕣 𝕦𝕟 𝕒𝕝𝕕𝕖𝕒𝕟𝕠
                            [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕒𝕝𝕕𝕖𝕒𝕟𝕠𝕤
                            [𝟛] 𝔼𝕝𝕚𝕞𝕚𝕟𝕒𝕣 𝕦𝕟 𝕒𝕝𝕕𝕖𝕒𝕟𝕠 𝕡𝕠𝕣 𝕚𝕕
                            [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                            
                            """);
                }
                break;
            }
            case 2: {
                System.out.println("""
                                      \n𓆩𝔸ℕ𝕀𝕄𝔸𝕃𝔼𝕊:𓆪""");
                if (tipoUsuario.equalsIgnoreCase("Espectador")){
                    System.out.print("""   
            
                        [̶̶̶𝟙]̶ ̶̶̶ℂ̶̶̶𝕒̶̶̶𝕣̶̶̶𝕘̶̶̶𝕒̶̶̶𝕣̶̶̶ ̶̶̶𝕦̶𝕟̶ ̶𝕒̶𝕟̶𝕚̶𝕞̶𝕒̶𝕝̶
                        [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕒𝕟𝕚𝕞𝕒𝕝𝕖𝕤
                        [̶𝟛]̶ ̶𝔼̶𝕝̶𝕚̶𝕞̶𝕚̶𝕟̶𝕒̶𝕣̶ ̶𝕦̶𝕟̶ ̶𝕒̶𝕟̶𝕚̶𝕞̶𝕒̶𝕝̶ ̶𝕡̶𝕠̶𝕣̶ ̶𝕚̶𝕕̶
                        [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                        [̶𝟝]̶ ̶𝔻̶𝕠̶𝕞̶𝕖̶𝕤̶𝕥̶𝕚̶𝕔̶𝕒̶𝕣̶ ̶𝕝̶𝕠̶𝕓̶𝕠̶
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("Survival")){
                    System.out.print("""   
            
                        [̶̶̶𝟙]̶ ̶̶̶ℂ̶̶̶𝕒̶̶̶𝕣̶̶̶𝕘̶̶̶𝕒̶̶̶𝕣̶̶̶ ̶̶̶𝕦̶𝕟̶ ̶𝕒̶𝕟̶𝕚̶𝕞̶𝕒̶𝕝̶
                        [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕒𝕟𝕚𝕞𝕒𝕝𝕖𝕤
                        [𝟛] 𝔼𝕝𝕚𝕞𝕚𝕟𝕒𝕣 𝕦𝕟 𝕒𝕟𝕚𝕞𝕒𝕝 𝕡𝕠𝕣 𝕚𝕕
                        [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                        [𝟝] 𝔻𝕠𝕞𝕖𝕤𝕥𝕚𝕔𝕒𝕣 𝕝𝕠𝕓𝕠
                        
                        """);
                } else if ( tipoUsuario.equalsIgnoreCase("Creativo") ||
                        tipoUsuario.equalsIgnoreCase("OP") ) {
                    System.out.print("""   
                            
                            [𝟙] ℂ𝕒𝕣𝕘𝕒𝕣 𝕦𝕟 𝕒𝕟𝕚𝕞𝕒𝕝
                            [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕒𝕟𝕚𝕞𝕒𝕝𝕖𝕤
                            [𝟛] 𝔼𝕝𝕚𝕞𝕚𝕟𝕒𝕣 𝕦𝕟 𝕒𝕟𝕚𝕞𝕒𝕝 𝕡𝕠𝕣 𝕚𝕕
                            [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                            [𝟝] 𝔻𝕠𝕞𝕖𝕤𝕥𝕚𝕔𝕒𝕣 𝕝𝕠𝕓𝕠
                            
                            """);
                }
                break;
            }
            case 3: {
                System.out.println("""
                                       𓆩ℍ𝕆𝕊𝕋𝕀𝕃𝔼𝕊:𓆪"""  );
                if (tipoUsuario.equalsIgnoreCase("Espectador")){
                    System.out.print("""   
            
                        [̶̶̶𝟙]̶̶̶ ̶̶̶ℂ̶𝕒̶𝕣̶𝕘̶𝕒̶𝕣̶ ̶𝕦̶𝕟̶ ̶𝕞̶𝕠̶𝕓̶ ̶𝕙̶𝕠̶𝕤̶𝕥̶𝕚̶𝕝̶
                        [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕞𝕠𝕓𝕤 𝕙𝕠𝕤𝕥𝕚𝕝𝕖𝕤
                        [̶𝟛]̶ ̶𝔼̶𝕝̶𝕚̶𝕞̶𝕚̶𝕟̶𝕒̶𝕣̶ ̶𝕦̶𝕟̶ ̶𝕞̶𝕠̶𝕓̶ ̶𝕡̶𝕠̶𝕣̶ ̶𝕚̶𝕕̶
                        [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("Survival")){
                    System.out.print("""   
            
                        [̶̶̶𝟙]̶̶̶ ̶̶̶ℂ̶𝕒̶𝕣̶𝕘̶𝕒̶𝕣̶ ̶𝕦̶𝕟̶ ̶𝕞̶𝕠̶𝕓̶ ̶𝕙̶𝕠̶𝕤̶𝕥̶𝕚̶𝕝̶
                        [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕞𝕠𝕓 𝕙𝕠𝕤𝕥𝕚𝕝𝕖𝕤
                        [𝟛] 𝔼𝕝𝕚𝕞𝕚𝕟𝕒𝕣 𝕦𝕟 𝕞𝕠𝕓 𝕡𝕠𝕣 𝕚𝕕
                        [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                        
                        """);
                } else if ( tipoUsuario.equalsIgnoreCase("Creativo") ||
                        tipoUsuario.equalsIgnoreCase("OP") ) {
                    System.out.print("""   
                            
                            [𝟙] ℂ𝕒𝕣𝕘𝕒𝕣 𝕦𝕟 𝕞𝕠𝕓 𝕙𝕠𝕤𝕥𝕚𝕝
                            [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕞𝕠𝕓 𝕙𝕠𝕤𝕥𝕚𝕝𝕖𝕤
                            [𝟛] 𝔼𝕝𝕚𝕞𝕚𝕟𝕒𝕣 𝕦𝕟 𝕞𝕠𝕓 𝕡𝕠𝕣 𝕚𝕕
                            [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                            
                            """);
                }
                break;
            }
            case 4: {
                System.out.println("""
                                       𓆩ℂ𝔸ℝℂ𝔼𝕃𓆪""" );
                if (tipoUsuario.equalsIgnoreCase("Espectador") ||
                        tipoUsuario.equalsIgnoreCase("Survival")){
                    System.out.print("""   
            
                        [̶𝟙]̶ ̶𝔸̶𝕘̶𝕣̶𝕖̶𝕘̶𝕒̶𝕣̶ ̶𝕞̶𝕠̶𝕓̶ ̶𝕒̶ ̶𝕝̶𝕒̶ ̶𝕔̶𝕒̶𝕣̶𝕔̶𝕖̶𝕝̶
                        [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕒𝕤 𝕝𝕒𝕤 𝕔𝕖𝕝𝕕𝕒𝕤
                        [𝟛] 𝕍𝕖𝕣 𝕚𝕟𝕗𝕠 𝕞𝕠𝕓 𝕖𝕟𝕔𝕒𝕣𝕔𝕖𝕝𝕒𝕕𝕠
                        [𝟜] 𝕍𝕖𝕣 𝕔𝕖𝕝𝕕𝕒𝕤 𝕝𝕚𝕓𝕣𝕖𝕤
                        [̶𝟝]̶̶ ̶𝕃̶𝕚̶𝕓̶𝕖̶𝕣̶𝕒̶𝕣̶ ̶𝕞̶𝕠̶𝕓̶
                        [𝟞] ℂ𝕠𝕟𝕥𝕒𝕣 𝕔𝕒𝕟𝕥𝕚𝕕𝕒𝕕 𝕕𝕖 𝕔𝕖𝕝𝕕𝕒𝕤 𝕕𝕖𝕤𝕠𝕔𝕦𝕡𝕒𝕕𝕒𝕤
                        
                        """);
                } else if ( tipoUsuario.equalsIgnoreCase("Creativo") ||
                        tipoUsuario.equalsIgnoreCase("OP") ) {
                    System.out.print("""   
                            
                            [𝟙] 𝔸𝕘𝕣𝕖𝕘𝕒𝕣 𝕦𝕟 𝕞𝕠𝕓 𝕒 𝕝𝕒 𝕔𝕒𝕣𝕔𝕖𝕝
                            [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕒𝕤 𝕝𝕒𝕤 𝕔𝕖𝕝𝕕𝕒𝕤
                            [𝟛] 𝕍𝕖𝕣 𝕚𝕟𝕗𝕠 𝕞𝕠𝕓 𝕖𝕟𝕔𝕒𝕣𝕔𝕖𝕝𝕒𝕕𝕠
                            [𝟜] 𝕍𝕖𝕣 𝕔𝕖𝕝𝕕𝕒𝕤 𝕝𝕚𝕓𝕣𝕖𝕤
                            [𝟝] 𝕃𝕚𝕓𝕖𝕣𝕒𝕣 𝕞𝕠𝕓
                            [𝟞] ℂ𝕠𝕟𝕥𝕒𝕣 𝕔𝕒𝕟𝕥𝕚𝕕𝕒𝕕 𝕕𝕖 𝕔𝕖𝕝𝕕𝕒𝕤 𝕕𝕖𝕤𝕠𝕔𝕦𝕡𝕒𝕕𝕒𝕤
                            
                            """);
                }
                break;
            }
            case 5:{
                System.out.println("""
                                       𓆩𝕁𝕌𝔾𝔸𝔻𝕆ℝ𝔼𝕊:𓆪"""  );
                if (tipoUsuario.equalsIgnoreCase("Espectador") ||
                        tipoUsuario.equalsIgnoreCase("Survival") ||
                        tipoUsuario.equalsIgnoreCase("Creativo") ){
                    System.out.print("""   
            
                        [̶𝟙]̶ ̶𝔸̶𝕘̶𝕣̶𝕖̶𝕘̶𝕒̶𝕣̶ ̶𝕛̶𝕦̶𝕘̶𝕒̶𝕕̶𝕠̶𝕣̶ ̶𝕒̶ ̶𝕝̶𝕒̶ ̶𝕨̶𝕙̶𝕚̶𝕥̶𝕖̶𝕝̶𝕚̶𝕤̶𝕥̶
                        [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕛𝕦𝕘𝕒𝕕𝕠𝕣𝕖𝕤.
                        [̶𝟛]̶ ̶𝔼̶𝕝̶𝕚̶𝕞̶𝕚̶𝕟̶𝕒̶𝕣̶ ̶𝕛̶𝕦̶𝕘̶𝕒̶𝕕̶𝕠̶𝕣̶ ̶𝕕̶𝕖̶ ̶𝕝̶𝕒̶ ̶𝕨̶𝕙̶𝕚̶𝕥̶𝕖̶𝕝̶𝕚̶𝕤̶𝕥̶
                        [̶̶̶𝟜]̶̶ ̶̶̶𝔹̶𝕦̶𝕤̶𝕔̶𝕒̶𝕣̶ ̶𝕡̶𝕠̶𝕣̶ ̶𝕚̶𝕕̶
                        
                        """);
                } else if (tipoUsuario.equalsIgnoreCase("OP")){
                    System.out.print("""   
            
                        [𝟙] 𝔸𝕘𝕣𝕖𝕘𝕒𝕣 𝕛𝕦𝕘𝕒𝕕𝕠𝕣 𝕒 𝕝𝕒 𝕨𝕙𝕚𝕥𝕖𝕝𝕚𝕤𝕥
                        [𝟚] 𝕍𝕖𝕣 𝕥𝕠𝕕𝕠𝕤 𝕝𝕠𝕤 𝕛𝕦𝕘𝕒𝕕𝕠𝕣𝕖𝕤.
                        [𝟛] 𝔼𝕝𝕚𝕞𝕚𝕟𝕒𝕣 𝕛𝕦𝕘𝕒𝕕𝕠𝕣 𝕕𝕖 𝕝𝕒 𝕨𝕙𝕚𝕥𝕖𝕝𝕚𝕤𝕥
                        [𝟜] 𝔹𝕦𝕤𝕔𝕒𝕣 𝕡𝕠𝕣 𝕚𝕕
                        
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
        //s.close();
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

        s.close();
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

        s.close();
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

        s.close();
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

        System.out.print("El oveja es bebé? [1] SIIII :3 ,[0]-No. Está grande ya: ");
        opcion=s.nextInt();
        if (opcion == 1) p.setEsBebe(true);
        else if (opcion == 0) p.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.print("Ingrese el tipo de alimentación que tiene su panda [CARNIVORO, HERBIVORO, OMNIVORO]: ");
        tipoAlimentacion = s.next().toUpperCase();
        p.setTipoAlimentacion(tipoAlimentacion);

        int num = (int) (Math.random() * 8);//Un numero aleatorio entre 0 y 7. Esto porq en el juego el "caracter" del panda es alatorio.
        Gen_Panda[] genPandas = Gen_Panda.values();
        p.setGen( genPandas[num] );

        s.close();
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

        System.out.print("El panda es bebé? [1] SIIII :3 ,[0]-No. Está grande ya: ");
        opcion=s.nextInt();
        if (opcion == 1) l.setEsBebe(true);
        else if (opcion == 0) l.setEsBebe(false);
        else throw new Valor_de_atributo_no_valido_Exception("Valor cargado no válido.");

        System.out.print("Ingrese el tipo de alimentación que tiene su lobo [CARNIVORO, HERBIVORO, OMNIVORO]: ");
        tipoAlimentacion = s.next().toUpperCase();
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

        s.close();
        return l;
    }
    private Oveja crearOveja() throws Atributo_vacio_Exception, Valor_de_atributo_no_valido_Exception, InputMismatchException {
        Oveja o= new Oveja();
        Scanner s=new Scanner(System.in);

        String mensaje, nombre, color, tipoAlimentacion;
        int opcion;

        System.out.print("Ingrese el nombre de la oveja: ");
        nombre = s.next();
        if (nombre.isBlank()) throw new Atributo_vacio_Exception("El nombre está vacio");
        o.setNombre(nombre);

        System.out.print("Ingrese el tipo de alimentación que tiene su oveja [CARNIVORO, HERBIVORO, OMNIVORO]: ");
        tipoAlimentacion = s.next().toUpperCase();
        o.setTipoAlimentacion(tipoAlimentacion);
        System.out.println("El tipo de alimentación fue asignado correctamente.");

        System.out.print("Ingrese el color: ");
        color = s.next();
        if (color.isBlank()) throw new Atributo_vacio_Exception("El color está vacio");
        o.setColor(color);

        System.out.print("El panda es bebé? [1] SIIII :3 ,[0]-No. Está grande ya: ");
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

        s.close();
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

        s.close();
        return p;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════




}
