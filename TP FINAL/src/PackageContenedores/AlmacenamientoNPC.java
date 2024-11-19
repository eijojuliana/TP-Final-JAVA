package PackageContenedores;

import PackageExceptions.Atributo_vacio_Exception;
import PackageExceptions.Entidad_inexistente_Exception;
import PackageInterfaces.IConversionJSON;
import PackageModelo.Entidad;
import org.json.JSONArray;


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

    public T buscarXid (int id) {
        Entidad e;
        for (T t : arrayNPC) {
            e = (Entidad) t;
            if (e.getId() == id) {
                return (T) e;
            }
        }
        return null;
    }

    public boolean agregar(T NPC){ // Boolean
        boolean exito = false;

        if (NPC == null) throw new Atributo_vacio_Exception("El elemento vacio.");

        exito = arrayNPC.add(NPC);

        return exito;
    }


    public boolean eliminar(int id){
        boolean exito = false;

        if (buscarXid(id) == null) throw new Entidad_inexistente_Exception("El elemento no ha sido encontrado.");

        exito = arrayNPC.remove(buscarXid(id));
                
        return exito;
    }

    public int size () {return arrayNPC.size();}

    public boolean isEmpty () {return arrayNPC.isEmpty();}

    public T get (int index) {return arrayNPC.get(index);}

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

