import java.util.ArrayList;
import java.util.List;

public class Fila {
    private List<CeldaDatos> celdas;

    public Fila() {
        celdas = new ArrayList<>();
    }

    public void agregarCelda(CeldaDatos celda) {
        celdas.add(celda);
    }

    public CeldaDatos getCelda(int index) {
        return celdas.get(index);
    }

    public void eliminarCelda(int index) {
        celdas.remove(index);
    }

    public List<CeldaDatos> getCeldas() {
        return celdas;
    }

    public Fila copiar() {
        Fila copia = new Fila();
        for (CeldaDatos celda : celdas) {
            copia.agregarCelda(new CeldaDatos(celda.getValor(), celda.getTipoDato(), celda.getPosicionFila(), celda.getPosicionColumna()));
        }
        return copia;
    }

    // Método para obtener el valor de la celda dada una etiqueta de columna
    public String getValor(String columna, TablaDatos tablaDatos) {
        int columnaIndex = tablaDatos.getEtiquetasColumnas().indexOf(columna);
        if (columnaIndex == -1) {
            throw new IllegalArgumentException("La columna especificada no existe.");
        }
        CeldaDatos celda = celdas.get(columnaIndex);
        return celda.getValor() != null ? celda.getValor().toString() : null;
    }
}
