package com.ifpb.edu.controladores;

import com.ifpb.edu.classes.Usuario;
import com.ifpb.edu.daos.UsuarioDao;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControladorDeBusca implements Serializable {

    @EJB
    private UsuarioDao usuarioDao;

    private boolean resultadoDaBusca;

    private Usuario usuario = new Usuario();

    public String buscarUsuarioPorNome() {
        Usuario usuarioBuscado = usuarioDao.consultarPorNome(usuario.getNome());
        System.out.println(usuario.getNome());
        System.out.println(usuarioBuscado);
        if (usuarioBuscado != null) {
            usuario = usuarioBuscado;
            System.out.println("Entrou Aqui");
            resultadoDaBusca = true;
            return "pesquisa.xhtml";
        } else {
            System.out.println("Não");
            resultadoDaBusca = false;
            return "pesquisa.xhtml";
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
