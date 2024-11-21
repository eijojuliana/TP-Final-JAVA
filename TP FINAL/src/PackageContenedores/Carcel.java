package PackageContenedores;
import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Entidad_inexistente_Exception;
import PackageExceptions.Entidad_repetida_Exception;
import PackageExceptions.Valor_de_atributo_no_valido_Exception;
import PackageInterfaces.IConversionJSON;
import PackageJSON.JSONUtiles;
import PackageModelo.Celda;
import PackageModelo.Entidad;
import PackageModelo.Player;
import com.github.freva.asciitable.AsciiTable;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Carcel implements IConversionJSON{
    ///todo.Atributos///

    protected HashMap<Integer, Celda> carcel;

    /// todo.Constructores///
    public Carcel() {
        this.carcel = new HashMap<>();
    }


    /// todo.TO JSON///
    @Override
    public JSONObject toJSON() {
        JSONObject carcelJSON = new JSONObject();
        JSONArray celdasJSON = new JSONArray();

        try {
            for (Integer key : carcel.keySet()) {
                Celda celda = carcel.get(key);
                if (celda != null){
                    celdasJSON.put(celda.toJSON());
                }
            }
            carcelJSON.put("celdas", celdasJSON);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return carcelJSON;
    }

    @Override
    public boolean fromJSON(JSONObject j) {
        boolean exito = false;
        try{
            JSONArray jsonArray = j.getJSONArray("celdas");
            for(int i=0;i< jsonArray.length();i++){
                JSONObject jCelda = jsonArray.getJSONObject(i);
                Celda celda = new Celda();
                celda.fromJSON(jCelda);
                carcel.put(celda.getNumeroCelda(), celda);
            }
            exito = true;

        } catch (JSONException e){
            throw new RuntimeException();
        }
        return exito;
    }


    /// todo.METODOS///
    public boolean encarcelar(Entidad mob,LocalDate fechaEntrada,LocalDate fechaSalida,int id) throws Entidad_inexistente_Exception,Entidad_repetida_Exception
    {
        boolean encarcelado=false;
        try{
            if (mob!=null){
                if (!(carcel.containsKey(id))){
                    Celda nuevaCelda=new Celda(mob,fechaEntrada,fechaSalida);
                    carcel.put(nuevaCelda.getNumeroCelda(),nuevaCelda);
                    encarcelado=true;
                }
            }
        } catch (Entidad_inexistente_Exception e){
            System.out.println(e.getMessage());
        }
        return encarcelado;
    }

    public boolean liberarMob(int numeroCelda) throws Valor_de_atributo_no_valido_Exception {
        boolean liberado = false;
        try {
            if (carcel.containsKey(numeroCelda)) {
                Celda celda = carcel.get(numeroCelda);
                LocalDate fechaSalida = celda.getFechaSalida();
                LocalDate fechaActual = LocalDate.now();

                // Verificar si la fecha de salida ya pasó o es igual a la fecha actual
                if (!fechaActual.isBefore(fechaSalida)) {
                    carcel.remove(numeroCelda); // Liberar la celda
                    liberado = true;
                } else {
                    // Si la fecha actual es antes de la fecha de salida
                    throw new Valor_de_atributo_no_valido_Exception("El mob aún no puede ser liberado. La fecha de salida no ha llegado.");
                }
            } else {
                throw new Valor_de_atributo_no_valido_Exception("Número de celda no válido o inexistente.");
            }
        } catch (Valor_de_atributo_no_valido_Exception e) {
            System.out.println(e.getMessage());
        }
        return liberado;
    }

    public Celda obtenerInfoCelda(int numeroCelda){
        if ( carcel.get(numeroCelda) == null ) throw new Entidad_inexistente_Exception("No se encuentra la celda o está vacia");
        return carcel.get(numeroCelda);
    }

    public int contarCeldasDesocupadas() {
        int maxNumeroCelda = Celda.getIdIncremental(); // Obtiene el último ID generado.
        int celdasDesocupadas = 0;

        for (int i = 1; i <= maxNumeroCelda; i++) {
            if (!carcel.containsKey(i)) {
                celdasDesocupadas++;
            }
        }
        return celdasDesocupadas;
    }

    public ArrayList<Integer> obtenerCeldasDesocupadas() {
        ArrayList<Integer> celdasDesocupadas = new ArrayList<>();
        int maxNumeroCelda = Celda.getIdIncremental(); // Obtiene el último ID generado.

        for (int i = 1; i <= maxNumeroCelda; i++) {
            if (!carcel.containsKey(i)) {
                celdasDesocupadas.add(i);
            }
        }
        return celdasDesocupadas;
    }

    public String carcelToTable() throws Atributo_vacio_Exception {
        if(carcel.isEmpty()) throw new Atributo_vacio_Exception("La carcel está vacía.");

        int i = 0;
        String[][] data = new String[carcel.size() + 1][5];
        data[0] = new String[]{"ID Celda", "Nombre", "ID", "Fecha Entrada", "Fecha Salida"};

        for (Celda c : carcel.values()){
            data[i+1] = c.aFila();
            i++;
        }

        return Aldea.tabla_modificada(AsciiTable.getTable(data));
    }
}
