#language: pt
  Funcionalidade: Selecionar Passagem
    Cenario: Selecionar Passagem com Sucesso
      Dado que acesso o site Blazedemo
      Quando seleciono a origem como "São Paulo" e destino "Berlin"
      E clico em Procurar Voo
      Então exibe a frase indicando voo entre "São Paulo" e "Berlin"