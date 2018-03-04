# CryptoChat
### Projeto da disciplina de Segurança de Dados

Um projeto de Chat Online Criptografado. 

As convesas serão encriptadas de forma à manter a informação íntegra e confidencial. Apenas o usuário remetente e destinatário terão acesso plano as suas mensagens, graças ao bloqueio de interceptação de terceiros. 

**O PROJETO FAZ USO DE:**
> Criptografia assimétrica para as mensagens (chave pública e privada), de ponta a ponta.

> Persistencia em bases de dados diferentes: duas instâncias do PostgreSQL.

> Senha codigicada com ``MD5`` e armazenada como ``String`` formada pelo hexadecimal dos bytes.

> Certificado digital para impedir interceptação de mensagem da saída do client-side ao server-side. 

**Tecnologias usadas:**
1. ``Java Server Faces - JSF``
2. ``Java Persistence API -JPA``
3. ``HTML5``
4. ``CSS3``
5. ``JavaScript``

