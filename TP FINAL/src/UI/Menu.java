package UI;

import PackageContenedores.AlmacenamientoNPC;
import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Formato_no_valido_Exception;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageJSON.JSONUtiles;
import PackageModelo.*;
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

        AlmacenamientoNPC<Entidad> carcel = new AlmacenamientoNPC<>();

        while(bucle) {

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

          return p;
    }

    private Lobo crearLobo(){
    Lobo l= new Lobo();
    Scanner s=new Scanner(System.in);

    crearEntidad();
    System.out.println("Desea domesticar al lobo: \n 1-SIP \n 0-NOP ");
    int decision =s.nextInt();

    if (decision ==1 ){
        System.out.println("Ingrese su id :");
        int id=s.nextInt();
        String mensaje =l.domesticarLobo(id);
        System.out.println(mensaje);
    }
      return l;
    }







}
