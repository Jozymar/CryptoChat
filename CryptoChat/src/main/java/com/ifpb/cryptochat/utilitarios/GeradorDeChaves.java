package com.ifpb.cryptochat.utilitarios;

import com.ifpb.cryptochat.interfaces.GeradorDeChavesImpl;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GeradorDeChaves implements GeradorDeChavesImpl {

    @Override
    public KeyPair gerarChaves() throws NoSuchAlgorithmException {
        // chaves de tamanho 2048 (padrão)
        final int lenChave = 2048;
        // criando uma instância do gerador de chaves com o RSA
        KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
        // inicializando com o tamanho de chave definido
        gerador.initialize(lenChave);
        // cria as chaves pública e privada
        return gerador.genKeyPair();
    }

    @Override
    public PublicKey getPublicKey() 
            throws NoSuchAlgorithmException {
        return this.gerarChaves().getPublic();
    }

    @Override
    public PrivateKey getPrivateKey() 
            throws NoSuchAlgorithmException{
        return this.gerarChaves().getPrivate();
    }

}
