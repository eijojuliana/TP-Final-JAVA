package PackageModelo;

import PackageEnum.Gen_Panda;
import PackageEnum.TipoAlimentacion;
import PackageInterfaces.IConversionJSON;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

public final class Panda extends Animal implements IConversionJSON, ITabla {
    ///todo.ATRIBUTOS
    private Gen_Panda gen;
    ///todo.CONSTRUCTOR

    public Panda(String nombre, boolean esBebe, TipoAlimentacion tipoAlimentacion, Gen_Panda gen ) {
        super(nombre, 20.0, 0, Panda.class.getSimpleName(), esBebe, tipoAlimentacion);
        inicializar_danio();
        inicializar_gen();
    }

    public Panda(){
        setVida(20);
        setDanio(0);
        setTipo(Panda.class.getSimpleName());
    }

    ///todo.GET Y SET
    public Gen_Panda getGen() {
        return gen;
    }
    public void setGen(Gen_Panda gen) {
        this.gen = gen;
    }
    public void setGen(String gen) {
        if (
                gen.equals(Gen_Panda.NORMAL.name()) ||
                        gen.equals(Gen_Panda.AGRESIVO.name()) ||
                        gen.equals(Gen_Panda.PEREZOSO.name()) ||
                        gen.equals(Gen_Panda.ASUSTADIZO.name()) ||
                        gen.equals(Gen_Panda.JUGUETON.name()) ||
                        gen.equals(Gen_Panda.DEBIL.name()) ||
                        gen.equals(Gen_Panda.MARRON.name())
        ) this.gen = Gen_Panda.valueOf(gen);
    }

    //══TO STRING══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    @Override
    public String toString() {
        return "Panda{" +
                "gen=" + gen +
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
                {"Gen Panda", gen.name()}
        });
    }

    //══MÉTODOS══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    public String Rodar(){
        return "*Giro ,me caigo, me levanto, vuelvo a girar*";
    }

    private void inicializar_danio() {
        if (getGen() == Gen_Panda.AGRESIVO) danio = 3;
        else danio = 0.0;
    }

    private void inicializar_gen () {
        int num = (int) (Math.random() * 8);//Un numero aleatorio entre 0 y 7. Esto porq en el juego el "caracter" del panda es alatorio.
        Gen_Panda[] genPandas = Gen_Panda.values();
        setGen(genPandas[num]);
    }

    //══JSON══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════

    @Override
    public JSONObject toJSON(){
        JSONObject j;

        try {
            j = super.toJSON();
            j.put("gen", gen);
            j.put("tipo","Panda");

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
            setGen(j.getString("gen"));

            exito = true;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }
}
