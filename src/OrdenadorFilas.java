import java.util.Comparator;
import java.util.List;

public class OrdenadorFilas {
    
    private String columna;          // Nombre de la columna por la que se ordenarán las filas
    private boolean ascendente;      // Indica si el orden debe ser ascendente o descendente
    private TablaDatos tablaDatos;   // Referencia a la tabla de datos para obtener la lista de filas y sus valores

    // Constructor que inicializa la columna, el tipo de orden (ascendente o descendente) y la tabla de datos
    public OrdenadorFilas(String columna, boolean ascendente, TablaDatos tablaDatos) {
        this.columna = columna;
        this.ascendente = ascendente;
        this.tablaDatos = tablaDatos;
    }

    // Ordena la lista de filas basada en el valor de una columna específica
    public List<Fila> ordenar(List<Fila> filas) {
        // Comparador para ordenar filas según el valor de la columna especificada
        Comparator<Fila> comparador = Comparator.comparing(
            fila -> {
                String valor = fila.getValor(columna, tablaDatos); // Obtiene el valor de la columna en la fila
                return (valor != null) ? valor : ""; // Evita valores null en la comparación, usando "" en su lugar
            }
        );

        // Invierte el comparador si el orden es descendente
        if (!ascendente) {
            comparador = comparador.reversed();
        }

        filas.sort(comparador); // Ordena la lista de filas usando el comparador definido
        return filas;
    }
}
