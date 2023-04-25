package apiTest;

import apiTest.pojo.Configuracao;
import apiTest.pojo.Usuario;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

class TesteAccountJohnny {

    public TesteAccountJohnny() throws IOException {
    }

    public String leArquivoJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Instancia o objeto Gson
    Gson gson = new Gson();
    //Lê o arquivo json e instancia o objeto Configuracao
    String configuracaoJson = leArquivoJson("src/test/resources/json/configuracao.json");
    Configuracao configuracao = gson.fromJson(configuracaoJson, Configuracao.class);
    String userId; //UserId global por que pega no teste e usa no teardown
    String token; //Token global por que pega no teste e usa no teardown

    @AfterEach
    void tearDown() {
        //Deleta a conta criada
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
        .when()
                .delete(configuracao.getBaseUrl() + configuracao.getEndpoints().getUser() + "/" + userId)
        .then()
                .log().all()
                .statusCode(204);
    }
    @Test
    @DisplayName("Teste de criação de uma conta")
    @Order(1) //Executa em primeiro
    public void testeCriarUmaConta() throws IOException {
        //Le arquivo com informação de um usuário
        String jsonBody = leArquivoJson("src/test/resources/json/usuarioBookStore.json");
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(jsonBody, Usuario.class);

        Response response = (Response)  //Pega resultado e coloca no response
            given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(jsonBody)
            .when()
                .post(configuracao.getBaseUrl() + configuracao.getEndpoints().getUser())
            .then()
                .log().all()
                .statusCode(201)
                .body("username", is(usuario.getUserName()))
                .extract();

        //Guarda informações para deletar a conta depois
        userId = response.jsonPath().getString("userID");
        token = getToken(jsonBody);
    };

    @ParameterizedTest
    @Order(2) //Executa em segundo
    @CsvSource(value = {
            "Frieda1,11peaN*ts",
            "Shermy1,11peaN*ts",
            "PigPen1,11peaN*ts"
            }, delimiter = ',') //Separa os valores por virgula
    @DisplayName("Teste de criação Parametrizada de contas")
    public void TesteDeCriacaoParametrizadaDeContas(String username, String password) throws IOException {
        // Cria um usuário
        Usuario usuario = new Usuario();

        //Seta os valores do usuário
        usuario.setUserName(username);
        usuario.setPassword(password);

        //Converte para json string
        String jsonBodyParametrizado = gson.toJson(usuario);

        //Executa
        Response response = (Response) //Pega resultado e coloca no response
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(jsonBodyParametrizado)
        .when()
                .post(configuracao.getBaseUrl() + configuracao.getEndpoints().getUser())
        .then()
                .log().all()
                .statusCode(201)
                .body("username", is(usuario.getUserName()))
                .extract();

        //Guarda informações para deletar a conta depois
        userId = response.jsonPath().getString("userID");
        token = getToken(jsonBodyParametrizado);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/massaBookstoreUsers.csv", delimiter = ',', numLinesToSkip = 1) //Lê o arquivo csv
    @Order(3) //Executa em terceiro
    @DisplayName("Teste de criação Parametrizada de contas usando CSV")
    public void TesteDeCriacaoParametrizadaDeContasCSV(String username, String password) throws IOException {
        // Cria um usuário
        Usuario usuario = new Usuario();

        //Seta os valores do usuário
        usuario.setUserName(username);
        usuario.setPassword(password);

        //Converte para json string
        String jsonBodyParametrizado = gson.toJson(usuario);

        //Executa
        Response response = (Response) //Pega resultado e coloca no response
        given()
                .log().all()
                .contentType("application/json")
                .body(jsonBodyParametrizado)
        .when()
                .post(configuracao.getBaseUrl() + configuracao.getEndpoints().getUser())
        .then()
                .log().all()
                .statusCode(201)
                .body("username", is(usuario.getUserName()))
                .extract();

        //Guarda informações para deletar a conta depois
        userId = response.jsonPath().getString("userID");
        token = getToken(jsonBodyParametrizado);
    }

    //Função para pegar o token
    public String getToken(String jsonBody){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(jsonBody)
        .when()
                .post(configuracao.getBaseUrl() + configuracao.getEndpoints().getToken())
        .then()
                .log().all()
                .statusCode(200)
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .extract()
                    .jsonPath()
                    .getString("token");
    }

}
