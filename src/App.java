import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Crear un Gestor de Errores
        GestorErrores gestorErrores = new GestorErrores();

        // Ruta del archivo CSV
        String rutaArchivo = "datos.csv";
        String nombreArchivo = "DatosCSV";

        // Crear la instancia de BibliotecaCSV
        BibliotecaCSV bibliotecaCSV = new BibliotecaCSV(nombreArchivo, rutaArchivo);
        TablaDatos tablaDatos = null;
        
        try {
            // Cargar los datos desde el archivo CSV
            tablaDatos = bibliotecaCSV.cargarCSV();
        } catch (IOException e) {
            gestorErrores.manejarError("Error al cargar el archivo CSV: " + e.getMessage());
        }

        // Verificar si los datos fueron cargados correctamente
        if (tablaDatos != null && tablaDatos.getCantidadFilas() > 0) {
            // Mostrar los datos antes de ordenarlos
            System.out.println("Datos originales:");
            for (Fila fila : tablaDatos.getFilas()) {
                for (CeldaDatos celda : fila.getCeldas()) {
                    System.out.print(celda.getValor() + ", ");
                }
                System.out.println();
            }

            // Ordenar las filas por la primera columna en orden ascendente
            OrdenadorFilas ordenador = new OrdenadorFilas(0, "ascendente");
            String[][] datos = tablaDatos.getDatos(); // Obtener los datos como array bidimensional
            ordenador.ordenar(datos);  // Ordenar los datos en el array

            // Mostrar los datos después de ordenarlos
            System.out.println("\nDatos después de ordenar:");
            for (String[] fila : datos) {
                System.out.println(String.join(", ", fila));
            }

            // Crear un FiltroFila para filtrar las filas (por ejemplo, buscar un valor específico)
            List<String> criterios = new ArrayList<>();
            criterios.add("valor_a_buscar");
            List<String> operadores = new ArrayList<>();
            operadores.add("and");
            FiltroFila filtro = new FiltroFila(criterios, operadores, tablaDatos);
            TablaDatos tablaFiltrada = filtro.aplicar();

            // Mostrar los datos filtrados
            System.out.println("\nDatos después de aplicar filtro:");
            for (Fila fila : tablaFiltrada.getFilas()) {
                for (CeldaDatos celda : fila.getCeldas()) {
                    System.out.print(celda.getValor() + ", ");
                }
                System.out.println();
            }

            // Imputar valores faltantes en la columna "columna_a_imputar"
            GestorValoresFaltantes gestorValoresFaltantes = new GestorValoresFaltantes("imputacion", "valor_defecto", gestorErrores);
            gestorValoresFaltantes.imputar(tablaDatos, "columna_a_imputar");

            // Exportar los datos a CSV después de modificaciones
            try {
                bibliotecaCSV.exportarCSV();
            } catch (IOException e) {
                gestorErrores.manejarError("Error al exportar el archivo CSV: " + e.getMessage());
            }

        } else {
            System.out.println("No se cargaron datos o no hay filas en el archivo CSV.");
        }

        // Imprimir errores si los hubo
        if (!gestorErrores.getListaErrores().isEmpty()) {
            System.out.println("\nErrores ocurridos:");
            for (String error : gestorErrores.getListaErrores()) {
                System.out.println(error);
            }
        }
    }
}
