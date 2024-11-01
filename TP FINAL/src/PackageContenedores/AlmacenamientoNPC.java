package PackageContenedores;

import java.util.ArrayList;
import java.util.Iterator;

public class AlmacenamientoNPC<T> {
    //todo.ATRIBUTO
    protected ArrayList<T> arrayNPC;

    //todo.CONSTRUCTOR

    public AlmacenamientoNPC() {
        this.arrayNPC = new ArrayList<>();
    }

    //todo.METODOS

    public String agregar(T NPC){
        String mensaje="";
        if(NPC != null){
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

    public String mostrarObjetos(){
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = arrayNPC.iterator();

        while(iterator.hasNext()){
            sb.append(iterator.next().toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
