package afn;

import java.util.HashSet;
import java.util.Stack;

public class AFN {
    private HashSet<Estado> estados=new HashSet<>();
    private HashSet<Character> alfabeto=new HashSet<>();
    private Estado eInicial;
    private HashSet<Estado> eFinal=new HashSet<>();

    public AFN() {
        estados=new HashSet<>();
        alfabeto=new HashSet<>();
        eFinal=new HashSet<>();
        eInicial=new Estado();
    }

    public AFN(char sMin, char sMax) {
        Estado edoFin;
        estados=new HashSet<>();
        alfabeto=new HashSet<>();
        eFinal=new HashSet<>();
        eInicial=new Estado();
        edoFin=new Estado();
        
        eInicial.addTransicion(sMin,sMax,edoFin);
        edoFin.setAcept(true);
        
        for (char s = sMin; s <= sMax ; s++)
            alfabeto.add(s);
        
        estados.add(eInicial);
        estados.add(edoFin);
        
        eFinal.add(edoFin);
    }
    
    public AFN(char simb) {
        Estado edoFin;
        estados=new HashSet<>();
        alfabeto=new HashSet<>();
        eFinal=new HashSet<>();
        eInicial=new Estado();
        edoFin=new Estado();
        
        eInicial.addTransicion(simb, edoFin);
        edoFin.setAcept(true);
        
        alfabeto.add(simb);
        
        estados.add(eInicial);
        estados.add(edoFin);
        
        eFinal.add(edoFin);
    }

    public HashSet<Estado> getEstados() {
        return estados;
    }

    public HashSet<Character> getAlfabeto() {
        return alfabeto;
    }

    public HashSet<Estado> geteFinal() {
        return eFinal;
    }

    public Estado geteInicial() {
        return eInicial;
    }
    
    public static void Union(AFN afn1, AFN afn2){
        Estado nIni=new Estado(),nFin=new Estado();
        
        nIni.addTransicion(SimbEspecial.Epsilon, afn1.eInicial);
        nIni.addTransicion(SimbEspecial.Epsilon, afn2.eInicial);
        
        for(Estado e : afn1.eFinal){
            e.addTransicion(SimbEspecial.Epsilon, nFin);
            e.setAcept(false);
        }
        
        for(Estado e : afn2.eFinal){
            e.addTransicion(SimbEspecial.Epsilon, nFin);
            e.setAcept(false);
        }
        afn1.eFinal.clear();
        
        nFin.setAcept(true);
        afn1.eFinal.add(nFin);
        
        afn1.eInicial=nIni;
        
        afn1.estados.addAll(afn2.estados);
        afn1.estados.add(nIni);
        afn1.estados.add(nFin);
        
        afn1.alfabeto.addAll(afn2.alfabeto);       
    }
    
    public static void Concat(AFN afn1, AFN afn2){
        
        for (Transicion t : afn2.eInicial.getTransiciones()) 
            for (Estado e : afn1.eFinal)
                e.addTransicion(t);
        
        for (Estado e : afn1.eFinal)
                e.setAcept(false);
        
        for (Estado e : afn2.estados) {
            if(e.equals(afn2.eInicial) == false)
                afn1.estados.add(e);
        }
        
        afn1.eFinal.clear();
        afn1.eFinal.addAll(afn2.geteFinal());
        
        afn1.alfabeto.addAll(afn2.alfabeto);
    }
    
    public static void cPositiva(AFN afn1){
        Estado nIni=new Estado(), nFin=new Estado();
        nIni.addTransicion(SimbEspecial.Epsilon,afn1.eInicial);
        
        for (Estado e : afn1.eFinal) {
            e.addTransicion(SimbEspecial.Epsilon, nFin);
            e.addTransicion(SimbEspecial.Epsilon, afn1.eInicial);
            e.setAcept(false);
        }
        
        afn1.eFinal.clear();
        
        nFin.setAcept(true);
        afn1.eFinal.add(nFin);
        
        afn1.eInicial=nIni;
        afn1.estados.add(nIni);
        afn1.estados.add(nFin);       
    }
    
