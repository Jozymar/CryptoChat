package com.ifpb.cryptochat.entidades;

import com.ifpb.cryptochat.entidades.Mensagem;
import java.security.PublicKey;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T17:34:48")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, byte[]> foto;
    public static volatile SingularAttribute<Usuario, String> nickname;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, PublicKey> publicKey;
    public static volatile ListAttribute<Usuario, Mensagem> mensagens;
    public static volatile SingularAttribute<Usuario, String> email;

}