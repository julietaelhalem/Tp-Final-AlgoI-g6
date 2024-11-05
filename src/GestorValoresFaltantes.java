import java.util.List;

public class GestorValoresFaltantes {

    // Rellena todas las celdas con valor NA en una columna específica
    public static void imputarColumna(String etiqueta, TablaDatos tabla, Object valorImputacion) {
        List<CeldaDatos> columna = tabla.getColumna(etiqueta);
        for (CeldaDatos celda : columna) {
            if (celda.getValor() == CeldaDatos.NA) {
                celda.setValor(valorImputacion);
            }
        }
    }

    // Rellena todas las celdas con valor NA en la tabla completa
    public static void imputarTabla(TablaDatos tabla, Object valorImputacion) {
        for (Fila fila : tabla.getFilas()) {
            for (CeldaDatos celda : fila.getCeldas()) {
                if (celda.getValor() == CeldaDatos.NA) {
                    celda.setValor(valorImputacion);
                }
            }
        }
    }
}
