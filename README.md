# ABP_BackEnd

# Documentação da API

### 👥 Integrantes do Grupo  
- **Kauan Custodio Propodoski**  
- **Miguel Nilo Rosa**  
- **Thiago De Moliner Colombo**

---

## 🎯 Objetivo da API

O projeto tem como propósito o desenvolvimento de um **WebApp voltado para entretenimento e cultura**, mais especificamente um **sistema de gerenciamento de cinema**.  
A plataforma permitirá que os usuários visualizem filmes em exibição, datas e horários disponíveis, além de consultar assentos livres para reserva.

O sistema contará com **duas partes integradas**:
- **Público externo:** navegação e reserva de sessões.  
- **Uso interno:** administração de filmes, sessões, salas e reservas.

---

## ⚙️ Funcionalidades da API

### 1. Gerenciamento das Entidades do Banco de Dados
Permite **cadastro, edição, listagem e exclusão** das entidades do sistema, como usuários e sessoes
Essa camada garante **controle total e integridade dos dados**.

### 2. Autenticação JWT (JSON Web Token)
O login será baseado em **tokens JWT**, garantindo **segurança e praticidade**.  
Após o login, o servidor gera um token criptografado que valida todas as requisições subsequentes, permitindo acesso apenas a usuários autenticados.

### 3. Criptografia de Senhas com Hash
As senhas dos usuários são armazenadas de forma segura, utilizando funções de **hash (bcrypt ou SHA-256)**, evitando exposição em caso de vazamento.

### 4. Tratamento de Erros em Todas as Camadas
O sistema possui **tratamento centralizado de erros**, fornecendo respostas para falhas

### 5. Geração Automática de Sessões
Processo automatizado que cria **sessões de filmes** com base na **duração, disponibilidade de salas e horários**, reduzindo o esforço manual e mantendo o cronograma sempre atualizado.

### 6. Permissionamento
O sistema define **níveis de acesso diferenciados** (administrador, funcionário e cliente), controlando **visualização e modificação** de dados conforme o perfil do usuário.

---

## 🚀 Tecnologias Utilizadas

- Java
- Spring
- Postgresql
- Git

---

## 🧩 Principais Funcionalidades dos Modelos (Entidades)

### 1. Usuários
**Funcionalidades:**
- Cadastro e autenticação de usuários.  
- Associação de um usuário a um cinema (funcionário ou administrador).  
- Controle de acessos (campo `acessos` define o nível de permissão).

### 2. Sessões
**Funcionalidades:**
- Ligação entre filme, sala e horário.  
- Definição de preço base.  
- Controle de início e fim da sessão.

---

### 3. Reservas
**Funcionalidades:**
- Registro de reservas feitas por usuários.  
- Associação entre usuário, sessão e assento.  
- Controle de status e preço final.

---

### 4. Assento_Reserva
**Funcionalidades:**
- Define qual assento foi reservado em uma sessão.  
- Registra o preço final do assento (pode variar conforme o tipo).

---

## 🗃️ Estrutura de pastas

│
├── config/
├── controller/
├── domain/
│   ├── dto/
│   ├── entity/
│   ├── mapper/
├── repository/
├── service/
├── util/
└── validation/


## ❗ Exemplos de Erros HTTP

| Código  | Motivo            | Exemplo                                   |
| ------- | ----------------- | ----------------------------------------- |
| **403** | Sem permissão     | Usuário tentando acessar recurso proibido |
| **404** | Não encontrado    | Filme ou sessão inexistente               |
| **409** | Conflito          | E-mail duplicado, assento já reservado    |
| **500** | Erro interno      | Falha inesperada no servidor              |

---

## 🧱 Descrições e Atributos dos Modelos (Entidades)

| Entidade | Descrição | Principais Atributos |
|-----------|------------|----------------------|
| **Usuarios** | Representa os usuários do sistema (clientes ou administradores). | `id_usuario`, `nome`, `sobrenome`, `email`, `senha`, `id_cinema`, `acessos`, `id_funcao`|
| **Cinemas** | Armazena os dados dos cinemas cadastrados. | `id_cinema`, `nome`, `localizacao` , `horario_inicio`, `horario_fim`|
| **Localizacoes** | Define os endereços dos cinemas. | `id_localizacao`, `cep`, `cidade`, `bairro`, `rua`, `numero`, `uf` |
| **Filmes** | Define os filmes exibidos. | `id_filme`, `nome`, `descricao`, `duracao` |
| **Salas** | Define as salas de cada cinema. | `id_sala`, `numero`, `layout`, `capacidade`, `id_cinema` |
| **Assentos** | Representa os assentos dentro de uma sala. | `id_assento`, `fileira`, `numero`, `tipo`, `id_sala` |
| **Sessoes** | Define os horários e filmes em exibição. | `id_sessao`, `id_sala`, `id_filme`, `horario_inicio`, `horario_fim`, `preco_base` |
| **Reservas** | Representa as reservas feitas pelos usuários. | `id_reserva`, `id_usuario`, `id_sessao`, `status` |
| **Assento_Reserva** | Faz a relação entre reserva e assento. | `id_assento_reserva`, `id_reserva`, `id_assento`, `preco_final` |
| **funcoes** | Armazena os nomes das funções | `id_funcoes`, `nome` |


## ▶️ Como Executar o Projeto Localmente

Clonar o repositório

`git clone <url-do-repositorio>`

`cd ABP_BackEnd`

`Definir o DB_PASSWORD e DB_URL em uma configuração de execução`


A API estará disponível em:
http://localhost:8080
