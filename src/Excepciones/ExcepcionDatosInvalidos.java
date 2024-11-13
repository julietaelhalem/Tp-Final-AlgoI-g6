package Excepciones;

public class ExcepcionDatosInvalidos extends RuntimeException {
    public ExcepcionDatosInvalidos(String mensaje) {
        super(mensaje);
    }

    public ExcepcionDatosInvalidos(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
}

