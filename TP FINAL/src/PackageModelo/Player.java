package PackageModelo;

import PackageEnum.TipoPlayer;
import PackageEnum.TipoZombie;
import org.json.JSONException;
import org.json.JSONObject;

public class Player extends Entidad{
    ///todo.ATRIBUTOS///
    protected boolean esPremium;
    protected TipoPlayer tipoPlayer;

    ///todo.CONSTRUCTORES///
    public Player(String nombre, double vida, double danio, boolean esPremium, TipoPlayer tipoPlayer) {
        super(nombre, vida, danio, Player.class.getSimpleName());
        this.esPremium = esPremium;
        this.tipoPlayer = tipoPlayer;
    }
    public Player() {
    }

    ///todo.GETS Y SETS///
    public boolean isEsPremium() {
        return esPremium;
    }
    public void setEsPremium(boolean esPremium) {
        this.esPremium = esPremium;
    }
    public TipoPlayer getTipoPlayer() {
        return tipoPlayer;
    }
    public void setTipoPlayer(String tipoPlayer) {
        if (
                tipoPlayer.equals(TipoPlayer.SURVIVAL.name()) ||
                        tipoPlayer.equals(TipoPlayer.CREATIVO.name()) ||
                        tipoPlayer.equals(TipoPlayer.ESPECTADOR.name())
        ) this.tipoPlayer = TipoPlayer.valueOf(tipoPlayer);
    }

    ///todo.TO STRING///
    @Override
    public String toString() {
        return "Player{" +
                "esPremium=" + esPremium +
                ", tipoPlayer=" + tipoPlayer +
                "} " + super.toString();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    // todo JSON
    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("esPremium", esPremium);
            j.put("tipoPlayer", tipoPlayer);

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

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
