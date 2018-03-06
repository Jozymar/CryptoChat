package com.ifpb.cryptochat.interfaces;

import com.ifpb.cryptochat.entidades.ChavePrivada;
import java.security.NoSuchAlgorithmException;

public interface ChavePrivadaDao {

    public void persistirChave(ChavePrivada chavePrivada)
            throws NoSuchAlgorithmException;

    public ChavePrivada getChavePrivadaUsuario(int idUsuario);

}
