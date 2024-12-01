# Tech-Challenge Fiap - Software Architecture

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

