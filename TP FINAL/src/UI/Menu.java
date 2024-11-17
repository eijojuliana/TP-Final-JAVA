package UI;

import PackageContenedores.AlmacenamientoNPC;
import PackageEnum.Gen_Panda;
import PackageEnum.TipoZombie;
import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Formato_no_valido_Exception;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageJSON.JSONUtiles;
import PackageModelo.*;
import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public void iniciarMenu(){
        boolean bucle = true;
        Scanner s = new Scanner(System.in);

        int menu1;

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


        while(bucle) {
            System.out.println(z.toStringCuadrito());
            System.out.printf("\n\n");
            System.out.println(z.toAsciiTable());
            System.out.printf("\n\n");
            System.out.println(arrayToAsciiTable(zombies));

            printMenu();
            menu1 = s.nextInt();
            switch (menu1) {
                case 1: {

                    carcel.agregar(c);
                    carcel.agregar(c2);
                    carcel.agregar(c3);

                    JSONUtiles.grabarUnJson(carcel.toJSON(),"carcel");
                    break;
                }
                case 2: {


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
                    break;
                }
                case 3: {

                    break;
                }


                case 0: {
                    bucle = false;
                    break;
                }
                default: {
                    System.out.println("Error al cargar una opción intente nuevamente.");
                    s.nextLine();
                    break;
                }
            }
            if ( bucle ){
                System.out.println();
                pausa();
                System.out.println("\n");
            }
        }

    }
    private void printMenu(){
        System.out.print("""
                GESTIÓN MINECRAFT:
                1. Agregar creepers y grabarlos.
                2. Leer archivo de creepers
                0. Salir
                Ingrese una opción:""");
        System.out.print(" ");
    }

    private void pausa(){
        Scanner s = new Scanner(System.in);
        System.out.print("Ingrese un caracter para continuar... ");
        s.nextLine();
    }

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


    public static String arrayToAsciiTable(ArrayList<Zombie> zombies) {
        // Verificar si el ArrayList está vacío
        if (zombies == null || zombies.isEmpty()) {
            return "No hay zombies para mostrar";
        }

        // Crear los datos para la tabla
        String[][] data = new String[zombies.size() + 1][6];
        data[0] = new String[]{"Nombre", "Vida", "Daño", "¿Es bebé?", "Tipo Zombie"};

        for (int i = 0; i < zombies.size(); i++) {
            Zombie zombie = zombies.get(i);
            data[i + 1] = new String[]{
                    zombie.getNombre(),
                    String.format("%.2f", zombie.getVida()),
                    String.format("%.2f", zombie.getDanio()),
                    zombie.isEsBebe()? "Sí" : "No",
                    zombie.getTipoZombie().name()
            };
        }
        return AsciiTable.getTable(data);
    }




}
