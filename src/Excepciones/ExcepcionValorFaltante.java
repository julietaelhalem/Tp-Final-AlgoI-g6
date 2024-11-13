package Excepciones;

public class ExcepcionValorFaltante extends Exception {
    public ExcepcionValorFaltante(String mensaje) {
        super(mensaje);
    }

    public ExcepcionValorFaltante(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

