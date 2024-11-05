import java.util.Arrays;
import java.util.Comparator;

public class OrdenadorFilas {
    private int columnasOrdenamiento;
    private String tipoOrden; // "ascendente" o "descendente"

    public OrdenadorFilas(int columnasOrdenamiento, String tipoOrden) {
        this.columnasOrdenamiento = columnasOrdenamiento;
        this.tipoOrden = tipoOrden;
    }

    public void ordenar(String[][] datos) {
        Arrays.sort(datos, new Comparator<String[]>() {
            @Override
            public int compare(String[] fila1, String[] fila2) {
                if (tipoOrden.equalsIgnoreCase("ascendente")) {
                    return fila1[columnasOrdenamiento].compareTo(fila2[columnasOrdenamiento]);
                } else {
                    return fila2[columnasOrdenamiento].compareTo(fila1[columnasOrdenamiento]);
                }
            }
        });
    }
}
