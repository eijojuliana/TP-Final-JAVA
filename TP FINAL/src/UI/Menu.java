package UI;

import PackageContenedores.AlmacenamientoNPC;
import PackageJSON.JSONUtiles;
import PackageModelo.Creeper;
import PackageModelo.Entidad;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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








}
