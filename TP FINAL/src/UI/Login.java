package UI;

import PackageExceptions.Usuario_no_encontrado_Exception;
import PackageJSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Login {


    public String iniciarLogin(){
        Scanner s = new Scanner(System.in);
        String usuario, contrasenia;
        String tipoUsuario = null;

        System.out.print("Usuario: ");
        usuario = s.next();
        System.out.print("Contraseña: ");
        contrasenia = s.next();

        try {
            JSONArray jArrray = new JSONArray( JSONUtiles.leer2("jugadores") );
            for (int i=0; i<jArrray.length() ; i++){
                JSONObject jObject = new JSONObject();
                if ( jObject.getString("nombre").equals(usuario) && jObject.getString("contrasenia").equals(contrasenia) ){
                    tipoUsuario = jObject.getString("tipoPlayer");
                    break;
                }
            }

        } catch (JSONException e) {
            System.out.println("No existe el archivo jugadores.");
        } catch (IOException e) {
            System.out.println("Archivo de usuarios no encontrado. Usando credenciales por defecto.");
            if (usuario.equals("admin") && contrasenia.equals("admin") ){
                tipoUsuario = "OP";
            }
        }
        if ( tipoUsuario == null ) System.out.println("Nombre o contraseña incorrectos.");

        return tipoUsuario;
    }

}
