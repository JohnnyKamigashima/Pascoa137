package unitTest;
// bibliotecas necessarias para usar nos testes
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.Assert.assertEquals;


public class TestVolumeCubo {


    @Test
    //O "@Test" é uma anotação utilizada em algumas linguagens de
    // programação, como Java e JUnit, para indicar que um determinado
    // método é um teste automatizado.

    //A ordem que vai rodar cada teste
    @Order(1)
    public void testCalcularVolumeCubo() {
        // Configura
        double comprimentoAresta = 5.0;

        //Executa
        double volumeCalculado = Math.pow(comprimentoAresta, 3);
        double resultadoEsperado = 125.0;

        //Valida
        assertEquals(resultadoEsperado, volumeCalculado, 0.0001);

    }
    //O @ParameterizedTest é uma anotação de teste de software que permite a
    // criação de testes automatizados parametrizados, executando o mesmo teste com
    // diferentes conjuntos de dados de entrada para identificar possíveis problemas em
    // diferentes cenários de uso do sistema, garantindo a qualidade e confiabilidade do software.
    // É uma prática recomendada no desenvolvimento de software de qualidade.
    @ParameterizedTest

    //A função do @CsvSource é permitir a passagem de dados em formato CSV (Comma-Separated Values)
    // como entrada para testes parametrizados em frameworks de teste de software.
    // Essa anotação facilita a criação de testes com conjuntos de dados de entrada definidos
    // em um arquivo CSV, simplificando a escrita de testes com múltiplas combinações de dados e
    // possibilitando a verificação do comportamento do sistema em diferentes cenários.
    // É uma forma eficaz de parametrizar testes com dados externos em formato tabular.
    @CsvSource(value = {
            "5.0,3,125",
            "6.0,3,216",
            "7.0,3,343"
    }, delimiter = ',')
    @Order(2)
    void testCalculoVolumeCuboLista(double aresta, int expoente, double esperado) {

        //Configura
        double result = Math.pow(aresta, expoente);

        // Valida
        assertEquals(esperado, result, 0.0001);
    }

    @ParameterizedTest

    //A função do @CsvFileSource é permitir a leitura de dados em formato CSV de um
    // arquivo externo como entrada para testes parametrizados em frameworks de teste
    // de software. Essa anotação facilita a criação de testes com conjuntos de dados de
    // entrada definidos em arquivos CSV, permitindo a separação dos dados de teste do código de
    // teste e simplificando a manutenção dos dados de teste. É uma forma eficaz de parametrizar
    // testes com dados externos em formato tabular, promovendo a reutilização de dados de teste em
    // diferentes testes e cenários.
    //Como o CSV é externo é necessario passar o caminho no qual foi criado o arquivo
    // indica o caminho do arquivo que será lido e seus delimitadores.
    @CsvFileSource(resources = "/csv/massaVolumeCubo.csv", numLinesToSkip = 1, delimiter = ',')
    @Order(3)

    void testCalculoVolumeCuboCsv(String csvaresta, String csvexpoente, String resultadoesperado){
        //Configura
        double aresta = Double.parseDouble(csvaresta.substring(csvaresta.indexOf(":") + 1).trim());
        double expoente = Double.parseDouble(csvexpoente.substring(csvexpoente.indexOf(":") + 1).trim());
        double esperado = Double.parseDouble(resultadoesperado.substring(resultadoesperado.indexOf(":") + 1));

        //Executa
        double result = Math.pow(aresta, expoente);

        //Valida
        assertEquals(esperado, result, 0.0001);

    }
}


