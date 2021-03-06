package com.ifpb.cryptochat.interfaces;

import com.ifpb.cryptochat.entidades.ChavePrivada;
import com.ifpb.cryptochat.entidades.Mensagem;
import com.ifpb.cryptochat.entidades.Usuario;
import java.security.PublicKey;
import java.util.List;

public interface MensagemDao {

    public void enviarMensagem(Mensagem mensagem, PublicKey chavePublicaDestinatario);

    public List<Mensagem> getHistoricoMensagens(Usuario remetente,
            Usuario destinatario, ChavePrivada chavePrivadaRem,
            ChavePrivada chavePrivadaDest)
            throws Exception;

}
