package com.ifpb.cryptochat.entidades;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @Column(nullable = false, length = 32)
    private String senha;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;

    @OneToMany
    private List<Mensagem> mensagens;

    @Lob
    private PublicKey publicKey;

    public Usuario() {
        this.mensagens = new ArrayList<>();
    }

    public Usuario(String nome, String nickname, String email,
            String senha, byte[] foto, PublicKey publicKey) {
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.publicKey = publicKey;
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

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagem(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public boolean addMensagem(Mensagem mensagem) {
        return mensagens.add(mensagem);
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", nickname="
                + nickname + ", email=" + email + ", senha=" + senha
                + ", foto=" + Arrays.toString(foto) + ", mensagens=" + mensagens
                + ", publicKey=" + publicKey + '}';
    }

}
