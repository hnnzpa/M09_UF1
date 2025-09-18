public class Rot13 {

    static char[] lletres = "aàbcçdéèefghiíìjklmnñóòopqrstúùuvwxyz".toCharArray();
    public static void main(String[] args) {
        System.out.println(xifraRot13("Hola, que tal?"));
    }

    public static String xifraRot13(String cadena){
        char[] cadenaC = cadena.toCharArray(); 
        String textXifrat = ""; 
        boolean gran = false;

        for (int i = 0; i <= cadena.length(); i++){
            char lletra = cadenaC[i];
            if (lletra.isUpperCase){ //revisa si és maj per després convertir-la
                gran = true; 
            }
            int pos = posicio(lletra);
            if (pos == 0){ // si no és una lletra ho afegeix i continua
                textXifrat += lletra; 
                continue; 
            }
            //en cas de que ha de donar la volta
            if (pos > lletres.length-13){
                pos = (pos+13)%lletres.length; 
            }else {
                pos += 13; 
            }


            switch (gran){
                case true: 
                    textXifrat += lletraXPosicio(pos).toUpperCase; 
                case false:
                    textXifrat += lletraXPosicio(pos);
            }
        }
        return textXifrat; 
    }

    public static String desxifraRot13(String cadena){ return ""; }

    public static int posicio(char cT){
        for (int i = 0; i <= lletres.length; i++){
            char c = lletres[i];
            if (cT == c){
                return i+1; 
            } 
        }
        return 0; 
    }

    public static char lletraXPosicio(int pos){
        return lletres[pos];
    }
}
