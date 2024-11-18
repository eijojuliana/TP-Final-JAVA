import UI.Login;
import UI.Menu;

public class Main {
    public static void main(String[] args) {
        boolean bucle = true;
        Login l = new Login();
        Menu m = new Menu();
        String tipoUsuario = null;

        while (bucle){
            tipoUsuario = l.iniciarLogin();
            if ( tipoUsuario != null ) bucle = false;
            System.out.println();
        }

        m.iniciarMenu( tipoUsuario );

    }
}