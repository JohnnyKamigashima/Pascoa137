package unitTest;

import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class TesteAreaTriangulo {

    // exercicio squad
    @Test
    public void areaTriangulo() {
        double base = 5;
        double altura = 3;
        double resultadoEsperado = 7.5;
        double areaCalculada = Calculadora.areaTriangulo(base, altura);
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
    @CsvFileSource(resources = "/csv/massaAreaTriangulo.csv", numLinesToSkip = 1, delimiter = ',')

    public void exercicioTesteAreaTrianguloArquivo (String txtNum1, String txtNum2, String resultadoEsperado) {
        double areaCalculada = Calculadora.areaTriangulo(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));
    }

    // fim do exercício squad de teste unitário

}


