package PackageExceptions;

public class Usuario_no_encontrado_Exception extends RuntimeException {
    public Usuario_no_encontrado_Exception(String message) {
        super(message);
    }
}
