import java.util.ArrayList;
import java.util.List;

public class FiltroFila {
    private int filaObjetivo; // Índice de la fila que se quiere analizar (opcional)
    private Object criterioFiltro; // Valor que debe cumplir la fila
    private TablaDatos tablaDatos;

    public FiltroFila(int filaObjetivo, Object criterioFiltro, TablaDatos tablaDatos) {
        this.filaObjetivo = filaObjetivo;
        this.criterioFiltro = criterioFiltro;
        this.tablaDatos = tablaDatos;
    }

    public void aplicar() {
        // Validar si el índice de fila es válido
        if (filaObjetivo < 0 || filaObjetivo >= tablaDatos.getNumeroFilas()) {
            System.out.println("El índice de la fila especificada está fuera de rango.");
            return;
        }

        // Obtener la lista de filas original
        List filas = tablaDatos.getFilas();
        List filasFiltradas = new ArrayList<>();

        // Filtrar filas en función del criterio especificado
        for (Fila fila : filas) {
            for (CeldaDatos celda : fila.getCeldas()) {
                Object valor = celda.getValor();

                // Si el valor de alguna celda en la fila cumple el criterio, añadir la fila a la lista filtrada
                if (valor.equals(criterioFiltro)) {
                    filasFiltradas.add(fila);
                    break; // Salimos del bucle de celdas si se cumple el criterio para esta fila
                }
            }
        }

        // Actualizar la lista de filas en TablaDatos con las filas filtradas
        tablaDatos.setFilas(filasFiltradas);
        tablaDatos.setNumeroFilas(filasFiltradas.size());
    }
}