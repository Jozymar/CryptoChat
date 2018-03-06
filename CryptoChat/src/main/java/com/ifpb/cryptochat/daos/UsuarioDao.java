package com.ifpb.cryptochat.daos;

import com.ifpb.cryptochat.entidades.Usuario;
import com.ifpb.cryptochat.utilitarios.ProtetorDeSenha;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.ifpb.cryptochat.interfaces.ProtetorDeSenhaImpl;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsuarioDao {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    ProtetorDeSenhaImpl protectablePassword;

    public UsuarioDao() {
        this.protectablePassword = new ProtetorDeSenha();
    }

    public void cadastrar(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(protectablePassword.getPasswordProtected(usuario.getSenha()));
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

    public Usuario consultarPorNickname(String nickname, String nicknameSessao) {
        TypedQuery<Usuario> query = entityManager
                .createQuery("SELECT usuario FROM Usuario usuario "
                        + "WHERE usuario.nickname=:nickname "
                        + "AND usuario.nickname<>:nicknameSessao", Usuario.class);
        query.setParameter("nickname", nickname);
        query.setParameter("nicknameSessao", nicknameSessao);
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

    public List<Usuario> listarUsuarios(String nickname) {

        TypedQuery<Usuario> query = entityManager
                .createQuery("SELECT usuario FROM Usuario usuario "
                        + "WHERE usuario.nickname<>:nickname", Usuario.class);
        query.setParameter("nickname", nickname);
        List<Usuario> usuarios = query.getResultList();
        if (usuarios == null) {
            return new ArrayList<>();
        }
        List<Usuario> users = new ArrayList<>();
        for (Usuario user : usuarios) {
            users.add(user);

        }
        return users;
    }
}
