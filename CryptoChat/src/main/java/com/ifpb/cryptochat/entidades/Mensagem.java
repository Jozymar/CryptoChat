package com.ifpb.cryptochat.entidades;

import com.ifpb.cryptochat.conversores.StringToArrayByte;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Mensagem implements Serializable {

    @Id
    @GeneratedValue()
    private int id;

    @Lob
    @Column(nullable = false)
    private byte[] corpoMensagem;

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

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", corpoMensagem=" + corpoMensagem
                + ", remetente=" + remetente + ", destinatario="
                + destinatario + '}';
    }

}
