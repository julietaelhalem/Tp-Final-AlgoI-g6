import java.util.List;

import Excepciones.ExcepcionValorFaltante;

public class GestorValoresFaltantes {
    
    @SuppressWarnings("unused")
    private String metodoManejo;          // Define el método para manejar valores faltantes (no se usa en esta implementación)
    private Object valorImputacion;       // Valor que se usará para imputar valores faltantes
    private GestorErrores gestorErrores;  // Instancia para registrar errores durante la imputación

    // Constructor que inicializa el método de manejo, el valor de imputación y el gestor de errores
    public GestorValoresFaltantes(String metodoManejo, Object valorImputacion, GestorErrores gestorErrores) {
        this.metodoManejo = metodoManejo;
        this.valorImputacion = valorImputacion;
        this.gestorErrores = gestorErrores;
    }

    // Método principal que intenta imputar valores faltantes en una columna específica de la tabla
    public void administrar(TablaDatos tabla, String columna) {
        try {
            tabla.imputarValores(columna, valorImputacion);
        } catch (Exception e) {
            gestorErrores.manejarError("Error en imputación: " + e.getMessage());
        }
    }

    // Aplica el valor por defecto en una columna específica de la tabla, usando el método de imputación
    public void aplicarValorPorDefecto(TablaDatos tabla, String columna) {
        administrar(tabla, columna);
    }

    // Método alias para realizar la imputación en la tabla
    public void imputar(TablaDatos tabla, String columna) {
        administrar(tabla, columna);
    }

    // Método para verificar valores completos en la tabla
    public void verificarValoresCompletos(TablaDatos tabla) throws ExcepcionValorFaltante {
        List<Fila> filas = tabla.getFilas();
        
        for (int i = 0; i < filas.size(); i++) {
            Fila fila = filas.get(i);
            for (int j = 0; j < fila.getCeldas().size(); j++) {
                CeldaDatos celda = fila.getCelda(j);
                String valor = (String) celda.getValor();
                
                if (valor == null || valor.trim().isEmpty()) {
                    throw new ExcepcionValorFaltante("Falta un valor en la fila " + (i + 1) + ", columna " + (j + 1));
                }
            }
        }
    }
}

