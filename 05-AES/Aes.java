import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class Aes {
    public static final String ALGORITME_XIFRAT = "AES";  
    public static final String ALGORITME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    public static final int MIDA_IV = 16;
    public static final String CLAU = "LaClauSecteraQueVulguis";

    public static void main(String[] args){
        String[] msgs = {"Lorem ipsum dicet",
                         "Hola Andrés cómo está tu cuñado",
                         "Agora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try{
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            }catch(Exception e){
                System.out.println("Error de xifrat: "
                    + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msd: " + msg);
            System.out.println("Enc: " + Base64.getEncoder().encodeToString(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception{
        byte[] plaintext = msg.getBytes("UTF-8");

        SecretKeySpec keySpec = new SecretKeySpec(clau.getBytes(), 0, 16, ALGORITME_XIFRAT);

        byte[] iv = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext);

        byte[] finalBytes = new byte[MIDA_IV + encryptedBytes.length];
        System.arraycopy(iv, 0, finalBytes, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, finalBytes, MIDA_IV, encryptedBytes.length);

        return finalBytes;
    }

    public static String desxifraAES(byte[] blvlMsgXifrat, String clau) throws Exception{
        byte[] iv = new byte[MIDA_IV];
        System.arraycopy(blvlMsgXifrat, 0, iv, 0, MIDA_IV);

        byte[] msg = new byte[blvlMsgXifrat.length - MIDA_IV];
        System.arraycopy(blvlMsgXifrat, MIDA_IV, msg, 0, msg.length);

        SecretKeySpec keySpec = new SecretKeySpec(clau.getBytes(), 0, 16, ALGORITME_XIFRAT);

        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] plaintext = cipher.doFinal(msg);

        return new String(plaintext, "UTF-8");
    }
}
