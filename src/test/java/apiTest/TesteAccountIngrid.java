package apiTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;



    public class TesteAccountIngrid {
        String ct = "application/json";

        static String uriAccount = "https://bookstore.toolsqa.com/Account/v1/User";

        // Funções e Métodos
        // Funções de Apoio
        public static String lerArquivoJson(String arquivoJson) throws IOException {
            return new String(Files.readAllBytes(Paths.get(arquivoJson)));
        }
        @Test
        public void testarIncluirAccount() throws IOException {
            // carregar os dados do nosso json
            String jsonBody = lerArquivoJson("src/test/resources/json/useringrid1.json");


            String username = "ingridSF";

            // realizar o teste
            given()                                         // Dado que
                    .contentType(ct)                        // o tipo do conteúdo
                    .log().all()                            // mostre tudo
                    .body(jsonBody)                         // corpo da requisição
                    .when()                                         // Quando
                    .post("https://bookstore.toolsqa.com/Account/v1/User")                           // Endpoint / Onde
                    .then()                                         // Então
                    .log().all()                            // mostre tudo na volta
                    .statusCode(201)                      // comunic. ida e volta ok
                    //.body("code", is(200))                // tag code é 200
                    .body("username", is(username))      // tag type é "unknown"
            //.body("message", is(userId))                  // message é o userId
            ;
        } // fim do post

    }// fim da classe





