import java.io.IOException;

public class BibliotecaCSV {
    @SuppressWarnings("unused")
    private String nombreArchivo;
    private String rutaArchivo;
    private TablaDatos tablaDatos;

    public BibliotecaCSV(String nombreArchivo, String rutaArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.tablaDatos = new TablaDatos();
    }

    // Implementación de setTablaDatos para asignar la tabla de datos
    public void setTablaDatos(TablaDatos tablaDatos) {
        this.tablaDatos = tablaDatos;
    }

    public TablaDatos cargarCSV() throws IOException {
        LectorCSV lector = new LectorCSV(',', rutaArchivo, "UTF-8");
        this.tablaDatos = lector.leerArchivo();
        return tablaDatos;
    }

    public void exportarCSV() throws IOException {
        LectorCSV escritor = new LectorCSV(',', rutaArchivo, "UTF-8");
        escritor.escribirArchivo(tablaDatos);
    }
}
