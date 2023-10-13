package day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;


public class LoggingDemo {


    @Test(priority=1)
    void testLogs() {

        given()

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .log().all();
    }

}