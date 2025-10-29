package iticbcn.xifratge;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.InvalidKeyException;

public class XifradorAES implements Xifrador {
    public static final String ALGORITME_XIFRAT = "AES";  
    public static final String ALGORITME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    public static final int MIDA_IV = 16;
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        byte[] aesPassword;
        byte[] iv = generateIv();

        try {
            aesPassword = deriveKey(clau, iv, 256);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ClauNoSuportada(e.getLocalizedMessage());
        }

        SecretKeySpec keySpec = new SecretKeySpec(aesPassword, 0, 16, ALGORITME_XIFRAT);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        try {
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encryptedBytes = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
            byte[] finalBytes = new byte[MIDA_IV + encryptedBytes.length];
            System.arraycopy(iv, 0, finalBytes, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, finalBytes, MIDA_IV, encryptedBytes.length);

            return new TextXifrat(finalBytes);
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println("Error de configuración AES: " + e.getMessage());
            System.exit(1);
            return null;
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            System.err.println("Clave o parámetros AES inválidos: " + e.getMessage());
            System.exit(1);
            return null;
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            System.err.println("Error durante el cifrado AES: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat blvlMsgXifratTX, String clau) throws ClauNoSuportada {
        byte[] iv = new byte[MIDA_IV];
        byte[] blvlMsgXifrat = blvlMsgXifratTX.getBytes();
        System.arraycopy(blvlMsgXifratTX.getBytes(), 0, iv, 0, iv.length);

        byte[] aesPassword;
        try {
            aesPassword = deriveKey(clau, iv, 256);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ClauNoSuportada(e.getLocalizedMessage());
        }

        byte[] msg = new byte[blvlMsgXifrat.length - MIDA_IV];
        System.arraycopy(blvlMsgXifrat, MIDA_IV, msg, 0, msg.length);

        SecretKeySpec keySpec = new SecretKeySpec(aesPassword, 0, 16, ALGORITME_XIFRAT);

        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        try{
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] plaintext = cipher.doFinal(msg);

            return new String(plaintext, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println("Error de configuración AES: " + e.getMessage());
            System.exit(1);
            return null;
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            System.err.println("Clave o parámetros AES inválidos: " + e.getMessage());
            System.exit(1);
            return null;
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            System.err.println("Error durante el cifrado AES: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private byte[] generateIv() {
        byte[] ivBytes = new byte[MIDA_IV];
        secureRandom.nextBytes(ivBytes);
        return ivBytes;
    }

    private static byte[] deriveKey(String password, byte[] salt, int keySizeBits) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                100_000,
                keySizeBits
        );
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey key = skf.generateSecret(spec);
        return key.getEncoded();
    }
}
