public class CeldaDatos {
    private Object valor;
    private String tipoDato;
    private int posicionFila;
    private int posicionColumna;
    private boolean esValorFaltante;

    public CeldaDatos(Object valor, String tipoDato, int posicionFila, int posicionColumna) {
        this.valor = valor;
        this.tipoDato = tipoDato;
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
        this.esValorFaltante = valor == null;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
        this.esValorFaltante = valor == null;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public boolean esNA() {
        return esValorFaltante;
    }

    public void imputarValor(Object valorImputacion) {
        if (esValorFaltante) {
            this.valor = valorImputacion;
            this.esValorFaltante = false;
        }
    }

    public int getPosicionFila() {
        return posicionFila;
    }

    public int getPosicionColumna() {
        return posicionColumna;
    }
}
