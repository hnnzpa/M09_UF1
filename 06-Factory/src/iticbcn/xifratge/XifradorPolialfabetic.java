package iticbcn.xifratge;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    Random valor;
    static final char[] ALF = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();
    char[] perm = new char[ALF.length]; 

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            long clauSecreta = Long.parseLong(clau);
            initRandom(clauSecreta);
            String resultado = xifraPoliAlfa(msg);
            return new TextXifrat(resultado.getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (xifrat == null) return null;
        
        try {
            long clauSecreta = Long.parseLong(clau);
            initRandom(clauSecreta);
            String texto = new String(xifrat.getBytes());
            return desxifraPoliAlfa(texto);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
    }

    public String xifraPoliAlfa(String cadena){
        permutaAlfabet(ALF, valor);
        StringBuilder cadenaXifrada = new StringBuilder(); 
        char[] lletresCadena = cadena.toCharArray(); 

        for (char c : lletresCadena) {
            boolean gran = Character.isUpperCase(c); 
            c = Character.toUpperCase(c);
            
            char xifrat = xifraC(c);
            cadenaXifrada.append(gran ? xifrat : Character.toLowerCase(xifrat));
        }
        return cadenaXifrada.toString(); 
    }

    public String desxifraPoliAlfa(String cadenaX){
        permutaAlfabet(ALF, valor);
        StringBuilder cadenaDesxifrada = new StringBuilder(); 
        char[] lletresCadena = cadenaX.toCharArray(); 

        for (char c : lletresCadena) {
            boolean gran = Character.isUpperCase(c); 
            c = Character.toUpperCase(c);

            char desxifrat = desxifraC(c);
            cadenaDesxifrada.append(gran ? desxifrat : Character.toLowerCase(desxifrat));
        }
        return cadenaDesxifrada.toString(); 
    }

    public char desxifraC(char lletra){
        for (int i = 0; i < perm.length; i++) {
            if (lletra == perm[i]){
                return ALF[i];
            }
        }
        return lletra; 
    }
    

    public char xifraC(char lletra){
        for (int i = 0; i < ALF.length; i++) {
            if (lletra == ALF[i]){
                return perm[i];
            }
        }
        return lletra; 
    }

    public void initRandom(long clau){
        valor = new Random(clau);
    }

    public void permutaAlfabet(char[] array, Random rnd){
        ArrayList<Character> llista = new ArrayList<>();
        for (char c : array) {
            llista.add(c);
        }
        
        Collections.shuffle(llista, rnd);

        for (int i = 0; i < llista.size(); i++) {
            perm[i] = llista.get(i); 
        }
    }
}