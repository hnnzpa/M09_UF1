package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    static final char[] MIN = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    static final char[] MAJ = "aàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toUpperCase().toCharArray();

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            int desplazamiento = Integer.parseInt(clau);
            if (desplazamiento < 0 || desplazamiento > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            String resultado = xifraRotX(msg, desplazamiento);
            return new TextXifrat(resultado.getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (xifrat == null) return null;
        
        try {
            int desplazamiento = Integer.parseInt(clau);
            if (desplazamiento < 0 || desplazamiento > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            String texto = new String(xifrat.getBytes());
            return desxifraRotX(texto, desplazamiento);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    // Los métodos auxiliares se mantienen igual...
    public String xifraRotX(String cadena, int desplaçament){
        char[] cadenaC = cadena.toCharArray(); 
        StringBuilder textXifrat = new StringBuilder(); 
        
        for (char lletra : cadenaC) {
            textXifrat.append(xifraC(lletra, desplaçament)); 
        }
        return textXifrat.toString(); 
    }

    public char xifraC(char lletra, int n){
        if (esTrobaPosicioArray(lletra, MIN) >= 0){
            return lletraXPosicio(xifraPosicio(lletra, MIN, n), MIN);  
        } else if (esTrobaPosicioArray(lletra, MAJ) >= 0){
            return lletraXPosicio(xifraPosicio(lletra, MAJ, n), MAJ);
        }
        return lletra;                 
    }

    public int xifraPosicio(char lletra, char[] array, int despl){
        int pos = esTrobaPosicioArray(lletra, array);
        return (pos + despl) % array.length; 
    }
    
    public String desxifraRotX(String cadena, int desplaçament){ 
        char[] cadenaC = cadena.toCharArray(); 
        StringBuilder textDesxifrat = new StringBuilder(); 

        for (char lletra : cadenaC) {
            textDesxifrat.append(desxifraC(lletra, desplaçament));
        }
        return textDesxifrat.toString(); 
    }

    public char desxifraC(char lletra, int n){
        if (esTrobaPosicioArray(lletra, MIN) >= 0){
            return lletraXPosicio(desxifraPosicio(lletra, MIN, n), MIN);  
        } else if (esTrobaPosicioArray(lletra, MAJ) >= 0){
            return lletraXPosicio(desxifraPosicio(lletra, MAJ, n), MAJ);
        }
        return lletra;                 
    }
    
    public int desxifraPosicio(char lletra, char[] array, int despl){
        int pos = esTrobaPosicioArray(lletra, array);
        pos = (pos - despl) % array.length;
        if (pos < 0) {
            pos += array.length;
        }
        return pos; 
    }
    
    public String[] forcaBrutaX(String cadenaXifrada){
        String[] cadenesDesxifrades = new String[MIN.length];
        for (int i = 0; i < MIN.length; i++){
            cadenesDesxifrades[i] = desxifraRotX(cadenaXifrada, i);
        }
        return cadenesDesxifrades;
    }

    public int esTrobaPosicioArray(char character, char[] array){
        for (int i = 0; i < array.length; i++){
            if (character == array[i]){ 
                return i; 
            }
        }
        return -1; 
    }
    
    public char lletraXPosicio(int pos, char[] array){
        return array[pos];
    }

    public void imprimirStrArray(String[] array){
        for (int i = 0; i < array.length; i++){
            System.out.printf("(%d) -> %s%n", i, array[i]);
        }
    }
}