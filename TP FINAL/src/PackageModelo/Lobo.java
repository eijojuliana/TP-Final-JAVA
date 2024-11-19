package PackageModelo;

import PackageEnum.TipoAlimentacion;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

import javax.management.MalformedObjectNameException;
import java.util.ArrayList;

public class Lobo extends Animal implements IConversionJSON, ITabla {
    ///todo.ATRIBUTOS///
    protected boolean domesticado;
    protected int IDduenio;

    ///todo.CONSTRUCTORES///
    public Lobo(String nombre, double vida, double danio, boolean esBebe, TipoAlimentacion tipoAlimentacion, boolean domesticado, int IDduenio) {
        super(nombre, vida, danio, Lobo.class.getSimpleName(), esBebe, tipoAlimentacion);
        this.domesticado = domesticado;
        this.IDduenio = IDduenio;
    }

    public Lobo() {
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

    @Override
    public String[] aFila() {
        return new String[]{
                String.format("%d" ,getId()),
                getNombre(),
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
                isEsBebe()? "Sí" : "No",
                getTipoAlimentacion().name(),
                isDomesticado()? "Sí" : "No",
                String.format("%d", getIDduenio())};
    }

    //══MÉTODOS══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    public String domesticarLobo(int id){
         String mensaje;
            setDomesticado(true);
            setIDduenio(id);
            mensaje="FELICIDADES!! A DOMESTICADO A SU NUEVO LOBO";
        return mensaje;
    }

    //══JSON══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("domesticado", domesticado);
            j.put("IDduenio", IDduenio);

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
