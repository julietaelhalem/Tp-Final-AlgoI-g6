import java.util.ArrayList;
import java.util.List;

public class FiltroFila {

    // Selecciona filas donde el valor en la columna especificada cumple con el valor objetivo
    public static List<Fila> filtrarPorColumna(TablaDatos tabla, String etiquetaColumna, Object valorObjetivo) {
        List<Fila> resultado = new ArrayList<>();
        List<CeldaDatos> columna = tabla.getColumna(etiquetaColumna);

        for (int i = 0; i < columna.size(); i++) {
            if (columna.get(i).getValor().equals(valorObjetivo)) {
                resultado.add(tabla.getFila(i));
            }
        }
        return resultado;
    }
}

