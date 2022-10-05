# Premissas:
Criar uma API usando Spring Boot, que faz parte da nossa stack.
Utilizar um banco de dados em memória (HSQLDB ou H2)
Não é necessário desenvolver front-end, apenas o back-end.

# Teste:
Desenvolver um serviço que seja capaz de gerar uma venda.
Uma venda é composta por id, data da venda, valor, vendedor id e vendedor nome.

# Sua tarefa é desenvolver os serviços REST abaixo:
- Criar uma nova venda
- Retornar a lista de vendedores com os campos: nome, total de vendas do vendedor e média de vendas diária, conforme o período informado por parâmetro 

# Instruções:
Gostaríamos de ver o histórico de seus commits.
Vamos avaliar a maneira que você escreveu seu código, a solução apresentada e a cobertura de testes automatizados.
Caso encontre algum impedimento no decorrer do desenvolvimento, entregue da maneira que preferir e faça uma explicação sobre o impedimento.
Avaliaremos também sua postura, honestidade e a maneira que resolve problemas.
Não é necessário implementar nenhum tipo de autenticação
Ao finalizar subir o projeto no github e nos enviar o link por email.
Nos retornar dentro de 7 dias, iremos analisar o projeto e agendar uma entrevista. 

# Tecnologias utilizada para da inicio do projeto
- JAVA 17 
- SPRING 2.7.4
- JPA para persistir objetos no banco
- BANCO DE DADOS H2 para fazer nossas manipulaçao de dados e persistencia 
- VALIDATION para validar os campos
- DEVTOOL para sempre atualizar o start da aplicação
- LOMBOK para tirar os codigos repedidos, como os contrutor dos Get, Set, Construres, HashCode
- ASSURED para construir os testes automatizado 
- MODELMAPPER para trabalhar melhor com os modelos DTOs
- SPRINGFOX conhecido como SWUGGER para a documentação da API 
# Segui o link: http://localhost:8080/swagger-ui/index.html usar depois que subir aplicação    
