import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorErrores {
    private List<String> listaErrores;
    private Date registroError;

    public GestorErrores() {
        listaErrores = new ArrayList<>();
    }

    public void manejarError(String mensaje) {
        listaErrores.add(mensaje);
        registroError = new Date();
    }

    public void registrarError(String mensaje) {
        listaErrores.add(mensaje);
        registroError = new Date(); // Registrar el tiempo de cada error
    }

    public List<String> getListaErrores() {
        return listaErrores;
    }

    public Date getRegistroError() {
        return registroError;
    }

    public void imprimirErrores() {
        if (listaErrores.isEmpty()) {
            System.out.println("No se registraron errores.");
        } else {
            System.out.println("Errores registrados:");
            for (String error : listaErrores) {
                System.out.println("- " + error);
            }
        }
    }
}
