# CryptoChat
### Projeto da disciplina de Segurança de Dados

Um projeto de Chat Online Criptografado. 

As convesas serão encriptadas de forma à manter a informação íntegra e confidencial. Apenas o usuário remetente e destinatário terão acesso plano às suas mensagens recebidas, apenas o proprio usuário pode descriptografar as mensagens que lhe foram enviadas, seguindo o modelo de criptografia ``RSA``.

**O PROJETO FAZ USO DE:**
> Criptografia assimétrica ``RSA`` para as mensagens (chave pública e privada), de ponta a ponta.

> Persistencia em bases de dados diferentes: duas instâncias do PostgreSQL.

> Senha codificada com ``MD5`` e armazenada como ``String`` formada pelo hexadecimal dos bytes.

> Certificado digital assinado por entidade certificadora - enbutido no servidor - acessado na porta 8081, para impedir interceptação de mensagem da saída do client-side ao server-side. 

**Tecnologias usadas:**
1. ``Java Server Faces - JSF``
2. ``Java Persistence API -JPA``
3. ``Material Design (Materialize) - Framework de Design do Google``
