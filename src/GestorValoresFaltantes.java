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
}
