import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Crear instancia de TablaDatos y definir etiquetas de columnas
        TablaDatos tablaDatos = new TablaDatos();
        List<String> etiquetasColumnas = List.of("ID", "Nombre del Producto", "Estado");
        tablaDatos.setEtiquetasColumnas(etiquetasColumnas);

        // Agregar filas iniciales de productos
        tablaDatos.insertarFila(List.of("1", "Libro de Java", "En Stock"));
        tablaDatos.insertarFila(List.of("2", "Manual de Python", "Vendido"));
        tablaDatos.insertarFila(List.of("3", "Guía de SQL", "En Stock"));
        tablaDatos.insertarFila(List.of("4", "Diccionario de Inglés", "Agotado"));
        tablaDatos.insertarFila(List.of("5", "Diccionario de Frances", "Vendido"));

        // Mostrar la tabla de productos en consola
        System.out.println("Tabla de productos inicial:");
        mostrarTabla(tablaDatos);

        // Modificar el estado de un producto (usando setValor en Fila)
        System.out.println("\nModificando el estado del producto con ID 2...");
        tablaDatos.getFila(1).getCelda(2).setValor("En Stock");
        mostrarTabla(tablaDatos);

        // Eliminar un producto (por ejemplo, el producto con ID 4)
        System.out.println("\nEliminando el producto con ID 4...");
        tablaDatos.getFilas().remove(3);
        mostrarTabla(tablaDatos);

        // Filtrar productos que están "En Stock" (usando FiltroFila)
        System.out.println("\nFiltrando productos con estado 'En Stock'...");
        FiltroFila filtro = new FiltroFila("Estado", "En Stock", tablaDatos);
        List<Fila> filasFiltradas = filtro.filtrar();
        mostrarFilas(filasFiltradas);


        // Ordenar productos por "Nombre del Producto" (usando OrdenadorFilas)
        System.out.println("\nOrdenando productos por 'Nombre del Producto'...");
       // Creación del objeto OrdenadorFilas con los tres parámetros necesarios
        OrdenadorFilas ordenador = new OrdenadorFilas("Nombre del Producto", true, tablaDatos);

        // Uso del objeto ordenador para ordenar las filas
        List<Fila> filasOrdenadas = ordenador.ordenar(tablaDatos.getFilas());
        mostrarFilas(filasOrdenadas);


        // Exportar la tabla a un archivo CSV (usando BibliotecaCSV)
        String nombreArchivo = "inventario.csv";
        String rutaArchivo = "Escritorio/TP FINAL/pruebas/";

        BibliotecaCSV bibliotecaCSV = new BibliotecaCSV(nombreArchivo, rutaArchivo);
        bibliotecaCSV.setTablaDatos(tablaDatos);

        try {
            bibliotecaCSV.exportarCSV();
            System.out.println("\nArchivo CSV exportado exitosamente a " + rutaArchivo + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al exportar el archivo CSV: " + e.getMessage());
        }
    }

    // Método auxiliar para mostrar la tabla en consola
    private static void mostrarTabla(TablaDatos tabla) {
        System.out.println(String.join(" | ", tabla.getEtiquetasColumnas()));
        System.out.println("-".repeat(50));
        mostrarFilas(tabla.getFilas());
    }

    // Método auxiliar para mostrar una lista de filas en consola
    private static void mostrarFilas(List<Fila> filas) {
        for (Fila fila : filas) {
            List<String> valores = new ArrayList<>();
            for (int i = 0; i < fila.getCeldas().size(); i++) {
                valores.add(String.valueOf(fila.getCelda(i).getValor()));
            }
            System.out.println(String.join(" | ", valores));
        }
    }
}

