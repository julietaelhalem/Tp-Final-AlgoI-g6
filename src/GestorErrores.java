
import java.util.List;

public class GestorErrores {

    // Verifica si un índice está dentro de los límites permitidos para filas o columnas
    public static void verificarIndice(int index, int limite, String mensajeError) {
        if (index < 0 || index >= limite) {
            throw new IndexOutOfBoundsException(mensajeError);
        }
    }

    // Verifica si una etiqueta de columna existe en la tabla
    public static void verificarEtiquetaColumna(String etiqueta, List<String> etiquetasColumnas) {
        if (!etiquetasColumnas.contains(etiqueta)) {
            throw new IllegalArgumentException("Etiqueta de columna '" + etiqueta + "' no encontrada.");
        }
    }

    // Verifica si una fila tiene la cantidad correcta de celdas al agregarla a la tabla
    public static void verificarCantidadCeldas(Fila fila, int numeroColumnas) {
        if (fila.getCeldas().size() != numeroColumnas) {
            throw new IllegalArgumentException("La cantidad de celdas en la fila no coincide con el número de columnas.");
        }
    }
}
