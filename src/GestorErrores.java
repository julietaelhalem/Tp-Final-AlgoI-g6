import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorErrores {
    
    private List<String> listaErrores;   // Lista que almacena los mensajes de error registrados
    private Date registroError;          // Fecha y hora del último error registrado

    // Constructor que inicializa la lista de errores
    public GestorErrores() {
        listaErrores = new ArrayList<>();
    }

    // Maneja un error añadiéndolo a la lista y actualizando la fecha de registro
    public void manejarError(String mensaje) {
        listaErrores.add(mensaje);
        registroError = new Date();
    }

    // Registra un mensaje de error y actualiza la fecha de registro
    public void registrarError(String mensaje) {
        listaErrores.add(mensaje);
        registroError = new Date(); // Registrar el tiempo de cada error
    }

    // Devuelve la lista de errores registrados
    public List<String> getListaErrores() {
        return listaErrores;
    }

    // Devuelve la fecha y hora del último error registrado
    public Date getRegistroError() {
        return registroError;
    }

    // Imprime todos los errores registrados o indica que no hay errores si la lista está vacía
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

