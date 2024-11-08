import java.io.*;
// import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {
    private char delimitador;
    private String rutaArchivo;
    private String encoding;

    public LectorCSV(char delimitador, String rutaArchivo, String encoding) {
        this.delimitador = delimitador;
        this.rutaArchivo = rutaArchivo;
        this.encoding = encoding;
    }

    public TablaDatos leerArchivo() throws IOException {
        TablaDatos tablaDatos = new TablaDatos();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(rutaArchivo), encoding))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] valores = linea.split(String.valueOf(delimitador));
                List<Object> fila = new ArrayList<>();
                for (String valor : valores) {
                    fila.add(valor);
                }
                tablaDatos.insertarFila(fila);
            }
        }
        return tablaDatos;
    }

    public void escribirArchivo(TablaDatos tabla) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivo), encoding))) {
            for (int i = 0; i < tabla.getCantidadFilas(); i++) {
                Fila fila = tabla.getFila(i);
                StringBuilder linea = new StringBuilder();
                for (int j = 0; j < tabla.getCantidadColumnas(); j++) {
                    linea.append(fila.getCelda(j).getValor()).append(delimitador);
                }
                writer.write(linea.toString());
                writer.newLine();
            }
        }
    }
}
