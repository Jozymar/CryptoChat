package com.ifpb.cryptochat.controladores;

import com.ifpb.cryptochat.entidades.Usuario;
import com.ifpb.cryptochat.entidades.ChavePrivada;
import com.ifpb.cryptochat.interfaces.ChavePrivadaDao;
import com.ifpb.cryptochat.interfaces.UsuarioDao;
import com.ifpb.cryptochat.utilitarios.GeradorDeChaves;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
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
    private Usuario usuario;
    private HttpSession sessao;

    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private ChavePrivadaDao chavePrivadaDao;

    @PostConstruct
    public void instanceObjects() {
        this.usuario = new Usuario();
    }

    public String cadastrar() throws IOException, NoSuchAlgorithmException {
        if (usuarioDao.consultarPorEmail(usuario.getEmail()) != null) {
            mensagemErro("Cadastro", "Já existe um usuário cadastrado "
                    + "com o e-mail informado!");
        } else if (usuarioDao.consultarPorNickname(usuario.getNickname()) != null) {
            mensagemErro("Cadastro", "Já existe um usuário cadastrado "
                    + "com o nickname informado!");
        } else {
            byte[] arrayFoto = new byte[(int) foto.getSize()];
            foto.getInputStream().read(arrayFoto);
            usuario.setFoto(arrayFoto);

            KeyPair parChaves = GeradorDeChaves.gerarChaves();
            //seta chave publica no usuario
            usuario.setPublicKey(parChaves.getPublic());
            //persisite o usuario no banco
            usuarioDao.cadastrar(usuario);

            //adiciona o id do usuario e a chave privada
            ChavePrivada chavePrivada = new ChavePrivada(
                    parChaves.getPrivate(), usuario.getId());
            //persiste a entidade ChavePrivada no banco
            chavePrivadaDao.persistirChave(chavePrivada);

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
