public class RotX{
    final static char[] MIN = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    final static char[] MAJ = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();

    public static void main(String[] args) {
        String cadena[] = {"ABC", "xyz", "Hola, Mr colçot", "Perdó, per tu què és?"};
        int rota = 2; 
        System.out.println(xifraRotX(cadena[0]));
        System.out.println(xifraRotX(cadena[1]));
    }

    public static String xifraRotX(String cadena){
        char[] cadenaC = cadena.toCharArray(); 
        String textXifrat = ""; 
        
        //fer un mètode per desxifrar un caracter



        for (int i = 0; i <= cadena.length()-1; i++){
            char lletra = cadenaC[i];
            boolean gran = false;

            if (esTrobaPosicioMaj(lletra)>=0 || esTrobaPosicioMin(lletra)>=0){
                if (Character.isUpperCase(lletra)){ 
                    gran = true; 
                    lletra = Character.toLowerCase(lletra);
                }

                int pos = esTrobaPosicio(lletra);

                if (pos < 0){ 
                    textXifrat += lletra; 
                    continue; 
                }

                if (pos >= ((lletres.length)-13)){
                    pos = (pos+13)%lletres.length; 
                } else {
                    pos += 13;
                }

                if (gran){
                    textXifrat += Character.toUpperCase(lletraXPosicio(pos)); 
                }else{
                    textXifrat += lletraXPosicio(pos);
                }
            }else {
                textXifrat += lletra; 
            }
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
    public static int esTrobaPosicioMin(char character){
        for (int i = 0; i < MIN.length; i++){
            char min = MIN[i];
            if (character == min){ 
                return i; 
            }
        }
        return -1; 
    }

    public static int esTrobaPosicioMaj(char character){
        for (int i = 0; i < MAJ.length; i++){
            char maj = MAJ[i];
            if (character == maj){ 
                return i; 
        }
        return -1; 
    }

    public static char lletraXPosicio(int pos){
        return lletres[pos];
    }
}
