package iticbcn.xifratge;

import java.util.ArrayList; 
import java.util.Collections;

public class XifradorMonoalfabetic implements Xifrador {
    static final char[] ALF = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();
    private final char[] PERM;

    public XifradorMonoalfabetic() {
        this.PERM = permutaAlfabet(ALF);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String cadenaXifrada = ""; 
        char[] lletresCadena = msg.toCharArray(); 

        for (int i = 0; i < lletresCadena.length; i++) {
            char c = lletresCadena[i];
            boolean gran = Character.isUpperCase(c); 
            c = Character.toUpperCase(c);
            
            char xifrat = xifraC(c);
            cadenaXifrada += gran ? xifrat : Character.toLowerCase(xifrat);
        }
        return new TextXifrat(cadenaXifrada.getBytes()); 
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        if (xifrat == null) return null;
        
        String cadenaDesxifrada = ""; 
        String cadenaX = new String(xifrat.getBytes());
        char[] lletresCadena = cadenaX.toCharArray(); 

        for (int i = 0; i < lletresCadena.length; i++) {
            char c = lletresCadena[i];
            boolean gran = Character.isUpperCase(c); 
            c = Character.toUpperCase(c);

            char desxifrat = desxifraC(c);
            cadenaDesxifrada += gran ? desxifrat : Character.toLowerCase(desxifrat);
        }
        return cadenaDesxifrada; 
    }

    public char desxifraC(char lletra){
        for (int i = 0; i < PERM.length; i++) {
            if (lletra == PERM[i]){
                return ALF[i];
            }
        }
        return lletra; 
    }
    

    public char xifraC(char lletra){
        for (int i = 0; i < ALF.length; i++) {
            if (lletra == ALF[i]){
                return PERM[i];
            }
        }
        return lletra; 
    }

    public char[] permutaAlfabet(char[] array){
        ArrayList<Character> llista = new ArrayList<>();
        for (char c : array) {
            llista.add(c);
        }
        
        Collections.shuffle(llista);

        char[] permutat = new char[llista.size()]; 
        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i); 
        }
        return permutat; 
    }
}