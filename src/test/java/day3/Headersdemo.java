package day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;


public class Headersdemo {

    //@Test(priority=1)
    void testHeaders( ) {

        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .header("Content-Encoding", "gzip")
                .header("server", "gws");


    }

    @Test(priority=2)
    void getheaders() {

        Response res =
                given()
                        .when()
                        .get("https://www.google.com/");

        // 1) get single header info

        //String headerValue =res.getHeader("Content-Type");
        //System.out.println("The value of content type header is  " +headerValue);

        // 2)get all headers info

        Headers myheaders= res.getHeaders();

        for(Header hd:myheaders)
        {
            System.out.println(hd.getName()+"      "+hd.getValue());
        }



    }

}