package PackageInterfaces;

import PackageModelo.Entidad;
import org.json.JSONObject;


public interface IConversionJSON<T> {

    JSONObject toJSON();

    boolean fromJSON(JSONObject j);
}
