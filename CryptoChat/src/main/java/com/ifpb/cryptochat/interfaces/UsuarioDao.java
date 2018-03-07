package com.ifpb.cryptochat.interfaces;

import com.ifpb.cryptochat.entidades.Usuario;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UsuarioDao {

    public void cadastrar(Usuario usuario) throws NoSuchAlgorithmException;

    public Usuario consultarPorNickname(String nickname);

    public Usuario consultarPorNickname(String nickname, String nicknameSessao);

    public Usuario consultarPorEmail(String email);

    public Usuario consultarPorNome(String nome);

    public Usuario autenticarUsuario(String email, String senha);

    public void atualizarUsuario(Usuario novoEstado);

    public List<Usuario> listarUsuarios(String nickname);
}
