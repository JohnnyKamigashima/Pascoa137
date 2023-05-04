#language: pt
Funcionalidade: Criar testes com questes de escolha unica, multipla, completar e dissertativa
  Cenario: Validar que todos os tipos de questões podem ser adicionados em um teste
    Dado que estou logado como professor
    E crio uma prova com título "Teste simulado de matemática" com "45" minutos de duração, enunciado "Resolva as 4 questões em 45 minutos conforme indicado no enunciado." e as tags "Matemática",  "Simulado", "Prova"
    E crio um teste com questão de escolha unica com título "Questão Simples Escolha", tags "Matemática",  "Simulado", "Prova", enunciado "Complete a questão conforme este enunciado", alternativas "Alternativa 1", "Alternativa 2", "Alternativa 3"
    E crio teste com questão de escolhas múltiplas com título "Questão Múltipla Escolha", tags "Matemática",  "Simulado", "Prova", enunciado "Complete a questão conforme este enunciado", alternativas "Alternativa 1", "Alternativa 2", "Alternativa 3"
    E crio teste com questão de completar com título "Questão de Preenchimento", tags "Matemática",  "Simulado", "Prova", enunciado "Complete a questão conforme este enunciado", "{uma} andorinha só, não faz. Verão."
    E crio teste com questão dissertativa com o título "Questão Dissertativa" com as tags "Matemática",  "Simulado", "Prova", enunciado "Complete a questão conforme este enunciado", "{uma} andorinha só, não faz. Verão."
    Quando eu clico em salvar "Teste simulado de matemática"
    Entao vejo que o "Teste simulado de matemática" é mostrado na lista de testes
