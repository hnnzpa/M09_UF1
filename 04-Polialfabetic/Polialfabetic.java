import java.util.Arrays;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {
    static Random valor;
    static final char[] ALF = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();
    static char[] perm = new char[ALF.length]; 

    private static long clauSecreta = 1234L; 

    public static void main(String[] args) {

        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre", 
        "Test 02 Taüll, DÍA, año", 
        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n-----------");
        for (int i = 0; i < msgsXifrats.length; i++) {
            initRandom(clauSecreta); //fet
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgsXifrats.length; i++) {
            initRandom(clauSecreta); 
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static String xifraPoliAlfa(String cadena){
        permutaAlfabet(ALF, valor);
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

    public static String desxifraPoliAlfa(String cadenaX){
        permutaAlfabet(ALF, valor);
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

    public static char desxifraC(char lletra){
        for (int i = 0; i < perm.length; i++) {
            char lletraXifrada = perm[i];
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
                lletra = perm[i];
                return lletra; 
            }
        }
        return lletra; 
    }

    public static void initRandom(long clau){
        valor = new Random(clau);
    }

    public static void permutaAlfabet(char[] array, Random rnd){
        ArrayList<Character> llista = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            llista.add(array[i]);
        }
        
        Collections.shuffle(llista, rnd);

        char[] permutat = new char[llista.size()]; 
        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i); 
        }
        perm = permutat;
    }

    public static boolean caseLetter(char lletra){
        if (Character.isUpperCase(lletra)){
            return true;
        }
        return false;
    }

}
