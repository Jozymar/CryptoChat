package com.ifpb.cryptochat.interfaces;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public interface GeradorDeChavesImpl {

    /**
     * Gerando as chaves pública e privada Método RSA
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public KeyPair gerarChaves() 
            throws NoSuchAlgorithmException;

}
