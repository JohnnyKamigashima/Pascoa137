#language: pt
  Funcionalidade: Como Professor, quero fazer upload de aula em video
    Cenario: Fazer upload de um video de aula em mp4
      Dado que eu esteja na pagina de administrador
      Quando eu adicionar uma nova Aula
      E clicar em enviar arquivo "/src/test/resources/Aula1Login.mp4"
      E preencher como titulo "Aula sobre Login no Eveclass"
      E preencher como descricao "Aula teste para mostrar como funciona um login automatizado no site do Eveclass."
      E selecionar o autor "João Avelino"
      E adicionar as tags
        |tag|
        |Aula|
        |automatizado|
        |selenium|
      E concluir e salvar
      Entao a mensagem "Aula sobre Login no Eveclass" deve ser exibida
