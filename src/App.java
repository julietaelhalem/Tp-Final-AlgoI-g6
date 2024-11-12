import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Crear instancia de TablaDatos y definir etiquetas de columnas
        TablaDatos tablaDatos = new TablaDatos();
        List<String> etiquetasColumnas = Arrays.asList("ID", "Nombre del Producto", "Estado");
        tablaDatos.setEtiquetasColumnas(etiquetasColumnas);

        // Agregar filas iniciales de productos
        tablaDatos.insertarFila(Arrays.asList("1", "Libro de Java", "En Stock"));
        tablaDatos.insertarFila(Arrays.asList("2", "Manual de Python", "Vendido"));
        tablaDatos.insertarFila(Arrays.asList("3", "Guía de SQL", "En Stock"));
        tablaDatos.insertarFila(Arrays.asList("4", "Diccionario de Inglés", "Agotado"));
        tablaDatos.insertarFila(Arrays.asList("5", "Diccionario de Frances", "Vendido"));

        // Mostrar la tabla de productos en consola
        System.out.println("Tabla de productos inicial:");
        mostrarTabla(tablaDatos);

        // Modificar el estado de un producto (usando setValor en Fila), con verificación de existencia
        System.out.println("\nModificando el estado del producto con ID 2...");
        if (tablaDatos.getFilas().size() > 1) {  // Verificar que exista la fila en el índice 1
            tablaDatos.getFila(1).getCelda(2).setValor("En Stock");
        } else {
            System.out.println("La fila con ID 2 no existe.");
        }
        mostrarTabla(tablaDatos);

        // Eliminar un producto (por ejemplo, el producto con ID 4), con verificación de existencia
        System.out.println("\nEliminando el producto con ID 4...");
        if (tablaDatos.getFilas().size() > 3) {  // Verificar que exista la fila en el índice 3
            tablaDatos.getFilas().remove(3);
        } else {
            System.out.println("La fila con ID 4 no existe.");
        }
        mostrarTabla(tablaDatos);

        // Filtrar productos que están "En Stock" (usando FiltroFila)
        System.out.println("\nFiltrando productos con estado 'En Stock'...");
        FiltroFila filtro = new FiltroFila("Estado", "En Stock", tablaDatos);
        List<Fila> filasFiltradas = filtro.filtrar();
        mostrarFilas(filasFiltradas);

        // Ordenar productos por "Nombre del Producto" (usando OrdenadorFilas)
        System.out.println("\nOrdenando productos por 'Nombre del Producto'...");
        OrdenadorFilas ordenador = new OrdenadorFilas("Nombre del Producto", true, tablaDatos);

        // Uso del objeto ordenador para ordenar las filas
        List<Fila> filasOrdenadas = ordenador.ordenar(tablaDatos.getFilas());
        mostrarFilas(filasOrdenadas);

        // Exportar la tabla a un archivo CSV (usando BibliotecaCSV)
        String nombreArchivo = "inventario.csv";
        String rutaArchivo = "test.txt";

        // Crear el archivo si no existe
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile(); // Crea el archivo
            } catch (Exception e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }

        BibliotecaCSV bibliotecaCSV = new BibliotecaCSV(nombreArchivo, rutaArchivo);
        bibliotecaCSV.setTablaDatos(tablaDatos);

        // // Verificar que la cantidad de filas en la tabla es consistente antes de exportar
        // if (tablaDatos.getFilas().size() == tablaDatos.getCantidadFilas()) {
        //     BibliotecaCSV.exportarCSV();
        // } else {
        //     System.out.println("Error: La cantidad de filas en la tabla no es consistente. Exportación abortada.");
        // }

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
        System.out.println(repetirCaracter('-', 50));
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

    // Método auxiliar para repetir un carácter
    private static String repetirCaracter(char caracter, int cantidad) {
        StringBuilder sb = new StringBuilder(cantidad);
        for (int i = 0; i < cantidad; i++) {
            sb.append(caracter);
        }
        return sb.toString();
    }
}
