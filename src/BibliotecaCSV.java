import java.io.IOException;

public class BibliotecaCSV {
    @SuppressWarnings("unused") 
    private String nombreArchivo;   
    private String rutaArchivo;     
    private TablaDatos tablaDatos;  

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
       
        LectorCSV lector = new LectorCSV(',', rutaArchivo, "UTF-8");
      
        this.tablaDatos = lector.leerArchivo();
        return tablaDatos;
    }

    // Método que exporta los datos de tablaDatos a un archivo CSV
    public void exportarCSV() throws IOException {
       
        LectorCSV escritor = new LectorCSV(',', rutaArchivo, "UTF-8");
        
        escritor.escribirArchivo(tablaDatos);
    }
}
