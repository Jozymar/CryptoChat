package com.ifpb.cryptochat.entidades;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false, length = 80)
    private String senha;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;

    public Usuario() {
    }

    public Usuario(int id, String nome, String nickname, String email, String senha, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String fotoBase64() {
        return Base64.encode(foto);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", nickname=" + nickname + ", email=" + email + ", senha=" + senha + ", foto=" + foto + '}';
    }
}
