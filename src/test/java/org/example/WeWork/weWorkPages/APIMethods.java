package org.example.WeWork.weWorkPages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.base.AppiumConfiguration;
import org.testng.Assert;

public class APIMethods extends AppiumConfiguration {

    private static final String AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2YTg3ZTY4MDMyODIwMTIzZmQ0Yzg0YjQzNDhjYjc3ZCIsInN1YiI6IjY2Mjg5NDExOTFmMGVhMDE0YjAwOWU1ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6zIM73Giwg5M4wP6MX8KDCpee7IMnpnLTZUyMpETb08";
    private static final String AUTH_TOKEN_ERROR = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2YTg3ZTY4MDMyODIwMTIzZmQ0Yzg0YjQzNDhjYjc3ZCIsInN1YiI6IjY2Mjg5NDExOTFmMGVhMDE0YjAwOWU1ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ";
    private static final String ERROR_LANG = "sdjhfjkh";
    private static final String ERROR_PAGE_COUNT = "-1";

    public static void GETConfig() {
        RestAssured.baseURI = "https://api.themoviedb.org/3/configuration";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("authorization", AUTH_TOKEN);

        Response response = request.log().all().get();
        Assert.assertEquals(response.getStatusCode(), 200, "Response code is not 200");
        System.out.println(response.asPrettyString());
    }

    public static void GETNowPlaying() {
        RestAssured.baseURI = "https://api.themoviedb.org/3/movie/now_playing";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("authorization", AUTH_TOKEN);
        request.queryParam("language", "en-US");
        request.queryParam("page", "1");


        Response response = request.log().all().get();
        Assert.assertEquals(response.getStatusCode(), 200, "Response code is not 200");
        System.out.println(response.asPrettyString());
    }

    public static void GETTopRated() {
        RestAssured.baseURI = "https://api.themoviedb.org/3/movie/top_rated";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("authorization", AUTH_TOKEN);
        request.queryParam("language", "en-US");
        request.queryParam("page", "1");


        Response response = request.log().all().get();
        Assert.assertEquals(response.getStatusCode(), 200, "Response code is not 200");
        System.out.println(response.asPrettyString());
    }

    public static void GETConfig_withErrorToken() {
        RestAssured.baseURI = "https://api.themoviedb.org/3/configuration";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("authorization", AUTH_TOKEN_ERROR);

        Response response = request.log().all().get();
        Assert.assertEquals(response.getStatusCode(), 401, "Response code is not 401");
        String status_message = response.jsonPath().get("status_message").toString();
        System.out.println(status_message);
        Assert.assertTrue(status_message.contains("Invalid API key"));
    }

    public static void GETNowPlaying_withErrorPageCount() {
        RestAssured.baseURI = "https://api.themoviedb.org/3/movie/now_playing";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("authorization", AUTH_TOKEN);
        request.queryParam("language", "en-US");
        request.queryParam("page", ERROR_PAGE_COUNT);


        Response response = request.log().all().get();
        Assert.assertEquals(response.getStatusCode(), 400, "Response code is not 400");
        String status_message = response.jsonPath().get("status_message").toString();
        System.out.println(status_message);
        Assert.assertTrue(status_message.contains("Invalid page"));
    }

    public static void GETTopRated_withErrorLanguage() {
        RestAssured.baseURI = "https://api.themoviedb.org/3/movie/top_rated";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("authorization", AUTH_TOKEN);
        request.queryParam("language", ERROR_LANG);
        request.queryParam("page", "1");


        Response response = request.log().all().get();
        Assert.assertNotEquals(response.getStatusCode(), 200, "Response code must be other than 200 as wrong params passed");
    }
}
