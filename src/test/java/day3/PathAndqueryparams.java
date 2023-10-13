package day3;

import org.testng.annotations.Test;
import org.json.JSONObject;
import org.json.JSONTokener;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;


// https://reqres.in/api/users?page=2&id=5

public class PathAndqueryparams {

    @Test
    void testQueryAndPathParameters()
    {
                                    //"users?page=2&id=5"we will Define them inside the prerequisite inside the given section
        given()
                .pathParam("mypath", "users") //"mypath" is a name , and "users" is a value
                .queryParam("page", "2") //"page" is a name , and "2" is a value
                .queryParam("id", "5") //"id" is a name , and "5" is a value
        .when()
                .get("https://reqres.in/api/{mypath}")          // in the curly brace specify the name of the path parameter

        .then()
                .statusCode(200)
                .log().all()	;


    }

}