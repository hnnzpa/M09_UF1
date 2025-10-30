package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(2048); 
        return generador.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher xifrat = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        xifrat.init(Cipher.ENCRYPT_MODE, clauPublica);
        return xifrat.doFinal(msg.getBytes("UTF-8"));
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher desxifrat = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        desxifrat.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] bytesDesxifrats = desxifrat.doFinal(msgXifrat);
        return new String(bytesDesxifrats, "UTF-8");
    }
}
