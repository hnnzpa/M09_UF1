public class RotX{
    final static char[] MIN = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    final static char[] MAJ = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();

    public static void main (String[] args) {
        String cadena[] = {"ABC", "xyz", "Hola, Mr colçot", "Perdó, per tu què és?", "Hanna"};
        String cadenaXifrada[] = new String[cadena.length];
        int rota[] = {0, 2, 4, 12, 20};
        int nXifra = 3;  

        System.out.printf("%nXIFRAT%n");
        System.out.println("--------");
        for (int i = 0; i < cadena.length; i++){
            System.out.printf("(%d) %-25s => %s%n",rota[i], cadena[i], xifraRotX(cadena[i], rota[i]));
            cadenaXifrada[i] = xifraRotX(cadena[i], rota[i]) ;
        }

        System.out.printf("%nDESXIFRAT%n");
        System.out.println("--------");
        for (int i = 0; i < cadenaXifrada.length; i++){
            System.out.printf("(%d) %-25s => %s%n", rota[i], cadenaXifrada[i], desxifraRotX(cadenaXifrada[i], rota[i]));
        }

        System.out.printf("%nMissatge xifrat: %s%n---------%n", cadenaXifrada[nXifra]);
        imprimirStrArray(forcaBrutaX(cadenaXifrada[nXifra]));
        
    }

    public static String xifraRotX(String cadena, int desplaçament){
        char[] cadenaC = cadena.toCharArray(); 
        String textXifrat = ""; 
        
        for (int i = 0; i < cadena.length(); i++){
            char lletra = cadenaC[i];
            textXifrat += xifraC(lletra, desplaçament); 
        }
        return textXifrat; 
    }

    public static char xifraC(char lletra, int n){
        if (esTrobaPosicioArray(lletra, MIN)>=0){
            return lletraXPosicio(xifraPosicio(lletra, MIN, n), MIN);  
        }else if (esTrobaPosicioArray(lletra, MAJ)>=0){
            return lletraXPosicio(xifraPosicio(lletra, MAJ, n), MAJ);
        }
        return lletra;                 
    }

    public static int xifraPosicio(char lletra, char[] array, int despl){
        int pos = esTrobaPosicioArray(lletra, array);
        if (pos >= ((array.length)-despl)){
            pos = (pos+despl)%array.length; 
        } else {
            pos += despl;
        }
        return pos; 
    }
    
    public static String desxifraRotX(String cadena, int desplaçament){ 
        char[] cadenaC = cadena.toCharArray(); 
        String textDesxifrat = ""; 

        for (int i = 0; i <= cadena.length()-1; i++){
            char lletra = cadenaC[i];
            textDesxifrat += desxifraC(lletra, desplaçament);
        }
        return textDesxifrat; 
    }

    public static char desxifraC (char lletra, int n){
        if (esTrobaPosicioArray(lletra, MIN)>=0){
            return lletraXPosicio(desxifraPosicio(lletra, MIN, n), MIN);  
        }else if (esTrobaPosicioArray(lletra, MAJ)>=0){
            return lletraXPosicio(desxifraPosicio(lletra, MAJ, n), MAJ);
        }
        return lletra;                 
    }
    
    public static int desxifraPosicio(char lletra, char[] array, int despl){
        int pos = (esTrobaPosicioArray(lletra, array)-despl);
        if (pos < 0){
            pos += array.length;
        }
        return pos; 
    }
    
    public static String[] forcaBrutaX(String cadenaXifrada){
        String[] cadenesDesxifrades = new String[MIN.length];

        for (int i = 0; i < MIN.length; i++){
            cadenesDesxifrades[i] = desxifraRotX(cadenaXifrada, i);
        }
        return cadenesDesxifrades;
    }

    public static int esTrobaPosicioArray(char character, char[] array){
        for (int i = 0; i < array.length; i++){
            char c = array[i];
            if (character == c){ 
                return i; 
            }
        }
        return -1; 
    }
    
    public static char lletraXPosicio(int pos, char[] array){
        return array[pos];
    }

    public static void imprimirStrArray(String[] array){
        for (int i = 0; i < array.length; i++){
            System.out.printf("(%d) -> %s%n",i, array[i]);
        }
    }
}
