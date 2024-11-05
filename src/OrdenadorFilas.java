import java.util.Comparator;

public class OrdenadorFilas {

    // Ordena la tabla en base a una columna en orden ascendente
    public static void ordenarPorColumna(TablaDatos tabla, String etiquetaColumna, boolean ascendente) {
        int colIndex = tabla.getEtiquetasColumnas().indexOf(etiquetaColumna);
        if (colIndex == -1) {
            throw new IllegalArgumentException("Etiqueta de columna no encontrada.");
        }

        Comparator<Fila> comparador = Comparator.comparing(fila -> (Comparable) fila.getCelda(colIndex).getValor());

        if (!ascendente) {
            comparador = comparador.reversed();
        }
        
        tabla.getFilas().sort(comparador);
    }
}
