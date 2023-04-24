package unitTest;

import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteAreaTriangulo {

    // exercicio squad
    @Test
    public void areaTriangulo() {
        double base = 5;
        double altura = 3;
        double resultadoEsperado = 7.5;

        double areaCalculada = Calculadora.areaTriangulo(base, altura);
    }
    @Test
    public void areaTrianguloJohnny() {
        //Observação: Testes precisam de 3 etapas: Configura, Executa e Valida.
        //O mais importante é o Valida, pois é o que garante que o teste está correto.

        //Configura
        // Aqui você configura os dados que você vai dar de entrada e o valor de saída esperado.
        double base = 5;
        double altura = 3;
        double resultadoEsperado = 7.5;

        //Executa
        // Aqui você executa o método do Desenvolvedor que você quer testar.
        double areaCalculada = Calculadora.areaTriangulo(base, altura);

        //Valida
        // Aqui você valida se o resultado que você obteve é igual ao resultado esperado.
        assertEquals(areaCalculada,resultadoEsperado);
    }
    @ParameterizedTest
    @CsvSource(value = {
            "8, 6, 24",
            "10, 6, 30"
            } , delimiter = ',')
    public void exercicioTesteAreaTriangulo (String txtNum1, String txtNum2, String resultadoEsperado) {
        double areaCalculada = Calculadora.areaTriangulo(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));
    }
    @ParameterizedTest
    //Configura
    @CsvSource(value = {
            "8, 6, 24",
            "10, 6, 30"
    } , delimiter = ',')
    public void exercicioTesteAreaTrianguloJohnny (double Num1, double Num2, double resultadoEsperado) {
        //Executa
        double areaCalculada = Calculadora.areaTriangulo(Num1, Num2);

        //Valida
        assertEquals(areaCalculada,resultadoEsperado);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/massaAreaTriangulo.csv", numLinesToSkip = 1, delimiter = ',')

    public void exercicioTesteAreaTrianguloArquivo (String txtNum1, String txtNum2, String resultadoEsperado) {
        double areaCalculada = Calculadora.areaTriangulo(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));
    }

    @ParameterizedTest
    //Configura
    @CsvFileSource(resources = "/csv/massaAreaTriangulo.csv", numLinesToSkip = 1, delimiter = ',')

    public void exercicioTesteAreaTrianguloArquivoJohnny (double Num1, double Num2, double resultadoEsperado) {
        //Executa
        double areaCalculada = Calculadora.areaTriangulo(Num1, Num2);

        //Valida
        assertEquals(areaCalculada,resultadoEsperado);
    }


    // fim do exercício squad de teste unitário

}


