package com.ifpb.cryptochat.entidades;

import com.ifpb.cryptochat.entidades.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-05T16:29:03")
@StaticMetamodel(Mensagem.class)
public class Mensagem_ { 

    public static volatile SingularAttribute<Mensagem, Usuario> remetente;
    public static volatile SingularAttribute<Mensagem, Integer> id;
    public static volatile SingularAttribute<Mensagem, byte[]> corpoMensagem;
    public static volatile SingularAttribute<Mensagem, Usuario> destinatario;

}