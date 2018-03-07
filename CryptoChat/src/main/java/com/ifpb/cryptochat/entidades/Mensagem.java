package com.ifpb.cryptochat.entidades;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Mensagem implements Serializable {

    @Id
    @GeneratedValue()
    private int id;

    @Lob
    @Column(nullable = false)
    @Basic(fetch = FetchType.EAGER)
    private byte[] corpoMensagem;

    @Transient
    private String corpoMensagemPlano;

    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario remetente;

    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario destinatario;

    public Mensagem() {

    }

    public Mensagem(byte[] corpoMensagem, Usuario remetente,
            Usuario destinatario) {
        this.corpoMensagem = corpoMensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getCorpoMensagem() {
        return corpoMensagem;
    }

    public void setCorpoMensagem(byte[] corpoMensagem) {
        this.corpoMensagem = corpoMensagem;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getCorpoMensagemPlano() {
        return corpoMensagemPlano;
    }

    public void setCorpoMensagemPlano(String corpoMensagemPlano) {
        this.corpoMensagemPlano = corpoMensagemPlano;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", corpoMensagem="
                + Arrays.toString(corpoMensagem) + ", corpoMensagemPlano="
                + corpoMensagemPlano + ", remetente="
                + remetente + ", destinatario="
                + destinatario + '}';
    }

}
