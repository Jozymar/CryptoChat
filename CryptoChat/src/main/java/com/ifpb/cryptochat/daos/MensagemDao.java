package com.ifpb.cryptochat.daos;

import com.ifpb.cryptochat.entidades.Mensagem;
import com.ifpb.cryptochat.entidades.Usuario;
import com.ifpb.cryptochat.interfaces.CriptografiaRSAImpl;
import com.ifpb.cryptochat.utilitarios.CriptografiaRSA;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MensagemDao {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    public void enviarMensagem(Mensagem mensagem) {
        entityManager.persist(mensagem);
    }

    public List<byte[]> getHistoricoIndividualEncriptadoUsuario(
            Usuario remetente, Usuario destinatario) {
        String querySql = "SELECT m.corpoMensagem FROM Mensagem m "
                + "WHERE m.remetente= :rem AND m.destinatario= :dest";
        TypedQuery<byte[]> createQuery = entityManager.createQuery(querySql, byte[].class);
        createQuery.setParameter("rem", remetente);
        createQuery.setParameter("dest", destinatario);
        return createQuery.getResultList();
    }

    public List<String> getHistoricoIndividualDesencriptadoUsuario(
            List<byte[]> mensagensEncripadas, PrivateKey chavePrivada)
            throws Exception {
        CriptografiaRSAImpl criptografiaRSA = new CriptografiaRSA();
        List<String> mensagensDesencriptadas = new ArrayList<>();
        for (byte[] mensagens : mensagensEncripadas) {
            String msgDesencriptada = criptografiaRSA
                    .desencriptarMensagem(mensagens, chavePrivada);
            mensagensDesencriptadas.add(msgDesencriptada);
        }
        return mensagensDesencriptadas;
    }

}
