package day8APIChaining;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;

public class GetUser {

    @Test
    void test_getUser(ITestContext context) {               // we should pass "context"


        // this particular method will get the value  from the user ID which is generated in my previous request
        //int id = (Integer) context.getAttribute("user_id");               //this should come from create user request

        // // when I put getSuite() this test becomes available at the suit level
        int id = (Integer) context.getSuite().getAttribute("user_id");  //this should come from create user request

        String bearerToken = "cedd86ff596a272a45db2a3d91c1c0526b6ff5b50c5d928f38d177c19d791063";

        given()
                .headers("Authorization","Bearer "+bearerToken)
                .pathParam("id", id)

        .when()
                .get("https://gorest.co.in/public/v2/users/{id}")

        .then()
                .statusCode(200)
                .log().all();


    }
}