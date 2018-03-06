package com.ifpb.cryptochat.daos;

import com.ifpb.cryptochat.entidades.ChavePrivada;
import com.ifpb.cryptochat.interfaces.ChavePrivadaDao;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ChavePrivadaDaoImpl implements ChavePrivadaDao {

    @PersistenceContext(unitName = "database-for-private-key")
    EntityManager entityManager;

    @Override
    public void persistirChave(ChavePrivada chavePrivada)
            throws NoSuchAlgorithmException {
        entityManager.persist(chavePrivada);
    }

    @Override
    public ChavePrivada getChavePrivadaUsuario(int idUsuario) {
        String querySql = "SELECT cp FROM ChavePrivada cp "
                + "WHERE cp.idUsuario= :id";
        TypedQuery<ChavePrivada> createQuery = entityManager
                .createQuery(querySql, ChavePrivada.class);
        createQuery.setParameter("id", idUsuario);
        return createQuery.getSingleResult();
    }

}
