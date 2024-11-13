import java.util.Comparator;
import java.util.List;

public class OrdenadorFilas {
    
    private String columna;          
    private boolean ascendente;     
    private TablaDatos tablaDatos;   

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
                String valor = fila.getValor(columna, tablaDatos); 
                return (valor != null) ? valor : ""; 
            }
        );

        // Invierte el comparador si el orden es descendente
        if (!ascendente) {
            comparador = comparador.reversed();
        }

        filas.sort(comparador); 
        return filas;
    }
}
