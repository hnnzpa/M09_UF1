public class RotX{
    final static char[] MIN = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    final static char[] MAJ = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();
    final static 

    public static void main(String[] args) {
        String cadena[] = {"ABC", "xyz", "Hola, Mr colçot", "Perdó, per tu què és?"};
        int rota[] = {2, 4, 12, 20}; 

        for (int i = 0; i < cadena.length; i++){
            System.out.println(xifraRotX(cadena[i], rota[i]));
        }
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
    /*
    public static String desxifraRotX(String cadena, int desplaçament){ 
        char[] cadenaC = cadena.toCharArray(); 
        String textDesxifrat = ""; 

        for (int i = 0; i <= cadena.length()-1; i++){
            char lletra = cadenaC[i];
            boolean gran = false;
            if (Character.isLetter(lletra)){

                if (Character.isUpperCase(lletra)){
                    gran = true; 
                }

                lletra = Character.toLowerCase(lletra);
                int pos = posicio(lletra);

                if (pos == -1){ // no està a l'array, l'afegim tal qual
                    textDesxifrat += lletra; 
                    continue; 
                }

                // desplaçar 13 enrere (amb volta si cal)
                pos -= 13;
                if (pos < 0) pos += lletres.length;

                // mirar si era maj i afegir la lletra corresponent
                if (gran){
                    textDesxifrat += Character.toUpperCase(lletraXPosicio(pos)); 
                }else{
                    textDesxifrat += lletraXPosicio(pos);
                }
            }else {
                textDesxifrat += lletra; 
            }
        }
        return textDesxifrat; }
    
    public static String forcaBrutaX(String cadenaXifrada){}
*/
    
    public static int esTrobaPosicioArray(char character, String[] array){
        for (int i = 0; i < array.length; i++){
            char c = array[i];
            if (character == c){ 
                return i; 
            }
        }
        return -1; 
    }

    public static char xifraC(char lletra, int n){
        if (esTrobaPosicioArray(lletra, MIN)>=0){
            return lletraXPosicio(posicio(lletra, MIN, n), MIN);  
        }else if (esTrobaPosicioArray(lletra, MAJ)>=0){
            return lletraXPosicio(posicio(lletra, MAJ, n), MAJ);
        }
        return lletra;                 
    }

    public static int posicio(char lletra, String[] array, int despl){
        int pos = esTrobaPosicioArray(lletra, array);
        if (pos >= ((array.length)-despl)){
            pos = (pos+despl)%lletres.length; 
        } else {
            pos += despl;
        }
        return pos; 
    }

    public static char lletraXPosicio(int pos, String[] array){
        return array[pos];
    }
}
