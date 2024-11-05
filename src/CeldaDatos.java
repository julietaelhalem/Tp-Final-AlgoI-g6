public class CeldaDatos {
    public static final String NA = "NA";
    
    private Object valor;
    private String tipoDato;
    private int posicionFila;
    private int posicionColumna;

    public CeldaDatos(Object valor, String tipoDato, int posicionFila, int posicionColumna) {
        if (validarTipoDato(valor, tipoDato)) {
            this.valor = valor;
        } else {
            this.valor = NA;
        }
        this.tipoDato = tipoDato;
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
    }

    private boolean validarTipoDato(Object valor, String tipoDato) {
        if (valor == null || valor.equals(NA)) {
            return true; // Aceptamos NA como válido para cualquier tipo
        }
        switch (tipoDato.toLowerCase()) {
            case "numerico":
                return valor instanceof Number;
            case "booleano":
                return valor instanceof Boolean;
            case "cadena":
                return valor instanceof String;
            default:
                return false;
        }
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        if (validarTipoDato(valor, tipoDato)) {
            this.valor = valor;
        } else {
            this.valor = NA;
        }
    }

    public String getTipoDato() {
        return tipoDato;
    }
}
