import java.io.IOException;

public class BibliotecaCSV {
    @SuppressWarnings("unused") 
    private String nombreArchivo;   // Nombre del archivo CSV (no se utiliza actualmente)
    private String rutaArchivo;     // Ruta del archivo CSV
    private TablaDatos tablaDatos;  // Objeto que almacena los datos de la tabla cargados desde o exportados hacia el archivo CSV

    // Constructor que inicializa el nombre y la ruta del archivo, y crea una instancia de TablaDatos
    public BibliotecaCSV(String nombreArchivo, String rutaArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.tablaDatos = new TablaDatos();
    }

    // Método para asignar un objeto TablaDatos a la instancia
    public void setTablaDatos(TablaDatos tablaDatos) {
        this.tablaDatos = tablaDatos;
    }

    // Método que carga los datos del archivo CSV en la tablaDatos y devuelve la tabla
    public TablaDatos cargarCSV() throws IOException {
        // Crea un objeto LectorCSV para leer el archivo CSV
        LectorCSV lector = new LectorCSV(',', rutaArchivo, "UTF-8");
        // Carga los datos desde el archivo CSV y los asigna a tablaDatos
        this.tablaDatos = lector.leerArchivo();
        return tablaDatos;
    }

    // Método que exporta los datos de tablaDatos a un archivo CSV
    public void exportarCSV() throws IOException {
        // Crea un objeto LectorCSV para escribir el archivo CSV
        LectorCSV escritor = new LectorCSV(',', rutaArchivo, "UTF-8");
        // Exporta los datos de tablaDatos al archivo CSV
        escritor.escribirArchivo(tablaDatos);
    }
}
