package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookstoreApiTestBruno {

//    private CSVReader reader;

    @BeforeEach
    public void setUpMethod() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking";
    }

//    @AfterMethod
//    public void tearDown() throws IOException {
//        if (reader != null) {
//            reader.close();
//        }


    @Test
    public void testGetPosts() {
        Response response = RestAssured.given()
                .when()
                .get()
                ;

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, 200);
        assertTrue(responseBody.contains("bookingid"));
//        Assert.assertTrue(responseBody.contains("userId"));
//        Assert.assertTrue(responseBody.contains("title"));
//        Assert.assertTrue(responseBody.contains("body"));
    }

        @ParameterizedTest
        @CsvFileSource(resources = "/user4/bookings.csv", delimiter = ',', numLinesToSkip = 1)
        public void testCreateBooking(String firstname, String lastname, String totalprice, String depositpaid, String checkin, String checkout) {

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body("{ \"firstname\": \"" + firstname + "\", \"lastname\": \"" + lastname + "\", \"totalprice\": " + Double.parseDouble(totalprice) + ", \"depositpaid\": " + Boolean.parseBoolean(depositpaid) + ", \"bookingdates\": { \"checkin\": \"" + checkin + "\", \"checkout\": \"" + checkout + "\" } }")
                    .when()
                    .post();

            int statusCode = response.getStatusCode();
            assertEquals(statusCode, 200);

            String responseBody = response.getBody().asString();
            assertTrue(responseBody.contains("bookingid"));

            int bookingId = response.jsonPath().getInt("bookingid");

            Response getBookingResponse = RestAssured.get("/" + bookingId);
            String getBookingResponseBody = getBookingResponse.getBody().asString();
            assertTrue(getBookingResponseBody.contains(firstname));
            assertTrue(getBookingResponseBody.contains(lastname));
            assertTrue(getBookingResponseBody.contains(totalprice));
            assertTrue(getBookingResponseBody.contains(depositpaid));
            assertTrue(getBookingResponseBody.contains(checkin));
            assertTrue(getBookingResponseBody.contains(checkout));
        }
//
//    @DataProvider(name = "bookingsData")
//    public Object[][] getBookingsData() throws IOException, CsvException {
//        try (CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Raquel\\Pascoa137\\src\\test\\resources\\user4\\bookings.csv"))) {
//            List<String[]> rows = reader.readAll();
//            return rows.toArray(new Object[rows.size()][6]);
//        }
//    }
//
//    @Test(dataProvider = "bookingsData")
//    public void testCreateBooking(String firstname, String lastname, String totalprice, String depositpaid, String checkin, String checkout) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate checkinDate = LocalDate.parse(checkin, formatter);
//        LocalDate checkoutDate = LocalDate.parse(checkout, formatter);
//
//        Response response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body("{ \"firstname\": \"" + firstname + "\", \"lastname\": \"" + lastname + "\", \"totalprice\": " + Double.parseDouble(totalprice) + ", \"depositpaid\": " + Boolean.parseBoolean(depositpaid) + ", \"bookingdates\": { \"checkin\": \"" + checkin + "\", \"checkout\": \"" + checkout + "\" } }")
//                .when()
//                .post();
//
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode, 200);
//
//        String responseBody = response.getBody().asString();
//        Assert.assertTrue(responseBody.contains("bookingid"));
//
//        int bookingId = response.jsonPath().getInt("bookingid");
//
//        Response getBookingResponse = RestAssured.get("/" + bookingId);
//        String getBookingResponseBody = getBookingResponse.getBody().asString();
//        Assert.assertTrue(getBookingResponseBody.contains(firstname));
//        Assert.assertTrue(getBookingResponseBody.contains(lastname));
//        Assert.assertTrue(getBookingResponseBody.contains(totalprice));
//        Assert.assertTrue(getBookingResponseBody.contains(depositpaid));
//        Assert.assertTrue(getBookingResponseBody.contains(checkin));
//        Assert.assertTrue(getBookingResponseBody.contains(checkout));
//    }
}