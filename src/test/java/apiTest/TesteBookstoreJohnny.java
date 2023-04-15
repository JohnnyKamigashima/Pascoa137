package apiTest;

import apiTest.pojo.Configuracao;
import apiTest.pojo.Usuario;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

class TesteBookstoreJohnny {

    //Configuração de variáveis globais
    public Usuario usuario;
    public Configuracao config;
    public Gson gson;
    public TesteBookstoreJohnny() throws IOException {
    }

    //Cria uma função para ler o arquivo json
    public String leArquivoJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Executa antes de cada teste
    @BeforeEach
    void setUp() throws IOException {
        //Instancia o objeto Gson
        this.gson = new Gson();

        //Lê o arquivo json e instancia o objeto Usuario
        String jsonUsuario = leArquivoJson("src/test/resources/json/usuarioBookStore.json");
        this.usuario = gson.fromJson(jsonUsuario, Usuario.class);

        //Lê o arquivo json e instancia o objeto Configuracao
        String jsonConfig = leArquivoJson("src/test/resources/json/configuracao.json");
        this.config = gson.fromJson(jsonConfig, Configuracao.class);
    }

    @Test
    @Order(0)
    @DisplayName("Teste de consulta de livros")
    void testeConsultaLivros() throws IOException {
        given()
            .log().all()
            .contentType("application/json")
        .when()
            .get(config.getBaseUrl() + config.getEndpoints().getBooks())
        .then()
            .log().all()
            .statusCode(200);
    }

    @ParameterizedTest(name = "{displayName} {0} ao Titulo {1}")
    @CsvFileSource(resources = "/csv/massaBookstoreIsbn.csv", numLinesToSkip = 1, delimiter = ';')
    @Order(1)
    @DisplayName("Verifica ISBN")
    void adicionaIsbnAoUsuario(String isbn, String titulo) throws IOException {

        given()
            .log().all()
            .contentType("application/json")
        .when()
            .get(config.getBaseUrl() + config.getEndpoints().getBook() + "?ISBN=" + isbn)
        .then()
            .log().all()
            .statusCode(200)
            .body("isbn", org.hamcrest.Matchers.equalTo(isbn))
            .body("title", org.hamcrest.Matchers.equalTo(titulo));
    }
}
