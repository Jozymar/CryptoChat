package com.ifpb.cryptochat.utilitarios;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GeradorDeChaves {
    
    public static KeyPair gerarChaves() throws NoSuchAlgorithmException {
        // chaves de tamanho 2048 (padrão)
        final int lenChave = 2048;
        // criando uma instância do gerador de chaves com o RSA
        KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
        // inicializando com o tamanho de chave definido
        gerador.initialize(lenChave);
        // cria as chaves pública e privada
        return gerador.genKeyPair();
    }

}
