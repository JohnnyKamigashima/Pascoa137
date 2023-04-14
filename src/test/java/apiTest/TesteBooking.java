package apiTest;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteBooking {

    private final String uri = "https://restful-booker.herokuapp.com/";

    private final String ct = "application/json";

    private static String token;
    private static int bookingId;

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @Test
    @Order(1)
    public void testeCreateToken() throws IOException {

        String jsonBody = lerArquivoJson("src/test/resources/json/bookingAuth.json");

        Response resp = (Response) given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "auth")
        .then()
                .log().all()
                .statusCode(200)
                .extract()
        ;

        token = resp.jsonPath().getString("token");
        System.out.println("Token: " + token);
    }

    @Test
    @Order(2)
    public void testeGetBookingIds() {

        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uri + "booking")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    @Order(3)
    public void testeCreateBooking() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/booking.json");

        Response resp = (Response) given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "booking")
        .then()
                .log().all()
                .statusCode(200)
                .body("booking.firstname", is("Fulano"))
                .body("booking.lastname", is("Ciclano"))
                .extract()
        ;

        bookingId = resp.jsonPath().getInt("bookingid");

    }

    @Test
    @Order(4)
    public void testeGetBooking() {

        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uri + "booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(200)
                .body("firstname", is("Fulano"))
                .body("lastname", is("Ciclano"))
        ;

    }


    @Test
    @Order(5)
    public void testeUpdateBooking() throws IOException {

        String jsonBody = lerArquivoJson("src/test/resources/json/updateBooking.json");

        given()
                .contentType(ct)
                .log().all()
                .header("Cookie", "token=" + token)
                .body(jsonBody)
        .when()
                .put(uri + "booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(200)
                .body("firstname", is("Beltrano"))
                .body("lastname", is("Soprano"))
        ;
    }

    @Test
    @Order(6)
    public void testePartialUpdateBooking() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/patchBooking.json");

        given()
                .contentType(ct)
                .log().all()
                .header("Cookie", "token=" + token)
                .body(jsonBody)
        .when()
                .patch(uri + "booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(200)
                .body("totalprice", is(300))
                .body("additionalneeds", is("Dinner"))
                .body("bookingdates.checkout", is("2023-04-01"))
        ;
    }

    @Test
    @Order(7)
    public void testeDeleteBooking() {

        given()
                .contentType(ct)
                .log().all()
                .header("Cookie", "token=" + token)
        .when()
                .delete(uri + "booking/" + bookingId)
        .then()
                .log().all()
                .statusCode(201)
                .body(is("Created"))
        ;
    }
}
