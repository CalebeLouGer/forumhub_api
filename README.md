# 🗣️ ForumHub API

API RESTful para um sistema de fórum (CRUD de usuários, tópicos e respostas) construída com **Java e Spring Boot** — parte da coleção de projetos de backend do autor.

---

## 🧠 Visão Geral

O objetivo do *ForumHub API* é fornecer um backend robusto para aplicações de fórum, permitindo operações como:

- 📌 CRUD de **usuários**
- 📌 CRUD de **perguntas/tópicos**
- 📌 CRUD de **respostas**
- 🔐 Autenticação e autorização
- 📱 Integração com frontends ou apps

Este README padroniza instruções para uso, instalação e contribuições. :contentReference[oaicite:2]{index=2}

---

## 🚀 Funcionalidades

✅ Endpoints REST bem definidos  
✅ Estrutura de projeto em Java com Spring Boot  
✅ Persistência em banco relacional  
✅ Documentação auto-gerada (Swagger/OpenAPI)  

*(Ajuste conforme a implementação real do seu código.)* :contentReference[oaicite:3]{index=3}

---

## 🧾 Pré-requisitos

Antes de iniciar, tenha instalado:

- 🟢 **Java JDK 17+**
- ⚙️ **Maven 3.6+**
- 🗄️ Banco de dados SQL (ex: PostgreSQL ou MySQL)

---

## 📦 Instalação

### 🔁 Clonar o repositório

```bash
git clone https://github.com/CalebeLouGer/forumhub_api.git
cd forumhub_api
````

---

### ⚙️ Configurar variáveis de ambiente

Crie arquivo `application.properties` ou use variáveis de ambiente para configurar:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/forumhub
SPRING_DATASOURCE_USERNAME=seu_usuario
SPRING_DATASOURCE_PASSWORD=sua_senha
JWT_SECRET=sua_chave_secreta
```

---

### 🛠️ Compilar e rodar

```bash
mvn clean install
mvn spring-boot:run
```

ou, se estiver usando Docker:

```bash
docker build -t forumhub_api .
docker run -p 8080:8080 forumhub_api
```

---

## 📊 Documentação API

Após rodar a aplicação, a documentação interativa deve estar acessível em:

📍 `http://localhost:8080/swagger-ui.html`
📍 `http://localhost:8080/v3/api-docs`

*(confira caminhos conforme configuração real)*.

---

## 📌 Exemplos de Endpoints

| Método | Endpoint                     | Descrição                    |
| ------ | ---------------------------- | ---------------------------- |
| POST   | `/api/login`                 | Login e geração de token JWT |
| GET    | `/api/topicos`               | Listar todos os tópicos      |
| POST   | `/api/topicos`               | Criar novo tópico            |
| GET    | `/api/topicos/{id}`          | Obter tópico por ID          |
| POST   | `/api/topics/{id}/respostas` | Responder tópico             |

*(Ajuste conforme rotas implementadas no projeto.)*

---

## 💡 Testes

Execute os testes com:

```bash
mvn test
```

---

## 🤝 Contribuições

Contribuições são bem-vindas!
Siga o fluxo de GitHub:

1. Fork do projeto
2. Crie uma branch:
   `git checkout -b feature/nome-da-feature`
3. Commite suas alterações
4. Abra Pull Request
