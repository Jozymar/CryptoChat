package com.ifpb.cryptochat.controladores;

import com.ifpb.cryptochat.entidades.Usuario;
import com.ifpb.cryptochat.daos.UsuarioDao;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControladorDeBusca implements Serializable {

    @EJB
    private UsuarioDao usuarioDao;

    private boolean resultadoDaBusca;

    private Usuario usuario = new Usuario();

    public String buscarUsuarioPorNickname() {
        Usuario usuarioBuscado = usuarioDao.consultarPorNickname(usuario.getNickname());
        if (usuarioBuscado != null) {
            usuario = usuarioBuscado;
            resultadoDaBusca = true;
            return "faces/pesquisa.xhtml";
        } else {
            resultadoDaBusca = false;
            return "faces/pesquisa.xhtml";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isResultadoDaBusca() {
        return resultadoDaBusca;
    }

    public void setResultadoDaBusca(boolean resultadoDaBusca) {
        this.resultadoDaBusca = resultadoDaBusca;
    }
}
