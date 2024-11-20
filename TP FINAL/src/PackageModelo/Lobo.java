package PackageModelo;

import PackageContenedores.Aldea;
import PackageEnum.TipoAlimentacion;
import PackageExceptions.Entidad_inexistente_Exception;
import PackageExceptions.Entidad_repetida_Exception;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

import javax.management.MalformedObjectNameException;
import java.util.ArrayList;

public final class Lobo extends Animal implements IConversionJSON, ITabla {
    ///todo.ATRIBUTOS///
    private boolean domesticado;
    private int IDduenio;

    ///todo.CONSTRUCTORES///
    public Lobo(String nombre, boolean esBebe, TipoAlimentacion tipoAlimentacion, boolean domesticado) {
        super(nombre, 4, 4.0, Lobo.class.getSimpleName(), esBebe, tipoAlimentacion);
        inicializar_vida();
        this.domesticado = domesticado;
        this.IDduenio = -1;
    }

    public Lobo() {
        setVida(4);
        setDanio(4);
        setIDduenio(-1);
    }

    ///todo.GETS Y SETS///
    public boolean isDomesticado() {
        return domesticado;
    }
    public int getIDduenio() {
        return IDduenio;
    }

    public void setDomesticado(boolean domesticado) {
        this.domesticado = domesticado;
    }
    public void setIDduenio(int IDduenio) {
        this.IDduenio = IDduenio;
    }

    //══TO STRING══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    @Override
    public String toString() {
        return "Lobo{" +
                "domesticado=" + domesticado +
                ", IDduenio=" + IDduenio +
                "} " + super.toString();
    }

    @Override
    public String aTabla() {
        return AsciiTable.getTable(new String[][] {
                {"Mob", getTipo() },
                {"ID", String.format("%d" ,getId()) },
                {"Nombre", getNombre()},
                {"Vida", String.format("%.2f", getVida())},
                {"Daño", String.format("%.2f", getDanio())},
                {"¿Es bebé?", esBebe ? "Sí" : "No"},
                {"Tipo alimentación", tipoAlimentacion.name()},
                {"¿Es domesticado?", domesticado ? "Sí" : "No"},
                {"IDduenio", String.format("%d", getIDduenio())}
        });
    }

    //══MÉTODOS══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    public boolean domesticarLobo(int id) throws Valor_de_atributo_no_valido_Exception, Entidad_inexistente_Exception, Entidad_repetida_Exception{
         boolean exito = false;

         if (id<0) throw new Valor_de_atributo_no_valido_Exception("ID Negativo.");
         if (!Aldea.existePlayerEnArchivo(id)) throw new Entidad_inexistente_Exception("No se encuentra el jugador.");

         if (isDomesticado()) throw new Entidad_repetida_Exception("El lobo ya se encuentra domesticado");

         setDomesticado(true);
         setIDduenio(id);
         exito = true;

        return exito;
    }

    private void inicializar_vida() { //Se podría hacer una interfaz IInicializar, ya que se repite en varias clases, pero por cuestiones de complejizar menos el código decidí hacerlo en cada clase que lo precise.
        if (isDomesticado()) this.vida = 10;
        else this.vida = 4;
    }

    //══JSON══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("domesticado", domesticado);
            j.put("IDduenio", IDduenio);
            j.put("tipo","Lobo");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return j;
    }

    @Override
    public boolean fromJSON(JSONObject j) {
        boolean exito = false;
        try {
            super.fromJSON(j);
            setDomesticado(j.getBoolean("domesticado"));
            setIDduenio(j.getInt("IDduenio"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
