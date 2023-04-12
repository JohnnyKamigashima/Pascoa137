package apiTest;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testeUsuarioWell {
    static String ct = "application/json";
    static String uriUser ="https://petstore.swagger.io/v2/user/";


    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String (Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @Test
    @Order(1)
    void incluirUsuario() throws IOException {
        String corpoDoJson = lerArquivoJson("src/test/resources/json/cliente1.json");
        String usuarioId = "383976218";

        given()
                .contentType(ct)
                .log().all()
                .body(corpoDoJson)
                .when()
                .post(uriUser)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(usuarioId));
    }

    @Test
    @Order(2)
    public void ConsultarGetUser(){
        String username = "Maria";
        int userId = 383976218;
        String email = "marias@hotmail.com";
        String senha = "123";
        given()
                .contentType(ct)
                .log().all()
                .when()
                .get(uriUser + username)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userId))
                .body("email", is(email))
                .body("password",is(senha));

    }
}
