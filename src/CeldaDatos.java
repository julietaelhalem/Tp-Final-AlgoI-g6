public class CeldaDatos {
    
    private Object valor;             // Valor almacenado en la celda, de tipo genérico
    private String tipoDato;          // Tipo de dato del valor (e.g., "String", "Integer")
    private int posicionFila;         // Posición de la celda en la fila de la tabla
    private int posicionColumna;      // Posición de la celda en la columna de la tabla
    private boolean esValorFaltante;  // Indica si la celda tiene un valor faltante (true si es nulo)

    // Constructor que inicializa el valor, tipo de dato y posiciones, y determina si es valor faltante
    public CeldaDatos(Object valor, String tipoDato, int posicionFila, int posicionColumna) {
        this.valor = valor;
        this.tipoDato = tipoDato;
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
        this.esValorFaltante = valor == null;
    }

    // Devuelve el valor de la celda
    public Object getValor() {
        return valor;
    }

    // Asigna un nuevo valor a la celda y actualiza si es un valor faltante
    public void setValor(Object valor) {
        this.valor = valor;
        this.esValorFaltante = valor == null;
    }

    // Devuelve el tipo de dato del valor almacenado
    public String getTipoDato() {
        return tipoDato;
    }

    // Indica si la celda contiene un valor faltante
    public boolean esNA() {
        return esValorFaltante;
    }

    // Imputa un valor a la celda si actualmente tiene un valor faltante
    public void imputarValor(Object valorImputacion) {
        if (esValorFaltante) {
            this.valor = valorImputacion;
            this.esValorFaltante = false;
        }
    }

    // Devuelve la posición de la celda en la fila
    public int getPosicionFila() {
        return posicionFila;
    }

    // Devuelve la posición de la celda en la columna
    public int getPosicionColumna() {
        return posicionColumna;
    }
}
