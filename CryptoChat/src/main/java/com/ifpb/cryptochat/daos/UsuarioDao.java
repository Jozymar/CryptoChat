package com.ifpb.cryptochat.daos;

import com.ifpb.cryptochat.entidades.Usuario;
import com.ifpb.cryptochat.interfaces.GeradorDeChavesImpl;
import com.ifpb.cryptochat.utilitarios.ProtetorDeSenha;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.ifpb.cryptochat.interfaces.ProtetorDeSenhaImpl;
import com.ifpb.cryptochat.utilitarios.GeradorDeChaves;
import java.security.NoSuchAlgorithmException;

@Stateless
public class UsuarioDao {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    ProtetorDeSenhaImpl protectablePassword;
    GeradorDeChavesImpl geradorDeChaves;

    public UsuarioDao() {
        this.protectablePassword = new ProtetorDeSenha();
        this.geradorDeChaves = new GeradorDeChaves();
    }

    public void cadastrar(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(protectablePassword
                .getPasswordProtected(usuario.getSenha()));
        usuario.setPublicKey(geradorDeChaves.getPublicKey());
        entityManager.persist(usuario);
    }

    public Usuario consultarPorNickname(String nickname) {
        TypedQuery<Usuario> query = entityManager
                .createQuery("SELECT usuario FROM Usuario usuario "
                        + "WHERE usuario.nickname=:nickname", Usuario.class);
        query.setParameter("nickname", nickname);

        Optional<Usuario> usuario = query.getResultList().stream().findFirst();

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null;
        }
    }

    public Usuario consultarPorEmail(String email) {
        TypedQuery<Usuario> query = entityManager
                .createQuery("SELECT usuario FROM Usuario usuario "
                        + "WHERE usuario.email=:email", Usuario.class);
        query.setParameter("email", email);

        Optional<Usuario> usuario = query.getResultList().stream().findFirst();

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null;
        }
    }

    public Usuario consultarPorNome(String nome) {
        TypedQuery<Usuario> query = entityManager
                .createQuery("SELECT usuario FROM Usuario usuario "
                        + "WHERE usuario.nome=:nome", Usuario.class);
        query.setParameter("nome", nome);

        Optional<Usuario> usuario = query.getResultList().stream().findFirst();

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null;
        }
    }

    public Usuario autenticarUsuario(String email, String senha) {
        TypedQuery<Usuario> query = entityManager
                .createQuery("SELECT u FROM Usuario u WHERE u.email=:email "
                        + "AND u.senha=:senha", Usuario.class);
        query.setParameter("email", email);
        query.setParameter("senha", protectablePassword.getPasswordProtected(senha));

        Optional<Usuario> usuario = query.getResultList().stream().findFirst();

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null;
        }

    }

    public void atualizarUsuario(Usuario novoEstado) {
        entityManager.merge(novoEstado);
    }

}
