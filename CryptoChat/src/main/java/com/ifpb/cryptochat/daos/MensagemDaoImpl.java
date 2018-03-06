package com.ifpb.cryptochat.daos;

import com.ifpb.cryptochat.entidades.ChavePrivada;
import com.ifpb.cryptochat.entidades.Mensagem;
import com.ifpb.cryptochat.entidades.Usuario;
import com.ifpb.cryptochat.interfaces.MensagemDao;
import com.ifpb.cryptochat.utilitarios.CriptografiaRSA;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MensagemDaoImpl implements MensagemDao {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    @Override
    public void enviarMensagem(Mensagem mensagem, PublicKey chavePublicaDestinatario) {
        try {
            byte[] mensagemParaEncriptar = mensagem.getCorpoMensagem();
            byte[] mensagemEncriptada = CriptografiaRSA
                    .encriptarMensagem(mensagemParaEncriptar, chavePublicaDestinatario);
            mensagem.setCorpoMensagem(mensagemEncriptada);
        } catch (Exception ex) {
            Logger.getLogger(MensagemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        entityManager.persist(mensagem);
    }

//    @Override
//    public List<String> getHistoricoIndividualDesencriptadoUsuario(
//            Usuario remetente, Usuario destinatario, PrivateKey chavePrivada)
//            throws Exception {
//        String querySql = "SELECT m.corpoMensagem FROM Mensagem m "
//                + "WHERE m.remetente= :dest AND m.destinatario= :rem";
//
//        TypedQuery<byte[]> createQuery = entityManager
//                .createQuery(querySql, byte[].class);
//        createQuery.setParameter("rem", remetente);
//        createQuery.setParameter("dest", destinatario);
//
//        List<byte[]> mensagensEncriptadas = createQuery.getResultList();
//        if (mensagensEncriptadas == null) {
//            return new ArrayList<>();
//        }
//
//        List<String> mensagensDesencriptadas = new ArrayList<>();
//        for (byte[] mensagem : mensagensEncriptadas) {
//            byte[] msgDesencriptada = CriptografiaRSA.
//                    desencriptarMensagem(mensagem, chavePrivada);
//            String mensagemDesencriptada = new String(msgDesencriptada);
//            mensagensDesencriptadas.add(mensagemDesencriptada);
//        }
//        return mensagensDesencriptadas;
//    }

    @Override
    public List<String> getHistoricoConversas(Usuario remetente,
            Usuario destinatario, ChavePrivada chavePrivadaRem,
            ChavePrivada chavePrivadaDest)
            throws Exception {

        String querySql = "SELECT m FROM Mensagem m WHERE (m.remetente=:rem "
                + "AND m.destinatario=:dest) OR (m.remetente=:dest "
                + "AND m.destinatario=:rem)";

        TypedQuery<Mensagem> createQuery = entityManager
                .createQuery(querySql, Mensagem.class);
        createQuery.setParameter("rem", remetente);
        createQuery.setParameter("dest", destinatario);

        List<Mensagem> mensagens = createQuery.getResultList();

        if (mensagens == null) {
            return new ArrayList<>();
        }

        List<String> mensagensDesencriptadas = new ArrayList<>();

        for (Mensagem mensagem : mensagens) {
            String mensagemDesencriptada;
            if (mensagem.getDestinatario().getId() == chavePrivadaRem.getId()) {
                mensagemDesencriptada = new String(CriptografiaRSA
                        .desencriptarMensagem(mensagem.getCorpoMensagem(),
                                chavePrivadaRem.getChavePrivada()));
            } else {
                mensagemDesencriptada = new String(CriptografiaRSA
                        .desencriptarMensagem(mensagem.getCorpoMensagem(),
                                chavePrivadaDest.getChavePrivada()));
            }
            mensagensDesencriptadas.add(mensagemDesencriptada);

        }
        return mensagensDesencriptadas;
    }

}
