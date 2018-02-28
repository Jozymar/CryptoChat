/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.cryptochat.interfaces;

public interface ProtectablePassword {
    
    /**
     * Método HASH MD5
     * @param key
     * @return byte[]
     * Captura o código MD5 da senha
     */
    public byte[] getMD5Password(String key);
    
    /**
     * Método Auxiliar
     * @param messageDigest
     * @return String
     * Captura os hexadecimais do array de bytes e guarda dentro de uma String
     */
    public String getHexadecimalOfBytes(byte[] messageDigest);
    
    /**
     * Método Englobador
     * @param key
     * @return String
     * Engloba os métodos getMD5Password e getHexadecimalOfBytes
     * para executar em background e retornar a senha protegida
     */
    public String getPasswordProtected(String key);

}
