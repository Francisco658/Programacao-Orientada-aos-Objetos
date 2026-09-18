# Programação Orientada aos Objetos - Trabalho Prático

Trabalho Prático no âmbito da Unidade Curricular de Programação Orientada aos Objetos

**<ins> Grupo </ins>**
* [Afonso Bessa](https://github.com/AsseB2519) - a95225
* [Francisco Claudino](https://github.com/Francisco658) - a89493
* [João Barroso](https://github.com/JoaoBarroso25) - a95195

**Licenciatura em Engenharia Informática**

**Universidade do Minho (2022/2023)**

## Introdução

Este é um projeto de marketplace **Vintage**, onde será possível comprar e vender artigos novos e usados de diferentes categorias. O sistema é construído para permitir que os utilizadores registados assumam o papel de vendedor ou comprador. Os vendedores podem adicionar novos itens para venda e os compradores podem optar por comprá-los.

O sistema **Vintage** também gere todas as transações, incluindo as compras e vendas de produtos. Existem vários tipos de produtos disponíveis, como vestuário, calçado, acessórios e muito mais. É importante realçar que o sistema pode ser facilmente expandido para incluir outros tipos de produtos no futuro.

Cada produto tem um conjunto comum de propriedades e é identificado por um código alfanumérico exclusivo (código de barras). As compras são organizadas em encomendas e entregues por várias empresas de transporte. Para cada encomenda finalizada, a **Vintage** cobra uma taxa de garantia de serviço ao vendedor, garantindo a segurança e satisfação dos compradores caso as encomendas não sejam entregues.

O sistema **Vintage** controla o stock dos produtos disponíveis, bem como as encomendas feitas pelos compradores e as vendas realizadas pelos vendedores. Um vendedor pode publicar os seus produtos para venda e decidir qual empresa de transporte irá lidar com a expedição. Um comprador pode adicionar vários itens a uma encomenda e finalizá-la, e também tem a opção de devolver a encomenda dentro de 48 horas (configurável) após a finalização da encomenda.

Um recurso importante do sistema é a capacidade de simular o tempo para que ele possa funcionar de forma semelhante a um cenário de uso real. O sistema sabe em que data está e inclui uma funcionalidade que permite avançar para uma data no futuro. Quando o tempo avança, as encomendas são entregues, o stock de produtos é atualizado e assim por diante.

## Artigos
A **Vintage** especializa-se em três tipos principais de artigos: **Sapatilhas**, **T-shirts** e **Malas**. Cada um destes artigos pode ser novo ou usado, sendo neste último caso disponibilizado também uma avaliação do seu estado e informação sobre o número de donos que já possuiram o artigo. Cada artigo contém uma descrição, uma marca, um código alfanumérico e um preço base e ainda uma correcção de preço (normalmente um desconto) que é definida em função de cada tipo de artigo e da sua condição particular.
Os artigos são detalhados tal que:
  - As **Sapatilhas**, possuem um tamanho numérico, uma indicação se possuem atacadores/atilhos, uma cor e a data de lançamento da coleção a que pertencem (existe uma coleção por ano). Apenas há lugar a aplicação de desconto em sapatilhas usadas (ou seja que não sejam desta coleção/ano), definido pelo vendedor, ou em sapatilhas novas acima do tamanho 45. Um exemplo de cálculo do preço de uma sapatilha usada, ou antiga, poderá ser algo como:
  
```precoBase − (precoBase/numeroDonos ∗ estadoUtilizacao)```
                      
Poderá ainda existir um tipo especial de **Sapatilhas**, as **Premium** de edições especiais de autores reconhecidos, cujo valor de mercado aumenta com o passar dos anos. Para essas a fórmula de cálculo do valor terá de prever não um desconto, mas um acréscimo de valor.
  - As **T-Shirt**, possuem um tamanho (S,M,L,XL) e um padrão (liso, riscas, palmeiras). As **T-Shirts** com padrão liso nunca têm desconto. Os restantes padrões têm um desconto fixo de 50% se forem usados.
  - As **Malas**, possuem informação sobre a sua dimensão, sendo que o desconto será sempre proporcionalmente inverso à dimensão, o material de que são constituídas e o ano da coleção. Tal como para as **Sapatilhas**, para as **Malas** existem aquelas que são **Premium** e cujo valor em vez de decrescer com a utilização (número de anos da carteira) aumenta com a mesma. Essas malas apresentarão uma valorização de X% ao ano (a definir por cada tipo de mala).


## Requisitos

O trabalho proposto tem vários níveis de requisitos, desde os mais básicos até aos mais complexos, tais como:

  -  Requisitos base de gestão das entidades
  -  Efectuar estatísticas sobre o estado do programa
  -  Alterar os transportadores e prever a noção de Premium
  -  Automatizar a simulação
