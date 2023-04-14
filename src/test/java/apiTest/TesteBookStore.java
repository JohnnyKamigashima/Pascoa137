package apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteBookStore {

    Gson gson = new Gson();

    static String ct = "application/json";
    static String uriAccount = "https://bookstore.toolsqa.com/Account/v1/";
    static String uriBookStore = "https://bookstore.toolsqa.com/BookStore/v1/";

    static String username = "Ciclano";

    static String password = "Senha@1234567";
    static String jsonBody;
    static String userId;
    static String token;
    static String isbnGit;
    static String isbnJS;
    static String isbnASP;
    static String isbnSpeakingJS;

    @Test
    @Order(1)
    public void testeCriarUsuario() {

        Account account = new Account();
        account.userName = username;
        account.password = password;

        jsonBody = gson.toJson(account);

        Response resp = (Response) given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriAccount + "User")
        .then()
                .log().all()
                .statusCode(201)
                .body("username", is(username))
                .extract();

        userId = resp.jsonPath().getString("userID");
        System.out.println("userId: " + userId);

    }

    @Test
    @Order(2)
    public void testarTokenUser() {
        //realizar o Teste
        Response resp = (Response) given()                                            //Dados
                .contentType(ct)                           //o tipo de conteúdo
                .log().all()                               //mostre tudo
                .body(jsonBody)
        .when()                                            //Quando
                .post(uriAccount + "GenerateToken") //endpoint / onde
        .then()
                .log().all()                               //Mostre na volta
                .statusCode(200)                        //comunicação ida e volta ok
                .extract();

        token = resp.jsonPath().getString("token");
    } // fim do post

    @Test
    @Order(3)
    public void testeVerificarAutorizacao() {

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriAccount + "Authorized")
        .then()
                .log().all()
                .statusCode(200)
        ;

    }

    @Test
    @Order(4)
    public void testeConsultarUsuario() {

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
        .when()
                .get(uriAccount + "User/" + userId)
        .then()
                .log().all()
                .statusCode(200)
                .body("userId", is(userId))
                .body("username", is(username))
        ;
    }

    @Test
    @Order(6)
    public void testeConsultarLivros() {

        Response resp = (Response) given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
        .when()
                .get(uriBookStore + "Books")
        .then()
                .log().all()
                .statusCode(200)
                .extract();

        isbnGit = resp.jsonPath().getString("books[0].isbn");
        isbnJS = resp.jsonPath().getString("books[1].isbn");
        isbnASP = resp.jsonPath().getString("books[2].isbn");
        isbnSpeakingJS = resp.jsonPath().getString("books[3].isbn");
    }

    @Test
    @Order(7)
    public void testeConsultarLivro() {

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
        .when()
                .get(uriBookStore + "Book?ISBN=" + isbnGit)
        .then()
                .log().all()
                .statusCode(200)
                .body("isbn", is(isbnGit))
                .body("title", is("Git Pocket Guide"))

        ;
    }

    @Test
    @Order(8)
    public void testeCadastrarLivros() {

        BookStore book = new BookStore();

        book.userId = userId;

        book.collectionOfIsbns = new BookStore.ISBN[]{
                new BookStore.ISBN(isbnGit),
                new BookStore.ISBN(isbnJS),
                new BookStore.ISBN(isbnASP)
        };

        jsonBody = gson.toJson(book);

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
                .body(jsonBody)
                .when()
                .post(uriBookStore + "Books")
                .then()
                .log().all()
                .statusCode(201)
                .body("books[0].isbn", is(isbnGit))
                .body("books[1].isbn", is(isbnJS))
                .body("books[2].isbn", is(isbnASP))
        ;
    }

    @Test
    @Order(9)
    public void testeDeletarLivro() {

        BookStore book = new BookStore();
        book.userId = userId;
        book.isbn = isbnASP;

        jsonBody = gson.toJson(book);

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
                .body(jsonBody)
        .when()
                .delete(uriBookStore + "Book")
        .then()
                .log().all()
                .statusCode(204)
        ;
    }

    @Test
    @Order(10)
    public void testeAlterarLivro() {

        BookStore book = new BookStore();
        book.userId = userId;
        book.isbn = isbnSpeakingJS;

        jsonBody = gson.toJson(book);

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
                .body(jsonBody)
        .when()
                .put(uriBookStore + "Books/" + isbnJS)
        .then()
                .log().all()
                .statusCode(200)
                .body("userId", is(userId))
                .body("username", is(username))
        ;
    }

    @Test
    @Order(11)
    public void testeDeletarLivros() {

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
        .when()
                .delete(uriBookStore + "Books?UserId=" + userId)
        .then()
                .log().all()
                .statusCode(204)
        ;

    }

    @Test
    @Order(20)
    public void testeDeletarUsuario() {

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
        .when()
                .delete(uriAccount + "User/" + userId)
        .then()
                .log().all()
                .statusCode(204)
        ;
    }
}
