# Tech-Challenge Fiap - Software Architecture



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
- [Sara Akemi Watanabe](https://github.com/SaraAWatanabe)
- [Jequelia Santana Gomes](https://github.com/jequelia)

