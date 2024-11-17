package PackageContenedores;
import PackageModelo.Aldeano;
import PackageModelo.Animal;
import PackageModelo.Entidad;
import PackageModelo.Mob;
import java.util.HashMap;

public class Aldea {
    /// todo.Atributos///
    protected AlmacenamientoNPC<Animal> ArrayAnimales;
    protected AlmacenamientoNPC<Aldeano> ArrayAldeanos;
    protected AlmacenamientoNPC<Entidad> ArrayEntidad;

    ///todo.Constructores///
    public Aldea() {
        ArrayAnimales = new AlmacenamientoNPC<>();
        ArrayAldeanos = new AlmacenamientoNPC<>();
        ArrayEntidad = new AlmacenamientoNPC<>();
    }
    /// todo.Metodos//
    @Override
    public String toString() {
        return "Aldea{" +
                "ArrayAnimales=" + ArrayAnimales +
                ", ArrayAldeanos=" + ArrayAldeanos +
                ", ArrayEntidad=" + ArrayEntidad +
                '}';
    }
}
