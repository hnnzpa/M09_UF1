public class Rot13{

    static char[] lletres = "aàbcçdéèefghiíìjklmnñóòopqrstúùuvwxyz".toCharArray();
    public static void main(String[] args) {
        String cadena = "Hola, que tal?";
        String cadenaXifrada = xifraRot13(cadena); 
        System.out.println("El text xifrat: " + cadenaXifrada);
        String desxifrat = desxifraRot13(cadenaXifrada); 
        System.out.println("Cadena desxifrada a partir del text xifrat: " + desxifrat);
        
    }

    public static String xifraRot13(String cadena){
        char[] cadenaC = cadena.toCharArray(); 
        String textXifrat = ""; 
        

        for (int i = 0; i <= cadena.length()-1; i++){
            char lletra = cadenaC[i];
            boolean gran = false;
            if (Character.isLetter(lletra)){

                if (Character.isUpperCase(lletra)){ //revisa si és maj per després convertir-la
                    gran = true; 
                }

                lletra = Character.toLowerCase(lletra);
                int pos = posicio(lletra);

                if (pos == -1){ // si no ésta en la array ho afegeix i continua
                    textXifrat += lletra; 
                    continue; 
                }

                //en cas de que ha de donar la volta
                if (pos >= ((lletres.length)-13)){
                    pos = (pos+13)%lletres.length; 
                    
                } else {
                    pos += 13;
                }

                // mirar si era maj i afegir la lletra corresponent
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
    

    public static String desxifraRot13(String cadena){ 
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

    public static int posicio(char cT){
        for (int i = 0; i <= lletres.length-1; i++){
            char c = lletres[i];
            if (cT == c){
                return i; 
            } 
        }
        return -1; 
    }

    public static char lletraXPosicio(int pos){
        return lletres[pos];
    }
}
