package com.ifpb.cryptochat.utilitarios;

import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import com.ifpb.cryptochat.interfaces.CriptografiaRSAImpl;
import java.nio.charset.StandardCharsets;

public class CriptografiaRSA implements CriptografiaRSAImpl {

    @Override
    public byte[] encriptarMensagem(byte[] mensagem, PublicKey chavePublica)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, chavePublica);

        return cipher.doFinal(mensagem);
    }

    @Override
    public String desencriptarMensagem(byte[] mensagem, PrivateKey chavePrivada)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, chavePrivada);

        return new String(cipher.doFinal(mensagem), StandardCharsets.UTF_8);
    }

}
