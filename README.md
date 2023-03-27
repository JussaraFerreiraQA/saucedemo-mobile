# Testes no site Saucedemo
## Configuração de ambiente do projeto

site: https:saucedemo.com
Login: Standart User
Senha: secret_sauce

* Java
* JDK
* Maven
* Web Driver Selenium
* Junit
* Appium
* IntelliJ ou VS Code
* Emulador Mobile por exemplo Android Studio

## Execução dos Testes Automação Mobile

* Teste de Login com sucesso
* Teste de acesso ao site com senha inválida
* Teste de acesso ao site com Usuário em branco
* Teste de acesso ao site com senha em branco
* Teste de validação do produto backpack 
* Teste de validação de tela com checkout sem Postal Code


Funcionalidade: 
- Como um usuário adicionando produtos no backpack do site Saucedemo
- O usuário quer validar o preço no backpack
- Para que ele possa comprar o produto

1. Cenário: Acessar o site com sucesso

Contexto:
Dado que o usuário preenche suas credencias válidas
Quando é realizado login
Então o login é realizado com sucesso

______________________________________________

2. Cenario: Acessar o site com senha inválida

Contexto:
Dado que o usuário preencha dados de acesso com senha inválido
Quando é realizado login
Então deve mostrar mensagem de erro

_______________________________________________

3. Cenário: Acessar o site com usuário em branco

Contexto:
Dado que o usuário preencha dados de acesso com usuário em branco
Quando é realizado login
Então deve mostrar mensagem de erro


_______________________________________________

4. Cenário: Acessar o site com senha em branco

Contexto:
Dado que o usuário preencha dados de acesso com senha em branco
Quando é realizado login
Então deve mostrar mensagem de erro

________________________________________________

5. Cenário: Adicionar produto ao carrinho e validar valor

Contexto:
Dado que o usuário selecionou o produto backpack
Quando o usuário acessa o carrinho
Então o valor do produto deve ser $29.99

________________________________________________

6. Cenário: Validar testo de erro na página checkout

Contexto:
Dado que o usuário está na tela de checkout
Quando preenche os ddos name como maria
E last name Silva
Mas não preenche o Post code
Então deve mostrar uma mensagem de erro
