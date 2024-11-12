import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {
    
    private char delimitador;     // Caracter delimitador para separar los valores del CSV
    private String rutaArchivo;   // Ruta del archivo CSV a leer o escribir
    private String encoding;      // Codificación utilizada para leer/escribir el archivo

    // Constructor que inicializa el delimitador, la ruta del archivo y la codificación
    public LectorCSV(char delimitador, String rutaArchivo, String encoding) {
        this.delimitador = delimitador;
        this.rutaArchivo = rutaArchivo;
        this.encoding = encoding;
    }

    // Lee el archivo CSV y retorna los datos en una instancia de TablaDatos
    public TablaDatos leerArchivo() throws IOException {
        TablaDatos tablaDatos = new TablaDatos();
        // Abre el archivo usando el encoding especificado
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(rutaArchivo), encoding))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Divide la línea en valores usando el delimitador
                String[] valores = linea.split(String.valueOf(delimitador));
                List<Object> fila = new ArrayList<>();
                for (String valor : valores) {
                    fila.add(valor); // Agrega cada valor a la fila
                }
                tablaDatos.insertarFila(fila); // Inserta la fila en la tabla de datos
            }
        }
        return tablaDatos;
    }

    // Escribe los datos de TablaDatos en el archivo CSV
    public void escribirArchivo(TablaDatos tabla) throws IOException {
        // Abre el archivo para escribir usando el encoding especificado
          
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivo), encoding))) {
            for (int i = 0; i < tabla.getCantidadFilas(); i++) {
                Fila fila = tabla.getFila(i);
                StringBuilder linea = new StringBuilder();
                for (int j = 0; j < tabla.getCantidadColumnas(); j++) {
                    // Agrega el valor de cada celda a la línea, seguido del delimitador
                    linea.append(fila.getCelda(j).getValor()).append(delimitador);
                }
                writer.write(linea.toString()); // Escribe la línea en el archivo
                writer.newLine(); // Salto de línea después de cada fila
            }
        }
    }
}
