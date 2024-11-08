public class GestorValoresFaltantes {
    @SuppressWarnings("unused")
    private String metodoManejo;
    private Object valorImputacion;
    private GestorErrores gestorErrores;

    public GestorValoresFaltantes(String metodoManejo, Object valorImputacion, GestorErrores gestorErrores) {
        this.metodoManejo = metodoManejo;
        this.valorImputacion = valorImputacion;
        this.gestorErrores = gestorErrores;
    }

    public void administrar(TablaDatos tabla, String columna) {
        try {
            tabla.imputarValores(columna, valorImputacion);
        } catch (Exception e) {
            gestorErrores.manejarError("Error en imputación: " + e.getMessage());
        }
    }

    public void aplicarValorPorDefecto(TablaDatos tabla, String columna) {
        administrar(tabla, columna);
    }

    public void imputar(TablaDatos tabla, String columna) {
        administrar(tabla, columna);
    }
}
