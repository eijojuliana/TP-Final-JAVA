package PackageContenedores;
import PackageModelo.Aldeano;
import PackageModelo.Animal;
import PackageModelo.Entidad;

public class Aldea {
    /// todo.Atributos///
    protected AlmacenamientoNPC<Animal> ArrayAnimales;
    protected AlmacenamientoNPC<Aldeano> ArrayAldeanos;
    protected Carcel carcel;


    ///todo.Constructores///
    public Aldea() {
        ArrayAnimales = new AlmacenamientoNPC<>();
        ArrayAldeanos = new AlmacenamientoNPC<>();
       carcel=new Carcel();
    }
    /// todo.Metodos//
    @Override
    public String toString() {
        return "Aldea{" +
                "ArrayAnimales=" + ArrayAnimales +
                ", ArrayAldeanos=" + ArrayAldeanos +
                '}';
    }
}
