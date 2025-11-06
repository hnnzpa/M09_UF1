import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    int npass = 0; 

    public String getSHA512AmbSalt(String pw, String salt) throws Exception{
        String passwordConSalt = pw + salt;
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = passwordConSalt.getBytes();
        byte[] hashBytes = md.digest(bytes);

        //hexadecimal - mas leible
        HexFormat hex = HexFormat.of();
        String hash = hex.formatHex(hashBytes);

        return hash;
    }

    public String getPBKDF2AmdSalt(String pw, String salt) throws Exception{
        int iteraciones = 65536;
        int longitudClave = 512;

        PBEKeySpec spec = new PBEKeySpec(
            pw.toCharArray(),
            salt.getBytes(),
            iteraciones,
            longitudClave
        );

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

        byte[] hashBytes = factory.generateSecret(spec).getEncoded();

        HexFormat hex = HexFormat.of();
        String hash = hex.formatHex(hashBytes);

        return hash;
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception{
        String charset = "abcdefABCDEF1234567890!";
        npass = 0; 

        char[] intento = new char[6];

        for (int i = 0; i < charset.length(); i++) {
            intento[0] = charset.charAt(i);
            String password = new String(intento, 0, 1);
            npass++;

            String hashIntento = "";
            if (alg.equals("SHA-512")){
                hashIntento = getSHA512AmbSalt(password, salt);
            }else if (alg.equals("PBKDF2")){
                hashIntento = getPBKDF2AmdSalt(password, salt);
            }

            if (hashIntento.equals(hash)){
                return password;
            }
        }

        for (int i = 0; i < charset.length(); i++) {
            intento[0] = charset.charAt(i);
            for (int j = 0; j < charset.length(); j++) {
                intento[1] = charset.charAt(j);
                String password = new String(intento, 0, 2);
                npass++;

                String hashIntento = ""; 
                if ( alg.equals("SHA-512")){
                    hashIntento = getSHA512AmbSalt(password, salt);
                }else if (alg.equals("PBKDF2")){
                    hashIntento = getPBKDF2AmdSalt(password, salt);
                }

                if (hashIntento.equals(hash)){
                    return password;
                }
            }
        }

        for (int i = 0; i < charset.length(); i++) {
            intento[0] = charset.charAt(i);
            for (int j = 0; j < charset.length(); j++) {
                intento[1] = charset.charAt(j);
                for (int k = 0; k < charset.length(); k++) {
                    intento[2] = charset.charAt(k);
                    String password = new String(intento, 0, 3);
                    npass++;

                    String hashIntento = ""; 
                    if ( alg.equals("SHA-512")){
                        hashIntento = getSHA512AmbSalt(password, salt);
                    }else if (alg.equals("PBKDF2")){
                        hashIntento = getPBKDF2AmdSalt(password, salt);
                    }

                    if (hashIntento.equals(hash)){
                        return password;
                    }
                }
            }
        }

        for (int i = 0; i < charset.length(); i++) {
            intento[0] = charset.charAt(i);
            for (int j = 0; j < charset.length(); j++) {
                intento[1] = charset.charAt(j);
                for (int k = 0; k < charset.length(); k++) {
                    intento[2] = charset.charAt(k);
                    for (int l = 0; l < charset.length(); l++) {
                        intento[3] = charset.charAt(l);
                        String password = new String(intento, 0, 4);
                        npass++;

                        String hashIntento = ""; 
                        if ( alg.equals("SHA-512")){
                            hashIntento = getSHA512AmbSalt(password, salt);
                        }else if (alg.equals("PBKDF2")){
                            hashIntento = getPBKDF2AmdSalt(password, salt);
                        }

                        if (hashIntento.equals(hash)){
                            return password;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < charset.length(); i++) {
            intento[0] = charset.charAt(i);
            for (int j = 0; j < charset.length(); j++) {
                intento[1] = charset.charAt(j);
                for (int k = 0; k < charset.length(); k++) {
                    intento[2] = charset.charAt(k);
                    for (int l = 0; l < charset.length(); l++) {
                        intento[3] = charset.charAt(l);
                        for (int m = 0; m < charset.length(); m++) {
                            intento[4] = charset.charAt(m);
                            String password = new String(intento, 0, 5);
                            npass++;

                            String hashIntento = ""; 
                            if ( alg.equals("SHA-512")){
                                hashIntento = getSHA512AmbSalt(password, salt);
                            }else if (alg.equals("PBKDF2")){
                                hashIntento = getPBKDF2AmdSalt(password, salt);
                            }

                            if (hashIntento.equals(hash)){
                                return password;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < charset.length(); i++) {
            intento[0] = charset.charAt(i);
            for (int j = 0; j < charset.length(); j++) {
                intento[1] = charset.charAt(j);
                for (int k = 0; k < charset.length(); k++) {
                    intento[2] = charset.charAt(k);
                    for (int l = 0; l < charset.length(); l++) {
                        intento[3] = charset.charAt(l);
                        for (int m = 0; m < charset.length(); m++) {
                            intento[4] = charset.charAt(m);
                            for (int n = 0; n < charset.length(); n++) {
                                intento[5] = charset.charAt(n);
                                String password = new String(intento, 0, 6);
                                npass++;

                                String hashIntento = ""; 
                                if ( alg.equals("SHA-512")){
                                    hashIntento = getSHA512AmbSalt(password, salt);
                                }else if (alg.equals("PBKDF2")){
                                    hashIntento = getPBKDF2AmdSalt(password, salt);
                                }

                                if (hashIntento.equals(hash)){
                                    return password;
                                }
                            }
                        }
                    }
                }
            }
        }

        return null; 

    }

    public String getInterval(long t1, long t2){
        long milisegundos = t2 - t1;

        long segundos = milisegundos/1000;

        long minutos = segundos/60;
        segundos = segundos%60;

        if (minutos>0){
            return minutos + " minutos " + segundos + " segundos ";
        }else {
            return segundos + " segundos ";
        }
    }
    public static void main(String[] args) throws Exception{
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = {h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmdSalt(pw, salt)};
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
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.println("----------------------");
        }
    }
}
