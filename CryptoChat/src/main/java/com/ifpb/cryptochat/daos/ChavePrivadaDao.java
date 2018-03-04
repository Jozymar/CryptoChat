package com.ifpb.cryptochat.daos;

import com.ifpb.cryptochat.entidades.ChavePrivada;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ChavePrivadaDao {

    @PersistenceContext(unitName = "database-for-private-key")
    EntityManager entityManager;

    public void persistirChave(ChavePrivada chavePrivada)
            throws NoSuchAlgorithmException {
        entityManager.persist(chavePrivada);
    }

    public PrivateKey getChavePrivadaUsuario(int idUsuario) {
        String querySql = "SELECT c.chavePrivada FROM ChavePrivada c "
                + "WHERE c.idUsuario= :id";
        TypedQuery<PrivateKey> createQuery = entityManager
                .createQuery(querySql, PrivateKey.class);
        createQuery.setParameter("id", idUsuario);
        return createQuery.getSingleResult();
    }

}
