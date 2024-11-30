# Tech-Challenge Fiap - Software Architecture

## Introdução
O projeto foi desenvolvido no âmbito da pós graduação em *Software Architecture* da Fiap. Dado um contexto e um problema (descritos abaixo), foi desenvolvida uma aplicação *back-end* * em Java Spring Boot, utilizando os conteúdos aprendidos no curso.

### Contexto
Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém, com a expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser caótico e confuso. Por exemplo, imagine que um cliente faça um pedido complexo, como um hambúrguer personalizado com ingredientes específicos, acompanhado de batatas fritas e uma bebida. O atendente pode anotar o pedido em um papel e entregá-lo à cozinha, mas não há garantia de que o pedido será preparado corretamente.

Sem um sistema de controle de pedidos, pode haver confusão entre os atendentes e a cozinha, resultando em atrasos na preparação e entrega dos pedidos. Os pedidos podem ser perdidos, mal interpretados ou esquecidos, levando à insatisfação dos clientes e a perda de negócios.

Em resumo, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada. Sem ele, expandir a lanchonete pode acabar não dando certo, resultando em clientes insatisfeitos e impactando os negócios de forma negativa.

### Problema
Para solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente, com as seguintes funcionalidades:

- **Pedido**
- **Pagamento**
- **Acompanhamento**
- **Entrega**
- **Gerenciar clientes**
- **Gerenciar produtos e categorias**

### Objetivos
#### Fase 1
- Desenvolver uma aplicação para todo o sistema de backend (monolito), com base na **arquitetura hexagonal**, apresentando as seguintes APIs:

- **Cadastro do Cliente**
- **Identificação do Cliente via CPF**
- **Criar, editar e remover produtos**
- **Buscar produtos por categoria**
- **Fake checkout, apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido.**

- Desenvolver um banco de dados, utilizado para organizar a fila dos pedidos.

- Observação: A aplicação deve ser entregue com um Dockerfile configurado para executá-la corretamente, e um docker-compose.yml para subir o ambiente completo.

### Desenvolvimento
Foi desenvolvida uma aplicação *back-end* em Java Spring Boot, contendo um conjunto de APIs, um banco de dados não-relacional MySQL, um Dockerfile e um Docker Compose. Utilizou-se os princípios da **arquitetura hexagonal** para estruturar a aplicação de maneira modularizada, coesa, e escalável.

### Roadmap
- [x] Criação do **modelo Event Storming** utilizando a ferramenta *Miro* [link](https://miro.com/app/board/uXjVKX1KHs0=/?share_link_id=306947055624)
- [x] Desenvolvimento do projeto base Utilizando Java + SpringBoot
  - [x] **Cadastro do Cliente**: Os clientes possuirão uma interface de cadastro e poderão optar se cadastrar utilizando nome, e-mail e CPF.
  - [x] **Identificação do Cliente via CPF**: Os clientes possuirão uma interface de identificação e poderão se identificar utilizando o CPF.
  - [X] **Criar, editar e remover produtos**: Será possível criar, editar e remover produtos e em cada categoria (lanche, bebida e sobremesa)
  - [x] **Buscar produtos por categoria**: Os clientes possuirão uma interface de pedido e poderão buscar os produtos por categoria (lanche, bebida ou sobremesa)
  - [x] **Fake checkout, apenas enviar os produtos escolhidos para a fila**
- [x] Criação do banco de dados não relacional utilizando o MySQL
- [x] Criação do Dockerfile e Docker Compose
- [x] Disponibilização de um Swagger para as APIs

### Funcionamento

## Como iniciar o projeto

- Para iniciar o projeto, rode os comandos do Docker Compose:
```bash
docker compose build
docker compose up
```

## Swagger
É possível realizar requisições via Swagger, [clicando aqui](http://localhost:8081/swagger-ui/index.html).

---------

### Objetivos
#### Fase 2
 - Atualizar a aplicação desenvolvida na FASE 1 refatorando o código para seguir os padrões clean code e clean architecture.
 - Criar uma arquitetura em Kubernetes que atenda os requisitos de negócio e garanta escalabilidade.
 - Produzir um desenho da arquitetura contemplando os requisitos de negócio e os requisitos de infraestrutura.
 - Colection com todas as APIs desenvolvidas com exemplos de requisição.
 - Guia completo com todas as instruções para execução do projeto.
 - Link para vídeo demonstrando a arquitetura.

### Desenho da estrutura da arquitetura no código
![image](https://github.com/user-attachments/assets/0b31b8c9-552d-432d-ab81-03576bca3305)

### Desenho da arquitetura de negócio
![image](https://github.com/user-attachments/assets/99041874-f4b6-4fb4-8e6d-8d1254d12b26)

### Desenho da arquitetura da infraestrutura:
![image](https://github.com/user-attachments/assets/c0f0a42f-df07-4136-b8ab-e1ed2acb8763)


### Funcionamento
## Como criar um cluster Kubernetes local

- O cluster pode ser criado localmente utilizando o Docker Desktop
- Para subir o cluster corretamente, é necessário seguir os seguintes comandos:


```bash
kubectl apply -f namespace
kubectl apply -f metrics.yaml 
kubectl apply -f secrets
kubectl apply -f mysql-configmap.yaml 
kubectl apply -f app-configmap.yaml

Subir banco e adminer:
kubectl apply -f adminer-deployment.yaml 
kubectl apply -f adminer-service-nodePort.yaml 
kubectl apply -f mysql-statefulset.yaml 
kubectl apply -f mysql-service.yaml

Subir app: 
kubectl apply -f app-deployment.yaml 
kubectl apply -f app-service-nodePort.yaml 
kubectl apply -f app-hpa.yaml 

```

## Swagger
É possível realizar requisições via Swagger, [clicando aqui](http://localhost:30001/swagger-ui/index.html).

## Adminer 
É possível acessar o banco de dados via Adminer [clicando aqui](http://localhost:30000/http://localhost:30000/).

## Vídeo
É possível o vídeo com explicações sobre a arquitetura e infraestrutura [clicando aqui](https://youtu.be/mXX1s7UK7mU).

## Contatos
## Contatos
- [Felipe Pereira](https://github.com/FelipecgPereira)
- [Jequelia Santana Gomes](https://github.com/jequelia)
- [Marcelo C Batista](https://github.com/cxmarcelo)
- [Rafael Bernardo Roseno](https://github.com/RafaelRoseno)
- [Sara Akemi Watanabe](https://github.com/SaraAWatanabe)

