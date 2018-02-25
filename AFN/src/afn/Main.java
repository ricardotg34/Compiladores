package afn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AFN afn [] = new AFN[5];
        menu(afn);
//        afn1=new AFN('a','z');
//        afn2=new AFN('2','5');
//        AFN.Union(afn1,afn2);
//        
//        AFN.cKleene(afn1);
//        
//       afn2=null;
//       afn2=new AFN('b');
//       AFN.cPositiva(afn2);
//       AFN.Concat(afn1, afn2);
        
        System.out.println("Estado Inicial:"+afn[0].geteInicial().getId());
        for (Estado e: afn[0].geteFinal()) {
            System.out.println("Estado Final:"+e.getId());
        }
        
        for (Estado e : afn[0].getEstados()){
            System.out.println("Estado: "+e.getId());
            System.out.println("Transiciones:");
            for(Transicion t : e.getTransiciones()){
                System.out.print(t.getSimMin()+",");
                System.out.print(t.getSimMax()+",");
                System.out.println(t.getEdoSalida().getId()+"\n");                
            }
        }
        System.out.println("\nAlfabeto:");
        for(char a : afn[0].getAlfabeto())
            System.out.println(a);
    }
    
    public static void menu(AFN afn[]){
        Scanner entrada=new Scanner(System.in);
        String simbolo;
        String cadena;
        byte opcion,i1,i2;
        
        System.out.println("..........AFN..........");
        System.out.println("1. Crear AFN basico.");
        System.out.println("2. Unir AFN´s");
        System.out.println("3. concatenar AFN´s");
        System.out.println("4. Operacion Cerradura +.");
        System.out.println("5. Operacion ?");
        System.out.println("6. Operacion Cerradura *.");
        System.out.println("7. Validar cadena");
        System.out.println("8.Salir");
        System.out.print("Escoge una opcion:");
        opcion=entrada.nextByte();
        switch (opcion) {
            case 1:
                System.out.print("En que espacio desea guardar el automata? (1, 2, o 3):");
                i1=entrada.nextByte();
                System.out.print("Escribe el simbolo o rango de simbolos (a-z):");
                simbolo=entrada.next();
                if(simbolo.length()>1)
                    afn[i1-1]=new AFN(simbolo.charAt(0),simbolo.charAt(2));
                else
                    afn[i1-1]=new AFN(simbolo.charAt(0));
                menu(afn);
            case 2:
                System.out.print("Espacio del automata 1 (1, 2 o 3):");
                i1=entrada.nextByte();
                System.out.print("Espacio del automata 2 (1, 2 o 3):");
                i2=entrada.nextByte();                
                AFN.Union(afn[i1-1], afn[i2-1]);
                afn[i2-1]=null;
                menu(afn);
            case 3:
                System.out.print("Espacio del automata 1 (1, 2 o 3):");
                i1=entrada.nextByte();
                System.out.print("Espacio del automata 2 (1, 2 o 3):");
                i2=entrada.nextByte();               
                AFN.Concat(afn[i1-1], afn[i2-1]);
                afn[i2-1]=null;
                menu(afn);
            case 4:
                System.out.print("Espacio del automata (1, 2 o 3):");
                i1=entrada.nextByte();
                AFN.cPositiva(afn[i1-1]);
                menu(afn);
            case 5:
                System.out.print("Espacio del automata (1, 2 o 3):");
                i1=entrada.nextByte();
                AFN.cOpc(afn[i1-1]);
                menu(afn);
            case 6:
                System.out.print("Espacio del automata (1, 2 o 3):");
                i1=entrada.nextByte();
                AFN.cKleene(afn[i1-1]);
                menu(afn);
            case 7:
                System.out.print("Escribe la cadena a evaluar:");
                cadena=entrada.next();
                System.out.println("Espacio del automata con el cual se evaluara la cadena (1, 2 o 3):");
                i1=entrada.nextByte();
                if(afn[i1-1].AnalizarCadena(cadena) == true)
                    System.out.println("Cadena aceptada");
                else
                    System.out.println("Cadena no aceptada ");
                menu(afn);
            case 8:
                System.exit(0);
            default:
                System.out.println("Escribe una opcion valida");
                menu(afn);
        }
    }
}
