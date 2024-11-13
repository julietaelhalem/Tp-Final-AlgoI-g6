package Excepciones;

public class ExcepcionLecturaArchivo extends RuntimeException {
    public ExcepcionLecturaArchivo(String mensaje) {
        super(mensaje);
    }

    public ExcepcionLecturaArchivo(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
