package com.ifpb.cryptochat.interfaces;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface GeradorDeChavesImpl {

    /**
     * Gerando as chaves pública e privada Método RSA
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public KeyPair gerarChaves() 
            throws NoSuchAlgorithmException;

    public PublicKey getPublicKey() 
            throws NoSuchAlgorithmException;

    public PrivateKey getPrivateKey() 
            throws NoSuchAlgorithmException;

}