    public static void cOpc(AFN afn1){
        Estado nIni=new Estado(), nFin=new Estado();
        nIni.addTransicion(SimbEspecial.Epsilon,afn1.eInicial);
        nIni.addTransicion(SimbEspecial.Epsilon,nFin);
        
        for (Estado e : afn1.eFinal) {
            e.addTransicion(SimbEspecial.Epsilon, nFin);
            e.setAcept(false);
        }
        
        afn1.eFinal.clear();        
        
        nFin.setAcept(true);
        afn1.eFinal.add(nFin);
        
        afn1.eInicial=nIni;
        afn1.estados.add(nIni);
        afn1.estados.add(nFin);  
    }
    
    public static void cKleene(AFN afn1){
        Estado nIni=new Estado(), nFin=new Estado();
        nIni.addTransicion(SimbEspecial.Epsilon,afn1.eInicial);
        nIni.addTransicion(SimbEspecial.Epsilon,nFin);
        
        for (Estado e : afn1.eFinal) {
            e.addTransicion(SimbEspecial.Epsilon, nFin);
            e.addTransicion(SimbEspecial.Epsilon, afn1.eInicial);
            e.setAcept(false);
        }
        
        afn1.eFinal.clear();        
        
        nFin.setAcept(true);
        afn1.eFinal.add(nFin);
        
        afn1.eInicial=nIni;
        afn1.estados.add(nIni);
        afn1.estados.add(nFin);  
    }
    
    HashSet<Estado> CerraduraEp(Estado e){
        Estado r;
        HashSet<Estado> c=new HashSet<>();
        Stack<Estado> s=new Stack<>();
        s.clear();
        s.push(e);
        while(! s.empty()){
            r=s.pop();
            if(! c.contains(r)){
                c.add(r);
                for (Transicion t : r.getTransiciones())
                    if(t.getSimMin() == SimbEspecial.Epsilon)
                        s.push(t.getEdoSalida());                  
            }            
        }
       return c; 
    }
    
    HashSet<Estado> CerraduraEp(HashSet<Estado> E){
        HashSet<Estado> c=new HashSet<>();
        for (Estado e : E)
            c.addAll(CerraduraEp(e));
       return c; 
    }
    
    HashSet<Estado> Mover(Estado e, char c){
        HashSet<Estado> R=new HashSet<>();
        for (Transicion t : e.getTransiciones()){
            if(c >= t.getSimMin() && c <= t.getSimMax())
                R.add(t.getEdoSalida());
        }
        return R;                
    }
    
    HashSet<Estado> Mover(HashSet<Estado> E, char c){
        HashSet<Estado> R=new HashSet<>();
        for (Estado e : E)
            R.addAll(Mover(e,c));
        return R;                
    }
    
    HashSet<Estado> IrA(HashSet<Estado> E, char c){
        HashSet<Estado> R=new HashSet<>();
        for (Estado e : E)
            R.addAll(Mover(e,c));
        return CerraduraEp(R);                
    }
    
    boolean AnalizarCadena(String s){
        HashSet<Estado>C=new HashSet<>() ;
        C=CerraduraEp(this.eInicial);
        for (int i = 0; i < s.length(); i++) {
            C=IrA(C,s.charAt(i));
            if(C.isEmpty())
                return false;
        }
        for (Estado e : this.eFinal) {
            if(C.contains(e))
                return true;
        }
        return false;
    }
    
    public AFD AFNtoAFD (){
        AFD afd=new AFD();
        afd.setAlfabeto(this.alfabeto);
        HashSet<EstadosSi> Si=new HashSet<>();
        Si.add(new EstadosSi(CerraduraEp(this.eInicial)));
        for (Estado e : Si.) {
            
        }
    }
}
