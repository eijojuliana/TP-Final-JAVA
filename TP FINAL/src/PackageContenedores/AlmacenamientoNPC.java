package PackageContenedores;

import PackageInterfaces.IConversionJSON;
import PackageModelo.Creeper;
import PackageModelo.Entidad;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class AlmacenamientoNPC<T extends IConversionJSON> {
    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.ATRIBUTO
    protected ArrayList<T> arrayNPC;

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.CONSTRUCTOR

    public AlmacenamientoNPC() {
        this.arrayNPC = new ArrayList<>();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo.METODOS

    public String agregar(T NPC){ // Boleean
        String mensaje="";
        if(NPC != null){ // Tira una exception
            arrayNPC.add(NPC);
            mensaje="Se agrego el npc correctamente";
        }else{
            mensaje="No ha sido posible su carga.";
        }
        return mensaje;
    }


    public String eliminar(T NPC){
        String mensaje="";
        if(NPC == null){
            mensaje = "No se puede eliminar porq es null a ver si te das cuenta tontito";
        }else{
            arrayNPC.remove(NPC);
            mensaje = "Se elimino el objeto anashi";
        }
        return mensaje;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = arrayNPC.iterator();

        while(iterator.hasNext()){
            sb.append(iterator.next().toString() + ",\n");
        }
        return sb.toString();
    }

    //════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════
    //todo. JSON

    public JSONArray toJSON(){
        JSONArray j = new JSONArray();

        Iterator<T> iterator = arrayNPC.iterator();
        while (iterator.hasNext()){
            j.put( iterator.next().toJSON() );
        }
        return j;
    }


}

