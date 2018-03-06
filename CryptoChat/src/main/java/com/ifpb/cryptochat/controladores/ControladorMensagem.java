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
    private String mensagemUI;
    private boolean resultadoDaBusca;
    private CriptografiaRSA criptografiaRSA;

    @PostConstruct
    public void instanceObjects() {

        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        this.remetente = (Usuario) sessao.getAttribute("usuario");
        this.destinatario = new Usuario();
        this.mensagem = new Mensagem();
        this.criptografiaRSA = new CriptografiaRSA();
    }

    public String buscarUsuarioPorNickname() throws Exception {
        Usuario usuarioBuscado = usuarioDao
                .consultarPorNickname(destinatario.getNickname(),
                        remetente.getNickname());

        if (usuarioBuscado != null) {
            destinatario = usuarioBuscado;
            resultadoDaBusca = true;
            return "faces/pesquisa.xhtml";
        } else {
            resultadoDaBusca = false;
            return "faces/pesquisa.xhtml";
        }

    }

    public String buscarUsuarioNickname(String nickname) throws Exception {
        Usuario usuarioBuscado = usuarioDao
                .consultarPorNickname(nickname,
                        remetente.getNickname());
        if (usuarioBuscado != null) {
            destinatario = usuarioBuscado;
            return "faces/mensagem.xhtml";
        }
        return null;
    }

    public List<Usuario> listarUsuarios(String nickname) throws Exception {
        return usuarioDao.listarUsuarios(nickname);

    }

    public String enviarMensagem() throws Exception {

        //seta o remetente e o destinatario na mensagem
        mensagem.setRemetente(remetente);
        mensagem.setDestinatario(destinatario);

        //criptografa a mensagem
        PublicKey chavePublicaDestinatrio = destinatario.getPublicKey();
        byte[] mensagemParaCriptografia = criptografiaRSA
                .encriptarMensagem(mensagemUI.getBytes(), chavePublicaDestinatrio);
        mensagem.setCorpoMensagem(mensagemParaCriptografia);

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
        mensagemUI = null;
        return null;
    }

    public List<String> historicoMensagensPlano() throws Exception {
        PrivateKey chavePrivada = chavePrivadaDao
                .getChavePrivadaUsuario(remetente.getId()).getChavePrivada();

        List<String> retorno = mensagemDao.getHistoricoIndividualDesencriptadoUsuario(
                remetente, destinatario, chavePrivada);
        return retorno;
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

    public ChavePrivadaDao getChavePrivadaDao() {
        return chavePrivadaDao;
    }

    public void setChavePrivadaDao(ChavePrivadaDao chavePrivadaDao) {
        this.chavePrivadaDao = chavePrivadaDao;
    }

    public CriptografiaRSA getCriptografiaRSA() {
        return criptografiaRSA;
    }

    public void setCriptografiaRSA(CriptografiaRSA criptografiaRSA) {
        this.criptografiaRSA = criptografiaRSA;
    }

    public String getMensagemUI() {
        return mensagemUI;
    }

    public void setMensagemUI(String mensagemUI) {
        this.mensagemUI = mensagemUI;
    }

}
