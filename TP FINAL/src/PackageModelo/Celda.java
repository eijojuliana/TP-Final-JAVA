package PackageModelo;

import PackageInterfaces.IConversionJSON;
import PackageInterfaces.IFila;
import PackageInterfaces.ITabla;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Celda implements IConversionJSON, IFila, ITabla {

    //Atributos
    private static int idIncremental = 0;
    private int numeroCelda;
    private Entidad mob;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;

    //Constructor
    public Celda(Entidad mob, LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.mob = mob;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        numeroCelda = ++idIncremental;
    }
    public Celda() {
        numeroCelda=++idIncremental;
    }

    //Get y set
    public Entidad getMob() {
        return mob;
    }
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }
    public int getNumeroCelda() {
        return numeroCelda;
    }
    public static int getIdIncremental() {
        return idIncremental;
    }


    public void setMob(Entidad mob) {
        this.mob = mob;
    }
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public void setNumeroCelda(int numeroCelda) {
        this.numeroCelda = numeroCelda;
    }

    //Metodos
    @Override
    public String aTabla() {
        return AsciiTable.getTable(new String[][] {
                {"Numero de celda", String.format("%d", getNumeroCelda()) },
                {"Nombre", mob.getNombre() },
                {"ID", String.format("%d", mob.getId()) },
                {"Fecha de ingreso", String.format("%1$td/%1$tm/%1$tY", getFechaEntrada()) },
                {"Fecha de salida", String.format("%1$td/%1$tm/%1$tY", getFechaSalida()) },
        });
    }

    @Override
    public String[] aFila() {
        return new String[]{
                String.format("%d", getNumeroCelda()),
                mob.getNombre(),
                String.format("%d", mob.getId()),
                String.format("%1$td/%1$tm/%1$tY", getFechaEntrada()),
                String.format("%1$td/%1$tm/%1$tY", getFechaSalida()),
        };
    }

    //Metodos para poder guardar las fechas en JSON
    public static String localDateToString(LocalDate fecha) {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static LocalDate stringToLocalDate(String fecha) {
        try{
            return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e){
            System.out.println("Error al parsear la fecha: " + e.getMessage());
            return null;
        }
    }

    //JSON
    @Override
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();

        try {
            j.put("numeroCelda",numeroCelda);
            j.put("mob",mob.toJSON());
            j.put("fechaIngreso",localDateToString( getFechaEntrada() ));
            j.put("fechaSalida",localDateToString( getFechaSalida() ));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return j;
    }

    @Override
    public boolean fromJSON(JSONObject j) {
        boolean exito = false;
        try{
            setNumeroCelda(j.getInt("numeroCelda"));
            Entidad mob = new Entidad();
            mob.fromJSON(j.getJSONObject("mob"));
            setMob(mob);
            setFechaEntrada(stringToLocalDate(j.getString("fechaIngreso")));
            setFechaSalida(stringToLocalDate(j.getString("fechaSalida")));
        } catch (JSONException e){
            throw new RuntimeException(e);
        }

        return exito;
    }

}
