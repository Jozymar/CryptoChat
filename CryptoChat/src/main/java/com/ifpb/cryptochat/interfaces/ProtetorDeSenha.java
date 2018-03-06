package com.ifpb.cryptochat.interfaces;

public interface ProtetorDeSenha {
    
    /**
     * Método Englobador
     * @param key
     * @return String
     * Engloba os métodos getMD5Password e getHexadecimalOfBytes
     * para executar em background e retornar a senha protegida
     */
    public String getPasswordProtected(String key);

}
