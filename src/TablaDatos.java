import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TablaDatos {
    private List<Fila> filas;
    private List<String> etiquetasColumnas;
    private List<String> tiposColumnas;
    private int numeroFilas;
    private int numeroColumnas;

    public TablaDatos() {
        this.filas = new ArrayList<>();
        this.etiquetasColumnas = new ArrayList<>();
        this.tiposColumnas = new ArrayList<>();
        this.numeroFilas = 0;
        this.numeroColumnas = 0;
    }

    // Método para agregar una nueva fila a la tabla
    public void agregarFila(Fila fila) {
        GestorErrores.verificarCantidadCeldas(fila, numeroColumnas);
        filas.add(fila);
        numeroFilas++;
    }

    // Método para agregar una nueva columna a la tabla
    public void agregarColumna(String etiqueta, String tipoDato) {
        etiquetasColumnas.add(etiqueta);
        tiposColumnas.add(tipoDato);
        numeroColumnas++;

        // Agregar una nueva celda con valor NA en cada fila para la nueva columna
        for (int i = 0; i < numeroFilas; i++) {
            filas.get(i).agregarCelda(new CeldaDatos(CeldaDatos.NA, tipoDato, i, numeroColumnas - 1));
        }
    }

    // Método para obtener una fila completa por su índice
    public Fila getFila(int index) {
        GestorErrores.verificarIndice(index, numeroFilas, "Índice de fila fuera de rango.");
        return filas.get(index);
    }

    // Método para obtener todas las filas
    public List<Fila> getFilas() {
        return filas;
    }

    // Método para obtener una columna completa por su etiqueta
    public List<CeldaDatos> getColumna(String etiqueta) {
        GestorErrores.verificarEtiquetaColumna(etiqueta, etiquetasColumnas);
        int colIndex = etiquetasColumnas.indexOf(etiqueta);

        List<CeldaDatos> columna = new ArrayList<>();
        for (Fila fila : filas) {
            columna.add(fila.getCelda(colIndex));
        }
        return columna;
    }

    // Método para obtener una celda específica por índices de fila y columna
    public CeldaDatos getCelda(int filaIndex, int columnaIndex) {
        GestorErrores.verificarIndice(filaIndex, numeroFilas, "Índice de fila fuera de rango.");
        GestorErrores.verificarIndice(columnaIndex, numeroColumnas, "Índice de columna fuera de rango.");
        return filas.get(filaIndex).getCelda(columnaIndex);
    }

    // Método para obtener una celda específica por etiquetas de fila y columna
    public CeldaDatos getCeldaPorEtiqueta(String etiquetaFila, String etiquetaColumna) {
        int filaIndex = Integer.parseInt(etiquetaFila);  // Asumimos que las etiquetas de fila son numéricas
        int columnaIndex = etiquetasColumnas.indexOf(etiquetaColumna);

        if (columnaIndex == -1) {
            throw new IllegalArgumentException("Etiqueta de columna no encontrada.");
        }

        return getCelda(filaIndex, columnaIndex);
    }

    // Métodos para obtener información básica de la tabla
    public int getNumeroFilas() {
        return numeroFilas;
    }

    public int getNumeroColumnas() {
        return numeroColumnas;
    }

    public List<String> getEtiquetasColumnas() {
        return etiquetasColumnas;
    }

    public List<String> getTiposColumnas() {
        return tiposColumnas;
    }

    // Visualización básica de la tabla en formato de texto
    public void mostrarTabla() {
        System.out.print("| ");
        for (String etiqueta : etiquetasColumnas) {
            System.out.print(etiqueta + " | ");
        }
        System.out.println();

        for (Fila fila : filas) {
            System.out.print("| ");
            for (CeldaDatos celda : fila.getCeldas()) {
                System.out.print((celda.getValor() == CeldaDatos.NA ? "NA" : celda.getValor()) + " | ");
            }
            System.out.println();
        }
    }

    // Método para imputar valores faltantes en una columna específica
    public void imputarColumna(String etiqueta, Object valorImputacion) {
        GestorValoresFaltantes.imputarColumna(etiqueta, this, valorImputacion);
    }

    // Método para imputar valores faltantes en toda la tabla
    public void imputarTabla(Object valorImputacion) {
        GestorValoresFaltantes.imputarTabla(this, valorImputacion);
    }

    // Método para filtrar filas basadas en una columna y un valor objetivo
    public List<Fila> filtrarPorColumna(String etiquetaColumna, Object valorObjetivo) {
        return FiltroFila.filtrarPorColumna(this, etiquetaColumna, valorObjetivo);
    }

    // Método para ordenar las filas de la tabla según una columna específica
    public void ordenarPorColumna(String etiquetaColumna, boolean ascendente) {
        OrdenadorFilas.ordenarPorColumna(this, etiquetaColumna, ascendente);
    }

        // Método para concatenar esta tabla con otra
    public TablaDatos concatenarCon(TablaDatos otraTabla) {
        // Verificar que ambas tablas tengan el mismo número de columnas y etiquetas coincidentes
        if (this.numeroColumnas != otraTabla.getNumeroColumnas()) {
            throw new IllegalArgumentException("Las tablas no tienen el mismo número de columnas.");
        }
        
        for (int i = 0; i < this.numeroColumnas; i++) {
            String etiqueta = this.etiquetasColumnas.get(i);
            if (!etiqueta.equals(otraTabla.getEtiquetasColumnas().get(i))) {
                throw new IllegalArgumentException("Las etiquetas de columna no coinciden entre las tablas.");
            }
            if (!this.tiposColumnas.get(i).equals(otraTabla.getTiposColumnas().get(i))) {
                throw new IllegalArgumentException("Los tipos de columna no coinciden entre las tablas.");
            }
        }

        // Crear una nueva tabla con la concatenación de ambas
        TablaDatos nuevaTabla = new TablaDatos();
        nuevaTabla.etiquetasColumnas = new ArrayList<>(this.etiquetasColumnas);
        nuevaTabla.tiposColumnas = new ArrayList<>(this.tiposColumnas);
        nuevaTabla.numeroColumnas = this.numeroColumnas;

        // Agregar las filas de esta tabla
        for (Fila fila : this.filas) {
            nuevaTabla.agregarFila(fila);
        }

        // Agregar las filas de la otra tabla
        for (Fila fila : otraTabla.getFilas()) {
            nuevaTabla.agregarFila(fila);
        }

        return nuevaTabla;
    }

    public static TablaDatos crearDesdeMatriz(Object[][] datos, List<String> etiquetasColumnas, List<String> tiposColumnas) {
        if (etiquetasColumnas.size() != datos[0].length || tiposColumnas.size() != datos[0].length) {
            throw new IllegalArgumentException("La cantidad de etiquetas y tipos debe coincidir con el número de columnas en los datos.");
        }

        TablaDatos nuevaTabla = new TablaDatos();
        nuevaTabla.etiquetasColumnas = etiquetasColumnas;
        nuevaTabla.tiposColumnas = tiposColumnas;
        nuevaTabla.numeroColumnas = etiquetasColumnas.size();

        for (Object[] filaDatos : datos) {
            Fila fila = new Fila();
            for (int i = 0; i < filaDatos.length; i++) {
                CeldaDatos celda = new CeldaDatos(filaDatos[i], tiposColumnas.get(i), nuevaTabla.getNumeroFilas(), i);
                fila.agregarCelda(celda);
            }
            nuevaTabla.agregarFila(fila);
        }

        return nuevaTabla;
    }
    public void modificarCelda(int filaIndex, int columnaIndex, Object nuevoValor) {
        GestorErrores.verificarIndice(filaIndex, numeroFilas, "Índice de fila fuera de rango.");
        GestorErrores.verificarIndice(columnaIndex, numeroColumnas, "Índice de columna fuera de rango.");
        filas.get(filaIndex).getCelda(columnaIndex).setValor(nuevoValor);
    }

    // Modifica todos los valores de una fila
    public void modificarFila(int filaIndex, List<Object> nuevosValores) {
        GestorErrores.verificarIndice(filaIndex, numeroFilas, "Índice de fila fuera de rango.");
        if (nuevosValores.size() != numeroColumnas) {
            throw new IllegalArgumentException("El número de valores no coincide con el número de columnas.");
        }

        Fila fila = filas.get(filaIndex);
        for (int i = 0; i < numeroColumnas; i++) {
            fila.getCelda(i).setValor(nuevosValores.get(i));
        }
    }

    // Modifica todos los valores de una columna
    public void modificarColumna(String etiquetaColumna, List<Object> nuevosValores) {
        int colIndex = etiquetasColumnas.indexOf(etiquetaColumna);
        if (colIndex == -1) {
            throw new IllegalArgumentException("Etiqueta de columna no encontrada.");
        }
        if (nuevosValores.size() != numeroFilas) {
            throw new IllegalArgumentException("El número de valores no coincide con el número de filas.");
        }

        for (int i = 0; i < numeroFilas; i++) {
            filas.get(i).getCelda(colIndex).setValor(nuevosValores.get(i));
        }
    }
    // Método para obtener un subconjunto aleatorio de filas basado en un porcentaje
    public List<Fila> muestreoAleatorio(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 1) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 1.");
        }

        int cantidadMuestra = (int) (numeroFilas * porcentaje);
        List<Fila> filasAleatorias = new ArrayList<>(filas);
        Collections.shuffle(filasAleatorias); // Mezcla aleatoriamente las filas
        return filasAleatorias.subList(0, cantidadMuestra);
    }
}
