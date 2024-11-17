package UI;

import PackageJSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Scanner;

public class Login {


    public void iniciarLogin(){

        Scanner s = new Scanner(System.in);
        String usuario;

        System.out.print("Usuario: ");
        usuario = s.next();
        try {
            JSONArray j = new JSONArray( JSONUtiles.leer("usuarios") );

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

}
