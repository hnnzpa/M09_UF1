public class Rot13{

    static char[] lletres = "aàbcçdéèefghiíìjklmnñóòopqrstúùuvwxyz".toCharArray();
    public static void main(String[] args) {
        System.out.println(xifraRot13("Hola, que tal?"));
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
    

    public static String desxifraRot13(String cadena){ return ""; }

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
