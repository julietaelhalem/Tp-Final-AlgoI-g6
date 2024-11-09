import java.util.ArrayList;
import java.util.List;

public class FiltroFila {
    private String columna;
    private String valorFiltro;
    private TablaDatos tablaDatos;

    public FiltroFila(String columna, String valorFiltro, TablaDatos tablaDatos) {
        this.columna = columna;
        this.valorFiltro = valorFiltro;
        this.tablaDatos = tablaDatos;
    }

    // Método para filtrar filas basado en el valor de una columna específica
    public List<Fila> filtrar() {
        List<Fila> filasFiltradas = new ArrayList<>();
        int indiceColumna = tablaDatos.getEtiquetasColumnas().indexOf(columna);

        if (indiceColumna == -1) {
            throw new IllegalArgumentException("La columna especificada no existe.");
        }

        for (Fila fila : tablaDatos.getFilas()) {
            CeldaDatos celda = fila.getCelda(indiceColumna);
            if (celda.getValor() != null && celda.getValor().toString().equals(valorFiltro)) {
                filasFiltradas.add(fila);
            }
        }
        return filasFiltradas;
    }
}
