package PackageContenedores;
import PackageExceptions.Entidad_inexistente_Exception;
import PackageExceptions.Entidad_repetida_Exception;
import PackageInterfaces.IConversionJSON;
import PackageJSON.JSONUtiles;
import PackageModelo.Entidad;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Carcel extends AlmacenamientoNPC  {
    ///todo.Atributos///

    protected HashMap<Integer,LocalDate> condenajeje;

    /// todo.Constructores///
    public Carcel() {
        this.condenajeje = new HashMap<>();
    }

    /// todo.TO JSON///
    @Override
    public JSONArray toJSON() {
        JSONArray j;
        try {
            j = super.toJSON();

            JSONObject condenasJSON = new JSONObject();
            for (Integer key : condenajeje.keySet()) {
                condenasJSON.put(key.toString(), condenajeje.get(key).toString());
            }

            j.put(new JSONObject().put("condenas", condenasJSON));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return j;
    }


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


}
