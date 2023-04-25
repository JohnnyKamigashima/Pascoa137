package apiTest;

import apiTest.pojo.Usuario;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;



    public class TesteAccountIngrid {
        //Configura
        String ct = "application/json";

        //Inicializa variáveis userid e token de forma global para que todos os testes possam usar
        String userId;
        String token;

        // Funções e Métodos
        // Funções de Apoio
        public static String lerArquivoJson(String arquivoJson) throws IOException {
            return new String(Files.readAllBytes(Paths.get(arquivoJson)));
        }

        // Este é um outro caso parecido com o não teste de token, aqui ele deleta
        // a conta criada no teste depois de cada teste
        @AfterEach
        void tearDown() {
            //Deleta a conta criada
//           deletaContaCriada();
        }
//        @Test
//        @Disabled
//        public void testarIncluirAccount() throws IOException {
//            // carregar os dados do nosso json
//            String jsonBody = lerArquivoJson("src/test/resources/json/useringrid1.json");
//
//
//            String userName = "ingridSF";
//
//            // realizar o teste
//            given()                                         // Dado que
//                    .contentType(ct)                        // o tipo do conteúdo
//                    .log().all()                            // mostre tudo
//                    .body(jsonBody)                         // corpo da requisição
//                    .when()                                         // Quando
//                    .post("https://bookstore.toolsqa.com/Account/v1/User")                           // Endpoint / Onde
//                    .then()                                         // Então
//                    .log().all()                            // mostre tudo na volta
//                    .statusCode(201)                      // comunic. ida e volta ok
//                    //.body("code", is(200))                // tag code é 200
//                    .body("username", is(userName))      // tag type é "unknown"
//                    //.body("message", is(userId))                  // message é o userId
//            ;
//        } // fim do post

        @Test
        public void testarIncluirAccountJohnny() throws IOException {
            // carregar os dados do nosso json

            // Neste teste estamos incluindo um usuário novo, então precisamos deletar ele antes de incluir senão o teste falha
            //Configura
            String jsonBody = lerArquivoJson("src/test/resources/json/useringrid1.json");

            // Aqui vou carregar somente o nome do usuario do arquivo json para não escrever de forma fixa no teste
            // dessa forma só será necessário alterar o arquivo json para alterar o nome do usuario
            Gson gson = new Gson();
            Usuario username = gson.fromJson(jsonBody, Usuario.class);

            //Pega o userId e o token para usar no teste de deletar a conta
            //Executa
            //Pega resultado da requisição API coloca no response que é uma variável da classe Response
            Response response = (Response)
            given()                                         // Dado que
                    .contentType(ct)                        // o tipo do conteúdo
                    .log().all()                            // mostre tudo
                    .body(jsonBody)                         // corpo da requisição
            .when()                                         // Quando
                    .post("https://bookstore.toolsqa.com/Account/v1/User")                           // Endpoint / Onde
                    //Valida
            .then()                                         // Então
                    .log().all()                            // mostre tudo na volta
                    .statusCode(201)                      // comunic. ida e volta ok
                    .body("username", is(username.getUserName()))
                    .extract() //Extraindo o response
            ;

            //Pega o userId e o token para usar no teste de deletar a conta
            userId = response.jsonPath().getString("userID");
            token = getToken(jsonBody);
            deletaContaCriada();

        } // fim do post

        //Função para pegar o token
        //Esta função é quase um teste, ela exercita o endpoint de authanticação e retorna o token
        //Só não valida nada, pois o objetivo é pegar o token
        public String getToken(String jsonBody){

            //Executa
            return given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(jsonBody)
                    .when()
                    .post("https://bookstore.toolsqa.com/Account/v1/GenerateToken")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("status", is("Success"))
                    .body("result", is("User authorized successfully."))
                    .extract()
                    .jsonPath()
                    .getString("token");
        }

        public void deletaContaCriada(){
            given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .delete("https://bookstore.toolsqa.com/Account/v1/User/" + userId)
                    .then()
                    .log().all()
                    .statusCode(204);
        }

    }// fim da classe





