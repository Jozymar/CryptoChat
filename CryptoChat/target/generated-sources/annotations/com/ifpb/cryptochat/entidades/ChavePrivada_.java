package com.ifpb.cryptochat.entidades;

import java.security.PrivateKey;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-07T19:30:18")
@StaticMetamodel(ChavePrivada.class)
public class ChavePrivada_ { 

    public static volatile SingularAttribute<ChavePrivada, Integer> idUsuario;
    public static volatile SingularAttribute<ChavePrivada, Integer> id;
    public static volatile SingularAttribute<ChavePrivada, PrivateKey> chavePrivada;

}