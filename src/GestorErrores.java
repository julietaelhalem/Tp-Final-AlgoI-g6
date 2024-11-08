import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorErrores {
    private List<String> listaErrores;
    @SuppressWarnings("unused")
    private String nivelError;
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
    }

    public List<String> getListaErrores() {
        return listaErrores;
    }

    public Date getRegistroError() {
        return registroError;
    }
}
