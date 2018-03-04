package com.ifpb.cryptochat.entidades;

import com.ifpb.cryptochat.interfaces.GeradorDeChavesImpl;
import com.ifpb.cryptochat.utilitarios.GeradorDeChaves;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class ChavePrivada implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Lob
    @Column(nullable = false)
    private PrivateKey chavePrivada;

    private int idUsuario;

    @Transient
    private final GeradorDeChavesImpl geradorDeChaves = new GeradorDeChaves();

    public ChavePrivada() {

    }

    public ChavePrivada(int idUsuario) throws NoSuchAlgorithmException {
        this.chavePrivada = geradorDeChaves.getPrivateKey();
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PrivateKey getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(PrivateKey chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "ChavePrivada{" + "id=" + id + ", chavePrivada=" + chavePrivada
                + ", idUsuario=" + idUsuario + ", geradorDeChaves="
                + geradorDeChaves + '}';
    }

}
