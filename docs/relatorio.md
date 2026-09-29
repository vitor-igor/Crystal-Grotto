# Relatório de Modelagem e Persistência

## 1. Observações de modelagem

- **Tipos enumerados:** os atributos com conjunto controlado de valores foram representados por `enum` e persistidos com `EnumType.STRING`.

- **Objetos incorporáveis:** `CoordenadaGeografica` e `Endereco` foram modelados como `@Embeddable`, pois não possuem identidade ou ciclo de vida próprios.

- **Dados binários:** documentos, mapas e fotografias são armazenados como `@Lob` em `byte[]`, sem conversão para Base64.

- **Herança:** `Pessoa` foi definida como entidade abstrata utilizando a estratégia `JOINED`, mantendo os atributos comuns em uma tabela própria e os específicos nas tabelas das especializações.

- **Relacionamentos dependentes:** relações cujo ciclo de vida está diretamente associado à entidade proprietária utilizam `cascade` e/ou `orphanRemoval` quando apropriado.

- **Carregamento:** as coleções e os dados binários foram configurados para carregamento tardio, evitando a recuperação automática de grandes volumes de dados.

- **Integridade:** além das restrições declaradas pelo JPA, foram utilizadas restrições de banco para regras como valores positivos e a obrigatoriedade de informar massa ou volume em uma amostra.

---

## 2. Pessoas e herança

A principal decisão na modelagem de `Pessoa` foi utilizar uma hierarquia de entidades em vez de duplicar os atributos comuns em `Pesquisador` e `GuiaEspeleologia`. A estratégia `JOINED` foi escolhida para separar os dados compartilhados dos dados específicos de cada especialização. Com essa abordagem, os atributos comuns ficam concentrados na tabela da superclasse, enquanto cada especialização mantém apenas suas características próprias. Além de evitar duplicação, isso permite que novas especializações sejam acrescentadas posteriormente sem alterar a estrutura dos atributos comuns.

O endereço segue uma decisão diferente: apesar de possuir vários campos, ele não representa uma entidade independente. Por isso, `Endereco` foi definido como `@Embeddable` e seus atributos são persistidos junto à tabela de `Pessoa`.

## 3. Valores incorporáveis

`CoordenadaGeografica` também foi modelada como `@Embeddable`. A escolha é semelhante à realizada para `Endereco`: latitude, longitude e datum geodésico formam um único valor composto e não precisam de identidade própria. Essa representação evita a criação de tabelas adicionais para informações que não possuem ciclo de vida independente. Assim, tanto endereço quanto coordenadas permanecem como parte da entidade à qual pertencem.

## 4. Expedição e plano de segurança

A relação entre `Expedicao` e `PlanoSeguranca` recebeu uma atenção especial por se tratar de uma associação de ciclo de vida fortemente dependente. O plano não representa um recurso reutilizável entre expedições: ele existe especificamente para uma determinada expedição. Por isso, `PlanoSeguranca` é o lado proprietário da associação `@OneToOne`, mantendo a chave estrangeira da expedição com restrição de unicidade. No lado de `Expedicao`, a associação é representada por `mappedBy`. Também foi utilizado `cascade = CascadeType.ALL` com `orphanRemoval = true`. Dessa forma, a persistência e a remoção do plano acompanham a entidade à qual ele pertence, evitando que um plano fique armazenado sem a expedição correspondente.

Os procedimentos de evacuação foram tratados com `@ElementCollection`, pois são valores simples associados exclusivamente ao plano e não possuem atributos ou identidade que justificassem uma entidade própria.

## 5. Entidades associativas

`ParticipacaoExpedicao` e `UtilizacaoEquipamento` foram modeladas como entidades associativas porque as relações que representam possuem informações próprias.

No caso da participação, não seria suficiente armazenar apenas a relação entre pessoa e expedição, pois a própria participação possui atributos como função, presença, período e valor. Além disso, a combinação de pessoa e expedição possui uma restrição de unicidade em `ParticipacaoExpedicao`, impedindo que uma mesma pessoa seja registrada duas vezes na mesma expedição.

Da mesma forma, a utilização de um equipamento possui informações próprias daquela ocorrência, como retirada, devolução, condição e possíveis danos. Essa modelagem permite diferenciar os dados permanentes de uma pessoa ou equipamento dos dados pertencentes a uma participação ou utilização específica.

## 6. Coleta e amostras

Na relação entre `Coleta` e `Amostra`, a decisão mais relevante está na forma como a amostra é vinculada ao procedimento de coleta. Uma coleta pode originar várias amostras, enquanto cada amostra pertence a uma única coleta.

A entidade `Amostra` possui ainda uma restrição sobre seus dados de massa e volume: pelo menos uma dessas informações deve ser fornecida. Essa regra foi complementada com uma restrição no banco de dados, pois não é expressa apenas pela declaração de nulidade dos atributos.

A fotografia da amostra é armazenada como `@Lob` e configurada para carregamento tardio. A mesma abordagem é utilizada para os demais arquivos binários do sistema, evitando carregar documentos e imagens quando eles não são necessários para a operação realizada.

## 7. Autorizações ambientais

A associação com `Expedicao` foi definida como `@OneToMany`, permitindo que uma expedição possua mais de um registro de autorização. Essa escolha não significa que várias autorizações devam estar simultaneamente válidas, mas sim permite preservar o histórico de autorizações emitidas para a mesma expedição, inclusive quando uma autorização deixa de ser válida ou é substituída por outra. Essa distinção é importante porque representar a autorização como um simples `@OneToOne` faria com que uma nova autorização substituísse a anterior no relacionamento, dificultando a preservação desse histórico.

Por outro lado, a regra de que uma expedição não pode possuir mais de uma autorização corrente ou válida simultaneamente é uma restrição condicional. O relacionamento JPA não foi suficiente, por si só, para expressar essa condição (sendo necessário utilizar uma restrição adicional compatível com o PostgreSQL ou outra estratégia de validação). Portanto, a cardinalidade escolhida representa o **histórico de autorizações**, enquanto a situação e o período de validade determinam quais registros estão vigentes.

## 8. Estratégia de carregamento

A configuração de carregamento foi tratada considerando que algumas associações podem representar conjuntos grandes de dados. As coleções foram configuradas com `FetchType.LAZY`, evitando que uma entidade seja acompanhada automaticamente por todo o seu grafo de relacionamentos. A mesma preocupação foi aplicada aos arquivos binários, que não precisam ser recuperados em operações que utilizam apenas os dados cadastrais. Essa decisão permite que a recuperação de informações relacionadas aconteça conforme a necessidade da operação, evitando que uma consulta simples provoque o carregamento de setores, participações, coletas, amostras ou documentos que não serão utilizados.

## 9. Considerações finais

As principais decisões de persistência buscaram diferenciar estruturas que possuem identidade e ciclo de vida próprios de estruturas que funcionam apenas como valores pertencentes a outras entidades. Essa distinção orientou principalmente o uso de entidades associativas, objetos incorporáveis e relacionamentos com dependência de ciclo de vida.

Também foram consideradas as características específicas do domínio que não podem ser representadas apenas pela cardinalidade das associações. O caso das autorizações ambientais é o principal exemplo: a necessidade de preservar histórico e, ao mesmo tempo, controlar a validade atual exige separar essas duas preocupações no modelo.

Por fim, as configurações de integridade e carregamento foram definidas para complementar o mapeamento das entidades, evitando tanto registros estruturalmente inválidos quanto o carregamento desnecessário de dados potencialmente volumosos.