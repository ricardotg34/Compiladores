package afn;

import java.util.HashSet;

public class EstadosSi {
    private static short contador=0;
    private int id;
    private boolean analizado;
    private HashSet<Estado> Estados;

    public EstadosSi(HashSet<Estado> Estados ) {
        this.id=contador++;
        this.analizado=false;
        this.Estados=Estados;
    }

    public HashSet<Estado> getEstados() {
        return Estados;
    }
    
}
