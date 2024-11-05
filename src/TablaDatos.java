import java.util.ArrayList;
import java.util.List;

public class TablaDatos {
    private List<List<CeldaDatos>> filas; // Almacena filas, cada fila es una lista de celdas
    private int numeroFilas; // Número de filas
    private int numeroColumnas; // Número de columnas

    public TablaDatos() {
        filas = new ArrayList<>();
        numeroFilas = 0;
        numeroColumnas = 0;
    }

    // Método para agregar una nueva fila a la tabla
    public void agregarFila(List<CeldaDatos> nuevaFila) {
        filas.add(nuevaFila);
        numeroFilas++;
        numeroColumnas = Math.max(numeroColumnas, nuevaFila.size()); // Actualiza el número máximo de columnas
    }

    // Método para obtener una fila por su índice
    public List<CeldaDatos> getFila(int index) {
        if (index < 0 || index >= numeroFilas) {
            throw new IndexOutOfBoundsException("Índice de fila fuera de rango");
        }
        return filas.get(index);
    }

    // Método para obtener una celda específica
    public CeldaDatos getCelda(int filaIndex, int columnaIndex) {
        if (filaIndex < 0 || filaIndex >= numeroFilas || columnaIndex < 0 || columnaIndex >= numeroColumnas) {
            throw new IndexOutOfBoundsException("Índice de celda fuera de rango");
        }
        return filas.get(filaIndex).get(columnaIndex);
    }

    // Método para obtener el número de filas
    public int getNumeroFilas() {
        return numeroFilas;
    }

    // Método para obtener el número de columnas
    public int getNumeroColumnas() {
        return numeroColumnas;
    }

    // Método para imprimir la tabla de datos en un formato legible
    public void imprimirTabla() {
        for (List<CeldaDatos> fila : filas) {
            for (CeldaDatos celda : fila) {
                System.out.print(celda.getValor() + " "); // Asumiendo que CeldaDatos tiene un método getValor()
            }
            System.out.println();
        }
    }

    // Método para limpiar la tabla de datos
    public void limpiarTabla() {
        filas.clear();
        numeroFilas = 0;
        numeroColumnas = 0;
    }

    // Método para verificar si la tabla está vacía
    public boolean estaVacia() {
        return numeroFilas == 0;
    }
}
