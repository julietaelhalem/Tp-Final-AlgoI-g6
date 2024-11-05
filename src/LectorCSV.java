import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public TablaDatos leerArchivo() {
        TablaDatos tabla = new TablaDatos();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(rutaArchivo), Charset.forName(encoding))) {
            String linea;
            boolean esPrimeraLinea = true;
            while ((linea = reader.readLine()) != null) {
                String[] valores = linea.split(Character.toString(delimitador));
                if (esPrimeraLinea) {
                    for (String etiqueta : valores) {
                        tabla.agregarColumna(etiqueta, "String"); // Tipo de dato inicial: String
                    }
                    esPrimeraLinea = false;
                } else {
                    Fila fila = new Fila();
                    for (String valor : valores) {
                        CeldaDatos celda = new CeldaDatos(valor, "String", 0, 0); // Tipo inicial: String
                        fila.agregarCelda(celda);
                    }
                    tabla.agregarFila(fila);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tabla;
    }

    public void escribirArchivo(TablaDatos tabla) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(rutaArchivo), Charset.forName(encoding))) {
            // Escribir encabezado (etiquetas de las columnas)
            List<String> etiquetasColumnas = tabla.getEtiquetasColumnas();
            writer.write(String.join(Character.toString(delimitador), etiquetasColumnas));
            writer.newLine();

            // Escribir cada fila
            for (Fila fila : tabla.getFilas()) {
                List<String> valores = new ArrayList<>();
                for (CeldaDatos celda : fila.getCeldas()) {
                    valores.add(celda.getValor().toString());
                }
                writer.write(String.join(Character.toString(delimitador), valores));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

