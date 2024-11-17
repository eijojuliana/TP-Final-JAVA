package UI;

import PackageExceptions.Usuario_no_encontrado_Exception;
import PackageJSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class Login {


    public String iniciarLogin(){
        Scanner s = new Scanner(System.in);
        String usuario;
        int contrasenia;
        String tipoUsuario = null;

        System.out.print("Usuario: ");
        usuario = s.next();
        System.out.print("Contraseña: ");
        contrasenia = s.nextInt();

        try {
            JSONArray jArrray = new JSONArray( JSONUtiles.leer("jugadores") );
            for (int i=0; i<jArrray.length() ; i++){
                JSONObject jObject = new JSONObject();
                if ( jObject.getString("nombre").equals(usuario) && jObject.getInt("contrasenia") == contrasenia ){
                    tipoUsuario = jObject.getString("tipoPlayer");
                    break;
                }
            }
            if ( tipoUsuario == null ) throw new Usuario_no_encontrado_Exception("Nombre o contraseña incorrectos.");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return tipoUsuario;
    }

}
