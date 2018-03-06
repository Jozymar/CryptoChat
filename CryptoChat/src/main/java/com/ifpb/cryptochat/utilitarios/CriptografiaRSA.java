package com.ifpb.cryptochat.utilitarios;

import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class CriptografiaRSA {
    
    /**
     * Método para criptografar a mensagem Exemplo utilizado: criptografia feita
     * com a chave pública
     *
     * @param mensagem
     * @param chavePublica
     * @return
     * @throws Exception
     */
    public static  byte[] encriptarMensagem(byte[] mensagem, PublicKey chavePublica)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
        return cipher.doFinal(mensagem);
    }
    
    /**
     * Método para descriptografar uma mensagem Exemplo utilizado:
     * descriptografia feita com chave privada
     *
     * @param mensagem
     * @param chavePrivada
     * @return
     * @throws Exception
     */
    public static byte[] desencriptarMensagem(byte[] mensagem, PrivateKey chavePrivada)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
        return cipher.doFinal(mensagem);
    }

}
