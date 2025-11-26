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
Permite **cadastro, edição, listagem e exclusão** de todas as entidades do sistema, como usuários, cinemas, salas, filmes, sessões, assentos e reservas.  
Essa camada garante **controle total e integridade dos dados**.

### 2. Autenticação JWT (JSON Web Token)
O login será baseado em **tokens JWT**, garantindo **segurança e praticidade**.  
Após o login, o servidor gera um token criptografado que valida todas as requisições subsequentes, permitindo acesso apenas a usuários autenticados.

### 3. Criptografia de Senhas com Hash
As senhas dos usuários são armazenadas de forma segura, utilizando funções de **hash (bcrypt ou SHA-256)**, evitando exposição em caso de vazamento.

### 4. Tratamento de Erros em Todas as Camadas
O sistema possui **tratamento centralizado de erros**, fornecendo respostas **claras e padronizadas**, tanto para falhas internas quanto para erros de entrada, garantindo estabilidade e facilidade na depuração.

### 5. Geração Automática de Sessões
Processo automatizado que cria **sessões de filmes** com base na **duração, disponibilidade de salas e horários**, reduzindo o esforço manual e mantendo o cronograma sempre atualizado.

### 6. Permissionamento
O sistema define **níveis de acesso diferenciados** (administrador, funcionário e cliente), controlando **visualização e modificação** de dados conforme o perfil do usuário.

---

## 🧩 Principais Funcionalidades dos Modelos (Entidades)

### 1. Usuários
**Funcionalidades:**
- Cadastro e autenticação de usuários.  
- Associação de um usuário a um cinema (funcionário ou administrador).  
- Controle de acessos (campo `acessos` define o nível de permissão).

---

### 2. Cinemas
**Funcionalidades:**
- Cadastro de cinemas e vínculo com uma localização.  
- Relacionamento com salas, usuários e sessões.

---

### 3. Localizações
**Funcionalidades:**
- Armazena endereço completo de cinemas (CEP, cidade, bairro, rua, número e UF).

---

### 4. Filmes
**Funcionalidades:**
- Controle de catálogo de filmes (nome, descrição e duração).  
- Associação com sessões.

---

### 5. Salas
**Funcionalidades:**
- Gerenciamento das salas de exibição por cinema.  
- Definição de layout e capacidade máxima.  
- Associação com assentos e sessões.

---

### 6. Assentos
**Funcionalidades:**
- Controle detalhado por fileira, número e tipo (VIP, comum, acessível).  
- Associação com uma sala e reservas específicas.

---

### 7. Sessões
**Funcionalidades:**
- Ligação entre filme, sala e horário.  
- Definição de preço base.  
- Controle de início e fim da sessão.

---

### 8. Reservas
**Funcionalidades:**
- Registro de reservas feitas por usuários.  
- Associação entre usuário, sessão e assento.  
- Controle de status e preço final.

---

### 9. Assento_Reserva
**Funcionalidades:**
- Define qual assento foi reservado em uma sessão.  
- Registra o preço final do assento (pode variar conforme o tipo).

---

## 🧱 Descrições e Atributos dos Modelos (Entidades)

| Entidade | Descrição | Principais Atributos |
|-----------|------------|----------------------|
| **Usuarios** | Representa os usuários do sistema (clientes ou administradores). | `id_usuario`, `nome`, `sobrenome`, `email`, `senha`, `id_cinema`, `acessos` |
| **Cinemas** | Armazena os dados dos cinemas cadastrados. | `id_cinema`, `nome`, `localizacao` |
| **Localizacoes** | Define os endereços dos cinemas. | `id_localizacao`, `cep`, `cidade`, `bairro`, `rua`, `numero`, `uf` |
| **Filmes** | Define os filmes exibidos. | `id_filme`, `nome`, `descricao`, `duracao` |
| **Salas** | Define as salas de cada cinema. | `id_sala`, `numero`, `layout`, `capacidade`, `id_cinema` |
| **Assentos** | Representa os assentos dentro de uma sala. | `id_assento`, `fileira`, `numero`, `tipo`, `id_sala` |
| **Sessoes** | Define os horários e filmes em exibição. | `id_sessao`, `id_sala`, `id_filme`, `horario_inicio`, `horario_fim`, `preco_base` |
| **Reservas** | Representa as reservas feitas pelos usuários. | `id_reserva`, `id_usuario`, `id_sessao`, `status` |
| **Assento_Reserva** | Faz a relação entre reserva e assento. | `id_assento_reserva`, `id_reserva`, `id_assento`, `preco_final` |
