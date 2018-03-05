package com.ifpb.cryptochat.interfaces;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface CriptografiaRSAImpl {

    /**
     * Método para criptografar a mensagem Exemplo utilizado: criptografia feita
     * com a chave pública
     *
     * @param mensagem
     * @param chavePublica
     * @return
     * @throws Exception
     */
    public byte[] encriptarMensagem(byte[] mensagem, PublicKey chavePublica)
            throws Exception;

    /**
     * Método para descriptografar uma mensagem Exemplo utilizado:
     * descriptografia feita com chave privada
     *
     * @param mensagem
     * @param chavePrivada
     * @return
     * @throws Exception
     */
    public byte[] desencriptarMensagem(byte[] mensagem, PrivateKey chavePrivada)
            throws Exception;
}
