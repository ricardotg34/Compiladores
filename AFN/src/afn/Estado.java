package afn;

import java.util.HashSet;

public class Estado {
    private int id;
    private boolean acept;
    private HashSet<Transicion> transiciones;
    private static int cont=0;

    Estado() {
        transiciones=new HashSet<>();
        this.id = cont;
        cont++;
        this.acept = false;
    }

    public int getId() {
        return id;
    }

    public boolean isAcept() {
        return acept;
    }

    public void addTransicion(char simb, Estado edo) {
        transiciones.add(new Transicion(simb,edo));
    }
    
    public void addTransicion(char sMax,char sMin, Estado edo) {
        transiciones.add(new Transicion(sMax,sMin,edo));
    }
    
    public void addTransicion(Transicion t) {
        transiciones.add(t);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAcept(boolean acept) {
        this.acept = acept;
    }

    public HashSet<Transicion> getTransiciones() {
        return transiciones;
    }
    
    
}
