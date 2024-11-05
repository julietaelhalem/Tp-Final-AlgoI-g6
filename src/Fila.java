import java.util.ArrayList;
import java.util.List;

public class Fila {
    private List<CeldaDatos> celdas;

    public Fila() {
        this.celdas = new ArrayList<>();
    }

    public void agregarCelda(CeldaDatos celda) {
        celdas.add(celda);
    }

    public CeldaDatos getCelda(int index) {
        return celdas.get(index);
    }

    public List<CeldaDatos> getCeldas() {
        return celdas;
    }
}
