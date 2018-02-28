package com.ifpb.cryptochat.controladores;

import com.ifpb.cryptochat.entidades.Usuario;
import com.ifpb.cryptochat.daos.UsuarioDao;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class ControladorUsuario implements Serializable {

    private Part foto;

    private Usuario usuario = new Usuario();

    HttpSession sessao;

    @EJB
    private UsuarioDao usuarioDao;

    public String cadastrar() throws IOException {
        if (usuarioDao.consultarPorEmail(usuario.getEmail()) != null) {
            mensagemErro("Cadastro", "Já existe um usuário cadastrado "
                    + "com o e-mail informado!");
        } else if (usuarioDao.consultarPorNickname(usuario.getNickname()) != null) {
            mensagemErro("Cadastro", "Já existe um usuário cadastrado "
                    + "com o nickname informado!");
        } else {
//            byte[] fotos = new ImageFile(foto.toString()).toBytes();
//            usuario.setFoto(fotos);

            byte[] arrayFoto = new byte[(int) foto.getSize()];
            foto.getInputStream().read(arrayFoto);
            usuario.setFoto(arrayFoto);
            usuarioDao.cadastrar(usuario);
            return "login.xhtml";
        }
        return null;
    }

    public String realizarlogin() {
        Usuario usuarioLogado = usuarioDao.consultarPorEmail(usuario.getEmail());

        if (usuarioLogado == null) {
            mensagemErro("Login", "O usuário informado não está cadastrado!");
            return null;
        } else {
            Usuario usuarioAutenticavel = usuarioDao
                    .autenticarUsuario(usuario.getEmail(), usuario.getSenha());
            if (usuarioAutenticavel == null) {
                mensagemErro("Login", "Os dados informados estão incorretos!");
                return null;
            } else {
                sessao = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(true);
                sessao.setAttribute("usuario", usuarioLogado);
                sessao.setAttribute("nome", usuarioLogado.getNome());
                sessao.setAttribute("foto", usuarioLogado.getFoto());
                return "pesquisa.xhtml";
            }
        }
    }

    public String realizarLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }

    public String getNomeSession() {
        sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        return sessao.getAttribute("nome").toString();
    }

    public String getFotoSession() {
        sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        return Base64.encode((byte[]) sessao.getAttribute("foto"));
    }

    public void mensagemErro(String tituloPagina, String conteudo) {
        FacesMessage mensagemDeErro = new FacesMessage(conteudo);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(tituloPagina, mensagemDeErro);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Part getFoto() {
        return foto;
    }

    public void setFoto(Part foto) {
        this.foto = foto;
    }

}
