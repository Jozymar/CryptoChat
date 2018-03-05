package com.ifpb.cryptochat.entidades;

import java.io.Serializable;
import java.security.PrivateKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChavePrivada implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private PrivateKey chavePrivada;

    @Column(nullable = false)
    private int idUsuario;

    public ChavePrivada() {

    }

    public ChavePrivada(PrivateKey chavePrivada, int idUsuario) {
        this.chavePrivada = chavePrivada;
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
        return "ChavePrivada{" + "id=" + id + ", chavePrivada="
                + chavePrivada + ", idUsuario=" + idUsuario + '}';
    }

}
