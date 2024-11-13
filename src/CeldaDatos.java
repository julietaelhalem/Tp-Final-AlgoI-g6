
public class CeldaDatos {
    
    private Object valor;             
    private String tipoDato;         
    private int posicionFila;         
    private int posicionColumna;      
    private boolean esValorFaltante; 

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
    

