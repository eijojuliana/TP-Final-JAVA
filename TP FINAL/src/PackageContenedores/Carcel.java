package PackageContenedores;
import PackageExceptions.Entidad_inexistente_Exception;
import PackageExceptions.Entidad_repetida_Exception;
import PackageInterfaces.IConversionJSON;
import PackageJSON.JSONUtiles;
import PackageModelo.Celda;
import PackageModelo.Entidad;
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

    //
    public void setCarcel(HashMap<Integer, Celda> carcel) {
        this.carcel = carcel;
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
                if (celda.fromJSON(jCelda)){
                    carcel.put(celda.getNumeroCelda(), celda);
                }
            }
            exito = true;

        } catch (JSONException e){
            throw new RuntimeException();
        }
        return exito;
    }

    /*
    /// todo.METODOS///
   public boolean imponerCondena(int id,LocalDate fechaCondena ) throws Entidad_inexistente_Exception
   {
       boolean condenado;
       if (id<=0){
           throw new Entidad_inexistente_Exception("La entidad ingresada no existe");
       }
       condenajeje.put(id,fechaCondena);
       condenado=true;

       return condenado;
   }

   public boolean liberarMob(int id)throws Entidad_inexistente_Exception
   {
       boolean liberado;
       if (!condenajeje.containsKey(id)){
           throw new Entidad_inexistente_Exception("El id proporcionado no corresponde a un mob encarcelado tontito");
       }
       condenajeje.remove(id);
       JSONUtiles.grabarUnJson(this.toJSON(),"condenas");
       liberado=true;

       return liberado;

   }

     */


}
