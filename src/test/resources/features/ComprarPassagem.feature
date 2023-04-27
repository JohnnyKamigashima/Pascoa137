Feature: Comprar Passagem

  Scenario: : Comprar Passagem de "San Diego" para "Berlin"
  Given que acesso a pagina inicial
  When seleciono origem "San Diego" e destino "Berlin"
  And clico no botao Find Flights
  Then exibe pagina de voos entre "San Diego" e "Berlin" disponiveis