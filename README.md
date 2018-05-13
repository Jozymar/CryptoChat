# CryptoChat
## Projeto da disciplina de Segurança de Dados

### Um projeto de Chat Online Criptografado. 

As convesas serão encriptadas de forma à manter a informação íntegra e confidencial. Apenas o usuário remetente e destinatário terão acesso plano às suas mensagens recebidas, apenas o proprio usuário pode descriptografar as mensagens que lhe foram enviadas, seguindo o modelo de criptografia ``RSA``.

**O PROJETO FAZ USO DE:**
> Criptografia assimétrica ``RSA`` para as mensagens (chave pública e privada), de ponta a ponta.

> Persistencia em bases de dados diferentes: duas instâncias do PostgreSQL.

> Senha codificada com ``MD5`` e armazenada como ``String`` formada pelo hexadecimal dos bytes.

> Certificado digital assinado por entidade certificadora - enbutido no servidor - acessado na porta 8081 pelo Docker, para impedir interceptação de mensagem da saída do client-side ao server-side. 

**Tecnologias usadas:**

> 1. ``Java Server Faces - JSF``

> 2. ``Java Persistence API -JPA``

> 3. ``Material Design (Materialize) - Framework``

**Instruções para Implantação** 

Pré-requisitos: ``Docker`` - ``Payara 4 ^ Embarcado``

> 1. Execute o arquivo ``Shell script`` ``sh`` **run.sh** no terminal para implantação e execução dos containers no ``Docker``

> 2. Espere o processo de construção e execução das imagens e dos containers

> 3. Após o passo 2, espere mais alguns segundos para a finalização da implantação

> 4. Acesse a ``URL`` [Página Inicial do CryptoChat](http://localhost:8082/cryptochat/index.xhtml) para Página Inical do Sistema. 

**Instruções para Desimplantação** 

> 1. Execute o arquivo ``sh`` **kill.sh** no terminal



