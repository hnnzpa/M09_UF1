import java.util.Arrays;
import java.util.ArrayList; 
import java.util.Collections;

public class Monoalfabetic {
    static final char[] ALF = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();
    private static final char[] PERM = permutaAlfabet(ALF);

    public static void main(String[] args) {
        
        for (int i = 0; i < PERM.length; i++) {
            System.out.printf("%s -> %s%n", ALF[i], PERM[i]);
        }

        String original[] = {"Hola", "Hanna", "Qué tal van anar les vacances, Juan?"}; 
        String xifrat[] = new String[original.length];

        System.out.printf("%nXiftat%n---------%n");
        for (int i = 0; i < original.length; i++) {
            String paraula = original[i]; 
            xifrat[i] = xifraMonoAlfa(paraula);
            System.out.printf("%s -> %s%n", paraula, xifrat[i]);
            
        }

        System.out.printf("%nDesxiftat%n---------%n");
        for (int i = 0; i < xifrat.length; i++) {
            String paraula = xifrat[i]; 
            String desxifrada = desxifraMonoAlfa(paraula);
            System.out.printf("%s -> %s%n", paraula, desxifrada);
            
        }
        System.out.println();
    }

    public static String xifraMonoAlfa(String cadena){
        String cadenaXifrada = ""; 
        char[] lletresCadena = cadena.toCharArray(); 

        for (int i = 0; i < lletresCadena.length; i++) {
            char c = lletresCadena[i];

            boolean gran = caseLetter(c); 
            c = Character.toUpperCase(c);
            
            if (gran){
                cadenaXifrada += xifraC(c); 
            } else {
                cadenaXifrada += Character.toLowerCase(xifraC(c));
            }
            
        }
        return cadenaXifrada; 
    }

    public static String desxifraMonoAlfa(String cadenaX){
        String cadenaDesxifrada = ""; 
        char[] lletresCadena = cadenaX.toCharArray(); 

        for (int i = 0; i < lletresCadena.length; i++) {
            char c = lletresCadena[i];

            boolean gran = caseLetter(c); 
            c = Character.toUpperCase(c);

            if (gran){
                cadenaDesxifrada += desxifraC(c); 
            } else {
                cadenaDesxifrada += Character.toLowerCase(desxifraC(c));
            } 
            
        }
        return cadenaDesxifrada; 
    }

    //no funciona bé
    public static char desxifraC(char lletra){
        for (int i = 0; i < PERM.length; i++) {
            char lletraXifrada = PERM[i];
            if (lletra == lletraXifrada){
                lletra = ALF[i];
                return lletra; 
            }
        }
        return lletra; 
    }
    

    public static char xifraC(char lletra){
        for (int i = 0; i < ALF.length; i++) {
            char lletraOriginal = ALF[i];
            if (lletra == lletraOriginal){
                lletra = PERM[i];
                return lletra; 
            }
        }
        return lletra; 
    }

    public static char[] permutaAlfabet(char[] array){
        ArrayList<Character> llista = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            llista.add(array[i]);
        }
        
        Collections.shuffle(llista);

        char[] permutat = new char[llista.size()]; 
        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i); 
        }

        return permutat; 
    }

    public static boolean caseLetter(char lletra){
        if (Character.isUpperCase(lletra)){
            return true;
        }
        return false;
    }

}
