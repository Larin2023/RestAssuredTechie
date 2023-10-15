package day8APIChaining;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;

import com.github.javafaker.Faker;

/////////////////   we have to execute these test only through XML file

public class CreateUser {

    @Test
    void test_CreateUser(ITestContext context) {                // to make this id available for other tests we  have to pass "ITestContext context" parameter to this test method
// we have to  create a data
//we have to create  a request body
        Faker  faker = new Faker();

        JSONObject data = new JSONObject();
// add the data in the Json objec
        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        String bearerToken = "cedd86ff596a272a45db2a3d91c1c0526b6ff5b50c5d928f38d177c19d791063";

        int id=given()          // capture the ID from the response
                .headers("Authorization","Bearer "+bearerToken)  //most of the times we pass Token along with the headers
                .contentType("application/json")
                .body(data.toString())

        .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        System.out.println("Generated ID is : " +id);

        // use this ID in another test


        /////////////////////////////////////////this particular statement will make this ID available to other tests
        //context.setAttribute("user_id", id);                      //  this is variable is available only   in the test level
        context.getSuite().setAttribute("user_id", id);         // this is available at the suit level

    }

}