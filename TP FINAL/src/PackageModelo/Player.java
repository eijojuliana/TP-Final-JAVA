package PackageModelo;

import PackageEnum.TipoPlayer;

public class Player extends Entidad{
    ///todo.ATRIBUTOS///
    protected boolean esPremium;
    protected TipoPlayer tipoPlayer;

    ///todo.CONSTRUCTORES///
    public Player(String nombre, double vida, double danio, boolean esPremium, TipoPlayer tipoPlayer) {
        super(nombre, vida, danio);
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
    public void setTipoPlayer(TipoPlayer tipoPlayer) {
        this.tipoPlayer = tipoPlayer;
    }
///todo.TO STRING///
    @Override
    public String toString() {
        return "Player{" +
                "esPremium=" + esPremium +
                ", tipoPlayer=" + tipoPlayer +
                "} " + super.toString();
    }
}
