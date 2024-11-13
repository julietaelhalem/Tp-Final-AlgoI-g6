import java.util.ArrayList;
import java.util.List;

public class FiltroFila {
    private String columna;         
    private String valorFiltro;     
    private TablaDatos tablaDatos;  

    // Constructor que inicializa la columna, el valor de filtro y la tabla de datos
    public FiltroFila(String columna, String valorFiltro, TablaDatos tablaDatos) {
        this.columna = columna;
        this.valorFiltro = valorFiltro;
        this.tablaDatos = tablaDatos;
    }

    // Método que filtra las filas de la tabla según el valor de la columna especificada
    public List<Fila> filtrar() {
        List<Fila> filasFiltradas = new ArrayList<>();
        // Obtiene el índice de la columna a partir de su etiqueta
        int indiceColumna = tablaDatos.getEtiquetasColumnas().indexOf(columna);

        // Verifica si la columna existe en la tabla
        if (indiceColumna == -1) {
            throw new IllegalArgumentException("La columna especificada no existe.");
        }

        // Itera por cada fila de la tabla para aplicar el filtro
        for (Fila fila : tablaDatos.getFilas()) {
            CeldaDatos celda = fila.getCelda(indiceColumna);
            // Si el valor de la celda coincide con el valor del filtro, se añade la fila a la lista de resultados
            if (celda.getValor() != null && celda.getValor().toString().equals(valorFiltro)) {
                filasFiltradas.add(fila);
            }
        }
        return filasFiltradas;
    }
}

