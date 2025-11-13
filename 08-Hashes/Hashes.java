import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public static int npass = 0;
    public static final int MAX_LEN = 6;
    public static final String CHARSET = "abcdefABCDEF1234567890!";

    public static String getSHA512AmbSalt(String pw, String salt) throws Exception{
        String passwordConSalt = pw + salt;
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = passwordConSalt.getBytes();
        byte[] hashBytes = md.digest(bytes);
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hashBytes);
    }

    public static String getPBKDF2AmdSalt(String pw, String salt) throws Exception{
        int iteraciones = 65536;
        int longitudClave = 128;
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iteraciones, longitudClave);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hashBytes = factory.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hashBytes);
    }

    public static String getHash(String alg, String pw, String salt) throws Exception {
        if ("SHA-512".equalsIgnoreCase(alg)) {
            return getSHA512AmbSalt(pw, salt);
        } else if ("PBKDF2".equalsIgnoreCase(alg)) {
            return getPBKDF2AmdSalt(pw, salt);
        } else {
            throw new IllegalArgumentException("Algoritmo no soportado: " + alg);
        }
    }

    public static String pwTrobat(String alg, char[] intento, int ord, char lletra, int len, String hash, String salt) throws Exception{
        intento[ord] = lletra;
        if (ord != len - 1) return null;
        String psw = new String(intento, 0, len);
        npass++;
        String hashIntento = getHash(alg, psw, salt);
        if (hashIntento.equals(hash)) return psw;
        return null;
    }
    
    public String forcaBruta(String alg, String hashObjetivo, String salt) throws Exception {
        npass = 0;
        char[] intento = new char[MAX_LEN];
        int C = CHARSET.length();

        for (int len = MAX_LEN; len >= 1; len--) {
            int[] idx = new int[len];
            for (int p = 0; p < len; p++) intento[p] = CHARSET.charAt(0);
            while (true) {
                for (int p = 0; p < len; p++) intento[p] = CHARSET.charAt(idx[p]);
                String res = pwTrobat(alg, intento, len - 1, intento[len - 1], len, hashObjetivo, salt);
                if (res != null) return res;
                int pos = len - 1;
                while (pos >= 0) {
                    idx[pos]++;
                    if (idx[pos] < C) break;
                    idx[pos] = 0;
                    pos--;
                }
                if (pos < 0) break;
            }
        }
        return null;
    }


    public String getInterval(long t1, long t2){
        long milisegundos = t2 - t1;
        long segundos = milisegundos/1000;
        long minutos = segundos/60;

        milisegundos = milisegundos%1000;
        segundos = segundos%60;
        if (minutos>0){
            return minutos + " minutos " + segundos + " segundos " + milisegundos + " milisegundos";
        }else {
            return segundos + " segundos " +  milisegundos + " milisegundos";
        }
    }

    public static void main(String[] args) throws Exception{
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = {getSHA512AmbSalt(pw, salt), getPBKDF2AmdSalt(pw, salt)};
        String pwTrobat = null;
        String[] algoritmes = {"SHA-512", "PBKDF2"};
        for (int i = 0; i < aHashes.length; i++) {
            System.out.println("====================");
            System.out.printf("Algorisme:, %s\n", algoritmes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.println("---------------------");
            System.out.println("Inici forca bruta. --");
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algoritmes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.println("----------------------");
        }
    }
}
