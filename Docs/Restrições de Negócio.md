# Restrições de Negócio

---
## Restrições e regras de negócio

- Atributos em geral:
  - Aceita valor nulo ou não? 
    - _Sim_
  - Será permitido alteração futura?
    - _Sim_
  - Deverá ser único?
    - _Não_
  - Padrão (regex)-> um formato específico com CPF, CEP, CNPJ.
    - _Sim_ 
- Atributos do tipo texto:
  - Quantidade mínima e máxima de caracteres
    - _Mínima: 1; Máxima: Depende da situação_ 
  - Aceita valores espaço em branco ou não?
    - _Sim_ 
- Atributos numéricos:
  - Aceita valores negativos, positivos ou indiferente?
    - _Somente positivos_ 
  - Faixa de valores permitidos
    - _0 a Depende da situação_ 
  - Valores multipols de X. Ex: 10,20,30...
    - _Sim_
- Atributos de coleção:
  - Mínimo ou máximo de elementos.
    - _Mínimo: 2; Máximo: Depende da situação_
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
