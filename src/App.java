import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Excepciones.ExcepcionDatosInvalidos;
import Excepciones.ExcepcionLecturaArchivo;
import Excepciones.ExcepcionValorFaltante;

public class App {
    public static void main(String[] args) {
        // Crear instancia de TablaDatos y definir etiquetas de columnas
        TablaDatos tablaDatos = new TablaDatos();
        List<String> etiquetasColumnas = Arrays.asList("ID", "Nombre del Producto", "Estado");
        tablaDatos.setEtiquetasColumnas(etiquetasColumnas);

        // Agregar filas iniciales de productos con manejo de ExcepcionDatosInvalidos
        try {
            tablaDatos.insertarFila(Arrays.asList("1", "Libro de Java", "En Stock"));
            tablaDatos.insertarFila(Arrays.asList("2", "Manual de Python", "Vendido"));
            tablaDatos.insertarFila(Arrays.asList("3", "Guía de SQL", "En Stock"));
            tablaDatos.insertarFila(Arrays.asList("4", "Diccionario de Inglés", "Agotado"));
            tablaDatos.insertarFila(Arrays.asList("5", "Diccionario de Francés", "Vendido"));
        } catch (ExcepcionDatosInvalidos e) {
            System.err.println("Error al insertar fila: " + e.getMessage());
        }

        // Mostrar la tabla de productos en consola
        System.out.println("Tabla de productos inicial:");
        mostrarTabla(tablaDatos);

        // Modificar el estado de un producto con verificación de existencia y ExcepcionDatosInvalidos
        System.out.println("\nModificando el estado del producto con ID 2...");
        if (tablaDatos.getFilas().size() > 1) {
            try {
                tablaDatos.getFila(1).getCelda(2).setValor("En Stock");
            } catch (ExcepcionDatosInvalidos e) {
                System.err.println("Error al modificar el estado: " + e.getMessage());
            }
        } else {
            System.out.println("La fila con ID 2 no existe.");
        }
        mostrarTabla(tablaDatos);

        // Eliminar un producto con verificación de existencia
        System.out.println("\nEliminando el producto con ID 4...");
        if (tablaDatos.getFilas().size() > 3) {
            tablaDatos.eliminarFilas(3);
        } else {
            System.out.println("La fila con ID 4 no existe.");
        }
        mostrarTabla(tablaDatos);

        // Filtrar productos que están "En Stock"
        System.out.println("\nFiltrando productos con estado 'En Stock'...");
        FiltroFila filtro = new FiltroFila("Estado", "En Stock", tablaDatos);
        List<Fila> filasFiltradas = filtro.filtrar();
        mostrarFilas(filasFiltradas);

        // Ordenar productos por "Nombre del Producto"
        System.out.println("\nOrdenando productos por 'Nombre del Producto'...");
        OrdenadorFilas ordenador = new OrdenadorFilas("Nombre del Producto", true, tablaDatos);
        List<Fila> filasOrdenadas = ordenador.ordenar(tablaDatos.getFilas());
        mostrarFilas(filasOrdenadas);

        // Exportar la tabla a un archivo CSV
        String nombreArchivo = "inventario.csv";
        String rutaArchivo = "InventarioLibreria.csv";
        File archivo = new File(rutaArchivo);
        
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }

        BibliotecaCSV bibliotecaCSV = new BibliotecaCSV(nombreArchivo, rutaArchivo);
        bibliotecaCSV.setTablaDatos(tablaDatos);

        try {
            bibliotecaCSV.exportarCSV();
            System.out.println("\nArchivo CSV exportado exitosamente a " + rutaArchivo + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al exportar el archivo CSV: " + e.getMessage());
        } catch (ExcepcionLecturaArchivo e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }

        // Pruebas adicionales para GestorErrores y GestorValoresFaltantes
        System.out.println("\nPrueba de gestión de errores y valores faltantes:");

        // Crear instancias de GestorErrores y GestorValoresFaltantes
        GestorErrores gestorErrores = new GestorErrores();
        GestorValoresFaltantes gestorValoresFaltantes = new GestorValoresFaltantes(rutaArchivo, tablaDatos, gestorErrores);

        // Verificar valores faltantes en la tabla
        try {
            gestorValoresFaltantes.verificarValoresCompletos(tablaDatos);
            System.out.println("Todos los valores están completos.");
        } catch (ExcepcionValorFaltante e) {
            System.err.println("Error: Hay valores faltantes en la tabla - " + e.getMessage());
        }

        System.out.println("Pruebas completadas con GestorErrores y GestorValoresFaltantes.");
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
