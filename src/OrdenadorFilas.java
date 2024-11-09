import java.util.Comparator;
import java.util.List;

public class OrdenadorFilas {
    private String columna;
    private boolean ascendente;
    private TablaDatos tablaDatos;

    public OrdenadorFilas(String columna, boolean ascendente, TablaDatos tablaDatos) {
        this.columna = columna;
        this.ascendente = ascendente;
        this.tablaDatos = tablaDatos;
    }

    public List<Fila> ordenar(List<Fila> filas) {
        Comparator<Fila> comparador = Comparator.comparing(
            fila -> {
                String valor = fila.getValor(columna, tablaDatos);
                return (valor != null) ? valor : ""; // Evitar null en la comparación
            }
        );

        if (!ascendente) {
            comparador = comparador.reversed();
        }

        filas.sort(comparador);
        return filas;
    }
}
