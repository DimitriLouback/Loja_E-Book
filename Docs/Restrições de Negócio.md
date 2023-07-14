# Restrições de Negócio

---
## Restrições e regras de negócio

- Atributos em geral:
  - Aceita valor nulo ou não? 
    - _Apenas telefone (Pessoa) pode ser nulo_
  - Será permitido alteração futura?
    - _Apenas CPF (Pessoa) não pode alterar_
  - Deverá ser único?
    - _Apenas CPF (Pessoa), função (Cargo), titulo (E-Book) e serie (Coleção de E-Book)_
  - Padrão (regex)-> um formato específico com CPF, CEP, CNPJ.
    - _Apenas CPF (Pessoa)_ 
- Atributos do tipo texto:
  - Quantidade mínima e máxima de caracteres
    - _nome (Pessoa)= Mínima: 15; Máxima: 60_
    - _email (Pessoa)= Mínima: 10; Máxima: 60_
    - _cpf (Pessoa)= Mínima: 11; Máxima: 14_
    - _senha (Pessoa)= Mínima: 8; Máxima: 20_
    - _telefone (Pessoa)= Mínima: 9; Máxima: 16_
    - _função (Cargo)= Mínima: 10; Máxima: 30_
    - _titulo (E-Book)= Mínima: 1; Máxima: 60_
    - _genero (E-Book)= Mínima: 1; Máxima: 20_
    - _autor (E-Book)= Mínima: 15; Máxima: 60_
    - _editora (E-Book)= Mínima: 1; Máxima: 20_
    - _serie (Coleção de E-Book)= Mínima: 1; Máxima: 60_
  - Aceita valores espaço em branco ou não?
    - _Todos aceitam_ 
- Atributos numéricos:
  - Aceita valores negativos, positivos ou indiferente?
    - _Somente saldoDisponivel (Carteira) pode ser indiferente o resto é positivo_ 
  - Faixa de valores permitidos
    - _saldoDisponivel (Carteira)= 0 até 4000_
    - _salario (Cargo)= 900 até 5000_
    - _precoFinal (Compra)= 5 até 1500_
    - _preco (Produto)= 5 até 80_
    - _qtdPaginas (E-Book)= 10 até 3000_
    - _qtdVolumes (Coleção de E-Books)= 2 até 30_
  - Valores multiplos de X. Ex: 10,20,30...
    - _Sim_
- Atributos de coleção:
  - Mínimo ou máximo de elementos.
    - _telefone (Pessoa)= Mínimo: 0; Máximo: 6_
  - Atributos de relacionamento entre classes
    - _ _
  - Objeto A deve estar relacionamento com o objeto B.
    - _ _
  - Número mínimo ou máximo de relacionamentos.
    - _ _
  - Objeto A deve estar relacionado com objeto B que possua em seu atributo X o valor Y.
    - _ _
  - Restrições de Classe (que envolvem mais de 1 atributo)
    - _ _
  - A soma do valor de atributo A com o valor do atributo B deve ser igual a X.
    - _ _
  - Se atributo A possui o valor X, o atributo B deve estar preenchido com valor Y.
    - _ _
 
---
## Requisitos

- A aplicação deve gerenciar pelo menos 2 entidades distintas e que se relacionam.
- Futuramente será implementando segurança com sistema de login, adicione no diagrama a classe que representará o usuário do sistema.
