import java.io.IOException;

public class BibliotecaCSV {
    private String nombreArchivo;
    private String rutaArchivo;
    private TablaDatos tablaDatos;

    public BibliotecaCSV(String nombreArchivo, String rutaArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.tablaDatos = new TablaDatos();
    }

    public TablaDatos cargarCSV() {
        LectorCSV lector = new LectorCSV(',', rutaArchivo, "UTF-8");
        try {
            this.tablaDatos = lector.leerArchivo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tablaDatos;
    }

    public void exportarCSV() {
        LectorCSV escritor = new LectorCSV(',', rutaArchivo, "UTF-8");
        try {
            escritor.escribirArchivo(tablaDatos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}