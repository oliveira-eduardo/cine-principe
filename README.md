# cinema Poo
Integrantes

    João Guilherme

    Eduardo Andrade

    Matheus Guedes

Sobre o Projeto

# Cine Príncipe - Sistema de Gestão e Compra de Ingressos

O **Cine Príncipe** é uma aplicação desktop desenvolvida em **Java** para a gestão completa de um cinema, englobando desde o fluxo de compra de ingressos e conveniências pelo cliente até o controle administrativo de catálogo, funcionários e usuários. 

Originalmente projetado para rodar via terminal, o sistema evoluiu para uma interface gráfica utilizando **Java Swing** com a biblioteca de temas **FlatLaf**.

---

## Tecnologias e Ferramentas

* **Linguagem:** Java (JDK 25)
* **Gerenciador de Dependências:** Maven
* **Banco de Dados:** SQLite 
* **Interface Gráfica (GUI):** Java Swing + FlatLaf Look and Feel

---

## Funcionalidades Principais

### Fluxo do Cliente 
* **Catálogo de Filmes:** Navegação visual pelas produções em exibição e detalhes das sessões.
* **Mapa de Assentos Interativo:** Escolha de poltronas gerenciada por matrizes em tempo real, travando assentos já ocupados para evitar duplicidade.
* **Loja de Snacks:** Menu integrado para adição de pipocas, jujubas e bebidas com cálculo automático de quantidades e subtotal.
* **Benefícios e Cupons:** Aplicação automática de descontos com base em cupons promocionais ou perfis cadastrados.
* **Checkout:** Resumo financeiro e geração de comprovante de pagamento isolado e legível.

### Painel do Sistema (Administração)
* **Controle de Acesso:** Telas e permissões restritas baseadas no nível do usuário logado (Cliente, Administrador ou Funcionário).
* **Gestão de Usuários:** Cadastro de novos clientes, alteração de dados e exclusão segura.
* **Gestão do Catálogo:** Inserção, edição e remoção de filmes, horários, sinopses e preços de ingressos direto pela interface.

---

## Estrutura do Projeto (Arquitetura)

O código adota o padrão **MVC (Model-View-Controller)** e está modularizado de forma organizada dentro do diretório `src`:

* 📂 `control/`: Classes intermediárias (Controladores) que processam as regras de negócio e controlam as interações das telas.
* 📂 `data/`: Camada de acesso e gerenciamento direto com o banco de dados SQLite, contendo as classes controladoras de fluxo de dados;
* 📂 `gui/`: Componentes visuais e janelas gráficas do sistema desenvolvidas em Swing.
* 📂 `model/`: Entidades de negócio e objetos de dados (`Usuario`, `Filme`, `Sessao`, `Bilhete`, etc.).
* 📂 `repository/` / `service/`: Estruturas auxiliares para separação lógica de consultas e serviços da aplicação.
* 📂 `exceptions/`: Tratamento de exceções e erros personalizados do sistema.
* 📂 `imagens/`: Armazenamento local dos recursos visuais e pôsteres dos filmes.

---

## Como Executar a Aplicação

### Pré-requisitos
* Java JDK 25 instalado na máquina.

Clone o repositório no seu ambiente:
   ```bash
   git clone [https://github.com/seu-usuario/nome-do-repositorio.git](https://github.com/seu-usuario/nome-do-repositorio.git)