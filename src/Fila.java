import java.util.ArrayList;
import java.util.List;

public class Fila {
    private List<CeldaDatos> celdas; // Lista que almacena las celdas (CeldaDatos) de la fila

    // Constructor que inicializa la lista de celdas
    public Fila() {
        celdas = new ArrayList<>();
    }

    // Agrega una celda a la fila
    public void agregarCelda(CeldaDatos celda) {
        celdas.add(celda);
    }

    // Obtiene una celda de la fila en un índice específico
    public CeldaDatos getCelda(int index) {
        return celdas.get(index);
    }

    // Elimina una celda de la fila en un índice específico
    public void eliminarCelda(int index) {
        celdas.remove(index);
    }

    // Devuelve la lista completa de celdas de la fila
    public List<CeldaDatos> getCeldas() {
        return celdas;
    }

    // Crea y devuelve una copia de la fila, con copias individuales de cada celda
    public Fila copiar() {
        Fila copia = new Fila();
        for (CeldaDatos celda : celdas) {
            copia.agregarCelda(new CeldaDatos(celda.getValor(), celda.getTipoDato(), celda.getPosicionFila(), celda.getPosicionColumna()));
        }
        return copia;
    }

    // Obtiene el valor de una celda a partir de la etiqueta de la columna
    public String getValor(String columna, TablaDatos tablaDatos) {
        // Encuentra el índice de la columna a partir de su etiqueta en la tabla
        int columnaIndex = tablaDatos.getEtiquetasColumnas().indexOf(columna);
        if (columnaIndex == -1) {
            throw new IllegalArgumentException("La columna especificada no existe.");
        }
        // Obtiene la celda en el índice correspondiente y devuelve su valor como cadena
        CeldaDatos celda = celdas.get(columnaIndex);
        return celda.getValor() != null ? celda.getValor().toString() : null;
    }
}
