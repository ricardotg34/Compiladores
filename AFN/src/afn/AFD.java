package afn;

import java.util.HashSet;

public class AFD {
    private HashSet<Estado> estados=new HashSet<>();
    private HashSet<Character> alfabeto=new HashSet<>();
    private Estado eInicial;
    private HashSet<Estado> eFinal=new HashSet<>();
    
    
    public AFD() {
        estados=new HashSet<>();
        alfabeto=new HashSet<>();
        eFinal=new HashSet<>();
        eInicial=new Estado();
    }

    public HashSet<Character> getAlfabeto() {
        return alfabeto;
    }

    public HashSet<Estado> getEstados() {
        return estados;
    }

    public Estado geteInicial() {
        return eInicial;
    }

    public HashSet<Estado> geteFinal() {
        return eFinal;
    }

    public void setAlfabeto(HashSet<Character> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public void setEstados(HashSet<Estado> estados) {
        this.estados = estados;
    }

    public void seteFinal(HashSet<Estado> eFinal) {
        this.eFinal = eFinal;
    }

    public void seteInicial(Estado eInicial) {
        this.eInicial = eInicial;
    }
    
    
}
