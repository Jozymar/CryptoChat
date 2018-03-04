package com.ifpb.cryptochat.controladores;

import com.ifpb.cryptochat.daos.ChavePrivadaDao;
import com.ifpb.cryptochat.daos.MensagemDao;
import com.ifpb.cryptochat.entidades.Usuario;
import com.ifpb.cryptochat.daos.UsuarioDao;
import com.ifpb.cryptochat.entidades.Mensagem;
import com.ifpb.cryptochat.interfaces.CriptografiaRSAImpl;
import com.ifpb.cryptochat.utilitarios.CriptografiaRSA;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class ControladorMensagem implements Serializable {

    @EJB
    private UsuarioDao usuarioDao;
    @EJB
    private MensagemDao mensagemDao;
    @EJB
    private ChavePrivadaDao chavePrivadaDao;

    private Usuario remetente;
    private Usuario destinatario;
    private Mensagem mensagem;
    private boolean resultadoDaBusca;
    private CriptografiaRSAImpl criptografiaRSA;

    @PostConstruct
    public void instanceObjects() {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        this.remetente = (Usuario) sessao.getAttribute("usuario");
        this.destinatario = new Usuario();
        this.mensagem = new Mensagem();
        this.criptografiaRSA = new CriptografiaRSA();
    }

    public String buscarUsuarioPorNickname() {
        Usuario usuarioBuscado = usuarioDao
                .consultarPorNickname(destinatario.getNickname());

        if (usuarioBuscado != null) {
            destinatario = usuarioBuscado;
            resultadoDaBusca = true;
            return "faces/pesquisa.xhtml";
        } else {
            resultadoDaBusca = false;
            return "faces/pesquisa.xhtml";
        }
    }

    public String enviarMensagem() throws Exception {
        //seta o remetente e o destinatario na mensagem
        mensagem.setRemetente(remetente);
        mensagem.setDestinatario(destinatario);

        //criptografa a mensagem
        int idDestinatario = destinatario.getId();
        PublicKey publicKeyDestinatrio = destinatario.getPublicKey();
        byte[] mensagemParaCriptografia = mensagem.getCorpoMensagem();
        mensagem.setCorpoMensagem(criptografiaRSA.
                encriptarMensagem(mensagemParaCriptografia, publicKeyDestinatrio));

        //Busca o usuário para atualização
        Usuario usuarioParaAtualizar = usuarioDao
                .consultarPorEmail(remetente.getEmail());
        //Seta mensagem no usuario buscado
        usuarioParaAtualizar.addMensagem(mensagem);
        //Envia a mensagem
        mensagemDao.enviarMensagem(mensagem);
        //Atualiza o usuario setando a mensagem
        usuarioDao.atualizarUsuario(usuarioParaAtualizar);
        mensagem = new Mensagem();
        return null;
    }

    public List<String> getHistoricoMensagensPlano() throws Exception {
        List<byte[]> mensagensEncriptadas = mensagemDao
                .getHistoricoIndividualEncriptadoUsuario(remetente, destinatario);
        PrivateKey chavePrivada = chavePrivadaDao
                .getChavePrivadaUsuario(destinatario.getId());
        List<String> mensagensDesencriptadas = mensagemDao
                .getHistoricoIndividualDesencriptadoUsuario(mensagensEncriptadas, chavePrivada);
        return mensagensDesencriptadas;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public boolean isResultadoDaBusca() {
        return resultadoDaBusca;
    }

    public void setResultadoDaBusca(boolean resultadoDaBusca) {
        this.resultadoDaBusca = resultadoDaBusca;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public MensagemDao getMensagemDao() {
        return mensagemDao;
    }

    public void setMensagemDao(MensagemDao mensagemDao) {
        this.mensagemDao = mensagemDao;
    }

}
