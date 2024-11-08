import java.util.ArrayList;
import java.util.List;

public class TablaDatos {
    private List<Fila> filas;                  // Lista de filas en la tabla
    private int numeroFilas;                   // Número de filas en la tabla
    private int numeroColumnas;                // Número de columnas en la tabla
    private List<String> etiquetasColumnas;    // Etiquetas de las columnas

    public TablaDatos() {
        this.filas = new ArrayList<>();
        this.etiquetasColumnas = new ArrayList<>();
        this.numeroFilas = 0;
        this.numeroColumnas = 0;
    }

    // Obtener la lista de filas
    public List<Fila> getFilas() {
        return filas;
    }

    // Obtener la cantidad de filas
    public int getCantidadFilas() {
        return numeroFilas;
    }

    // Obtener la cantidad de columnas
    public int getCantidadColumnas() {
        return numeroColumnas;
    }

    // Obtener las etiquetas de las columnas
    public List<String> getEtiquetasColumnas() {
        return etiquetasColumnas;
    }

    // Establecer las etiquetas de las columnas
    public void setEtiquetasColumnas(List<String> etiquetas) {
        this.etiquetasColumnas = etiquetas;
        this.numeroColumnas = etiquetas.size();
    }

    // Obtener una fila específica por índice
    public Fila getFila(int index) {
        if (index < 0 || index >= filas.size()) {
            throw new IndexOutOfBoundsException("Índice de fila fuera de rango");
        }
        return filas.get(index);
    }

    // Método para insertar una nueva fila en la tabla
    public void insertarFila(List<Object> valores) {
        if (valores.size() != numeroColumnas) {
            throw new IllegalArgumentException("La cantidad de valores no coincide con el número de columnas");
        }
        
        Fila nuevaFila = new Fila();
        for (int i = 0; i < numeroColumnas; i++) {
            Object valor = valores.get(i);
            CeldaDatos celda = new CeldaDatos(valor, determinarTipoDato(valor), numeroFilas, i);
            nuevaFila.agregarCelda(celda);
        }
        filas.add(nuevaFila);
        numeroFilas++;
    }

    // Método para determinar el tipo de dato de un valor
    private String determinarTipoDato(Object valor) {
        if (valor instanceof Integer || valor instanceof Double || valor instanceof Float) {
            return "Numérico";
        } else if (valor instanceof Boolean) {
            return "Booleano";
        } else if (valor instanceof String) {
            return "Cadena";
        } else {
            return "Desconocido";
        }
    }

    // Obtener una celda específica por índice de fila y columna
    public CeldaDatos getCelda(int fila, int columna) {
        if (fila < 0 || fila >= numeroFilas || columna < 0 || columna >= numeroColumnas) {
            throw new IndexOutOfBoundsException("Índice de celda fuera de rango");
        }
        return filas.get(fila).getCelda(columna);
    }

    // Imputar valores en una columna para valores faltantes (NA)
    public void imputarValores(String columna, Object valorImputacion) {
        int columnaIndex = etiquetasColumnas.indexOf(columna);
        if (columnaIndex == -1) throw new IllegalArgumentException("Columna no encontrada");

        for (Fila fila : filas) {
            CeldaDatos celda = fila.getCelda(columnaIndex);
            if (celda.getValor() == null) {
                celda.setValor(valorImputacion);
            }
        }
    }

}
