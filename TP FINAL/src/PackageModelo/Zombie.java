package PackageModelo;

import PackageEnum.TipoZombie;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.IFila;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public final class Zombie extends Mob implements IConversionJSON, ITabla, IFila {
    ///todo.ATRIBUTOS///
    private TipoZombie tipoZombie;

    ///todo.CONSTRUCTORES///

    public Zombie(String nombre, double vida, double danio, boolean esBebe, TipoZombie tipoZombie) {
        super(nombre, 10.0, 2.5, Zombie.class.getSimpleName(),false);
        inicializar_drops();
        this.tipoZombie = tipoZombie;
    }

    public Zombie() {
    }

    ///todo.GETS Y SETS///
    public TipoZombie getTipoZombie() {
        return tipoZombie;
    }
    public void setTipoZombie(TipoZombie tipoZombie) {
        this.tipoZombie = tipoZombie;
    }
    public void setTipoZombie(String tipoZombie) {
        if (
                tipoZombie.equalsIgnoreCase(TipoZombie.COMUN.name()) ||
                tipoZombie.equalsIgnoreCase(TipoZombie.MOMIFICADO.name()) ||
                tipoZombie.equalsIgnoreCase(TipoZombie.CHIQUITO.name()) ||
                tipoZombie.equalsIgnoreCase(TipoZombie.ALDEANO.name()) ||
                tipoZombie.equalsIgnoreCase(TipoZombie.AHOGADO.name())
        ) this.tipoZombie = TipoZombie.valueOf(tipoZombie);
    }

    ///todo.TO STRING///
    @Override
    public String toString() {
        return "Zombie{" +
                "tipoZombie=" + tipoZombie +
                "} " + super.toString();
    }

    @Override
    public String aTabla() {
        // Crear la tabla usando la biblioteca AsciiTable
        return AsciiTable.getTable(new String[][] {
                {"Mob", getTipo() },
                {"ID", String.format("%d" ,getId()) },
                {"Nombre", getNombre()},
                {"Vida", String.format("%.2f", getVida())},
                {"Daño", String.format("%.2f", getDanio())},
                {"¿Es bebé?", esBebe ? "Sí" : "No"},
                {"Tipo Zombie", tipoZombie.name()}
        });
    }

    @Override
    public String[] aFila(){
        return new String[]{
                String.format("%d" ,getId()),
                getNombre(),
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
                isEsBebe()? "Sí" : "No"};
    }

    private void inicializar_drops() {
        drops.add("Carne podrida");
        setDrops(drops);
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("esBebe", esBebe);
            j.put("tipoZombie", tipoZombie);
            j.put("tipo","Zombie");

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
            setEsBebe(j.getBoolean("esBebe"));
            setTipoZombie(j.getString("tipoZombie"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
