package com.ifpb.cryptochat.daos;

import com.ifpb.cryptochat.entidades.Mensagem;
import com.ifpb.cryptochat.entidades.Usuario;
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

    public List<String> getHistoricoIndividualDesencriptadoUsuario(
            Usuario remetente, Usuario destinatario, PrivateKey chavePrivada) 
                throws Exception {
        
        String querySql = "SELECT m.corpoMensagem FROM Mensagem m " 
                + "WHERE m.remetente= :dest AND m.destinatario= :rem";

        TypedQuery<byte[]> createQuery = entityManager.createQuery(querySql, byte[].class);
        createQuery.setParameter("rem", remetente);
        createQuery.setParameter("dest", destinatario);

        List<byte[]> mensagensEncriptadas = createQuery.getResultList();
        
        if(mensagensEncriptadas == null){
            return new ArrayList<>();
        }
        
        List<String> mensagensDesencriptadas = new ArrayList<>();
        CriptografiaRSA criptografiaRSA = new CriptografiaRSA();

        for (byte[] mensagem : mensagensEncriptadas) {
            byte[] pk = criptografiaRSA
                    .desencriptarMensagem(mensagem,
                            chavePrivada);
            String mensagemDesencriptada = new String(pk);
            mensagensDesencriptadas.add(mensagemDesencriptada);
        }

        return mensagensDesencriptadas;
    }

//    public List<String> getHistoricoIndividualDesencriptadoUsuario(
//            List<byte[]> mensagensEncripadas, PrivateKey chavePrivada)
//            throws Exception {
//        CriptografiaRSAImpl criptografiaRSA = new CriptografiaRSA();
//        List<String> mensagensDesencriptadas = new ArrayList<>();
//        for (byte[] mensagem : mensagensEncripadas) {
//            String msgDesencriptada = Arrays.toString(criptografiaRSA
//                    .desencriptarMensagem(mensagem, chavePrivada));
//            mensagensDesencriptadas.add(msgDesencriptada);
//        }
//        return mensagensDesencriptadas;
//    }
}
