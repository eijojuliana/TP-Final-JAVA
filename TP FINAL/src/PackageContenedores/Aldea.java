package PackageContenedores;
import PackageModelo.Aldeano;
import PackageModelo.Animal;
import PackageModelo.Entidad;

public class Aldea {
    /// todo.Atributos///
    protected AlmacenamientoNPC<Animal> ArrayAnimales;
    protected AlmacenamientoNPC<Aldeano> ArrayAldeanos;
    protected AlmacenamientoNPC<Entidad> ArrayCarcel;

    ///todo.Constructores///
    public Aldea() {
        ArrayAnimales = new AlmacenamientoNPC<>();
        ArrayAldeanos = new AlmacenamientoNPC<>();
        ArrayCarcel = new AlmacenamientoNPC<>();
    }
    /// todo.Metodos//
    @Override
    public String toString() {
        return "Aldea{" +
                "ArrayAnimales=" + ArrayAnimales +
                ", ArrayAldeanos=" + ArrayAldeanos +
                ", ArrayEntidad=" + ArrayCarcel +
                '}';
    }
}
