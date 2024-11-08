import java.util.ArrayList;
import java.util.List;

public class Fila {
    private List<CeldaDatos> celdas;

    public Fila() {
        celdas = new ArrayList<>();
    }

    public void agregarCelda(CeldaDatos celda) {
        celdas.add(celda);
    }

    public CeldaDatos getCelda(int index) {
        return celdas.get(index);
    }

    public void eliminarCelda(int index) {
        celdas.remove(index);
    }

    public List<CeldaDatos> getCeldas() {
        return celdas;
    }

    public Fila copiar() {
        Fila copia = new Fila();
        for (CeldaDatos celda : celdas) {
            copia.agregarCelda(new CeldaDatos(celda.getValor(), celda.getTipoDato(), celda.getPosicionFila(), celda.getPosicionColumna()));
        }
        return copia;
    }
}
