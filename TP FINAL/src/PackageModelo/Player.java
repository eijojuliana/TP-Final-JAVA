package PackageModelo;

import PackageEnum.TipoPlayer;
import PackageEnum.TipoZombie;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.IFila;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

public class Player extends Entidad implements IConversionJSON, ITabla, IFila {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    ///todo.ATRIBUTOS///
    protected boolean premium;
    protected TipoPlayer tipoPlayer;
    protected String contrasenia;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    ///todo.CONSTRUCTORES///
    public Player(String nombre, double vida, double danio, boolean esPremium, TipoPlayer tipoPlayer, String contrasenia) {
        super(nombre, vida, danio, Player.class.getSimpleName());
        this.premium = esPremium;
        this.tipoPlayer = tipoPlayer;
        this.contrasenia = contrasenia;
        this.tipo = Player.class.getSimpleName();
    }
    public Player() {
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    ///todo.GETS Y SETS///
    public boolean isPremium() {
        return premium;
    }
    public TipoPlayer getTipoPlayer() {
        return tipoPlayer;
    }
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public void setEsPremium(boolean esPremium) {
        this.premium = esPremium;
    }
    public boolean setTipoPlayer(String tipoPlayer) {
        if (
            tipoPlayer.equalsIgnoreCase(TipoPlayer.SURVIVAL.name()) ||
            tipoPlayer.equalsIgnoreCase(TipoPlayer.CREATIVO.name()) ||
            tipoPlayer.equalsIgnoreCase(TipoPlayer.ESPECTADOR.name()) ||
            tipoPlayer.equalsIgnoreCase(TipoPlayer.OP.name()) )
        {
            this.tipoPlayer = TipoPlayer.valueOf(tipoPlayer.toUpperCase());
            return true;
        }
        else return false;
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    //todo TO STRING
    @Override
    public String toString() {
        return "Player{" +
                super.toString() +
                ", esPremium=" + premium +
                ", tipoPlayer=" + tipoPlayer +
                "} ";
    }

    @Override
    public String aTabla() {
        return AsciiTable.getTable(new String[][] {
                {"ID", String.format("%d", getId()) },
                {"Cuenta Premium", isPremium()? "Sí": "No" },
                {"Gamemode", getTipo() },
                {"Usuario", getNombre() },
                {"Contrasenia", String.format("%d", getContrasenia())}
        });
    }

    @Override
    public String[] aFila() {
        return new String[]{
                String.format("%d", getId()),
                getNombre(),
                isPremium()? "Sí": "No",
                String.format("%.2f", getVida()),
                String.format("%.2f", getDanio()),
        };
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("esPremium", premium);
            j.put("tipoPlayer", tipoPlayer);
            j.put("contrasenia", contrasenia);
            j.put("tipo","Player");
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
            setEsPremium(j.getBoolean("esPremium"));
            setTipoPlayer(j.getString("tipoPlayer"));
            setContrasenia(j.getString("contrasenia"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
