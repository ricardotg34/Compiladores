package afn;
public class Transicion {
    private char simMax;
    private char simMin;
    private Estado edoSalida;

    public Transicion(char simb, Estado edoSalida) {
        this.simMax = simb;
        this.simMin = simb;
        this.edoSalida = edoSalida;
    }

    public Transicion(char simMin, char simMax, Estado edoSalida) {
        this.simMax = simMax;
        this.simMin = simMin;
        this.edoSalida = edoSalida;
    }

    public char getSimMax() {
        return simMax;
    }

    public char getSimMin() {
        return simMin;
    }

    public Estado getEdoSalida() {
        return edoSalida;
    }

    public void setSimMax(char simMax) {
        this.simMax = simMax;
    }

    public void setSimMin(char simMin) {
        this.simMin = simMin;
    }

    public void setEdoSalida(Estado edoSalida) {
        this.edoSalida = edoSalida;
    }
    
    
    
   
}
