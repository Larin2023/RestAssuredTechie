package day7;

import org.testng.Assert;
import org.testng.annotations.Test;


import  io.restassured.http.ContentType;
//import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class Authentications {


    //1 ) Basic Authentication
    //@Test(priority=1)
    void testBasicAuthentication() {

        given()
                .auth().basic("postman", "password")   //Declaring Basic Authentication in Given()

                .when()
                .get("https://postman-echo.com/basic-auth")

                // Gather JSON response for same URL in POSTMAN
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    //2 ) Digest Authentication
    //@Test(priority=2)
    void testDigestAuthentication() {

        given()
                .auth().digest("postman", "password")       // Declaring Digest Authentication

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }


    //3 ) Preemptive Authentication
    //@Test(priority=3)
    void testPreemptiveAuthentication() {

        given()
                .auth().preemptive().basic("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    //4 ) BearerToken Authentication
    //@Test(priority=4)
    void testBearerTokenAuthentication() {

        // Generate Bearer token from GITHUB account

        // Use that Bearer Token in POSTMAN & gather JSON response for same URL

        String bearerToken="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";           //Store same Bearer token in variable in same class

        given()
                .headers("Authorization","Bearer "+bearerToken)     //Refer that variable and declare Bearer token Authentication

        .when()
                .get("https://api.github.com/user/repos")

        .then()
                .statusCode(200)
                .log().all();

    }

    //5) OAuth1 Authentication

    //@Test(priority=5)
    void testOAuth1Authentication() {

        given()
                .auth().oauth("ConsumerKey","ConsumerSecret" ,"accessToken" ,"tokenSecret" )

                .when()
                .get("url")

                .then()
                .statusCode(200)
                .log().all();
    }

    //6) OAuth2 Authentication
    //@Test(priority=6)
    void testOAuth2Authentication() {



        given()
                .auth().oauth2("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" )

                .when()
                .get("https://api.github.com/user/repos")

                .then()
                .statusCode(200)
                .log().all();



    }

    //7) API Key Authentication
    @Test(priority=7)
    void testApiKeyAuthentication() {



        given()
                .queryParam("appid", "6384bf93ae2b1ea09c801e0a5a10f835")
                .pathParam("mypath", "data/2.5/forecast/daily")
                .queryParam("q", "Delhi")
                .queryParam("units", "metric")
                .queryParam("cnt", "7")

                .when()
                .get("api.openweathermap.org/{mypath}")

                .then()
                .statusCode(200)
                .log().all();

    }
}