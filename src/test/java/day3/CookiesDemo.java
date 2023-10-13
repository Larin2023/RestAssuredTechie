package day3;

import org.testng.annotations.Test;

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

public class CookiesDemo {

    // my requirement is through our automation script we want to capture these cookies information

    //test should fail why because it will not be the  same every time if the test is failed means what
    //our cookie is generated our cookies generated  with the new value that is our expectation

    //@Test(priority=1)
    void testCookies( ) {

        given()

        .when()
                .get("https://www.google.com/")

        .then()
                .cookie("AEC","ARSKqsK7JCj6cRAt0PshMJBkUxfmf1_5BO0dZY8HEIpQFaOdMo4gU107Fg")         //here we have to pass key and the value pair
                .log().all();

    }

    @Test(priority=2)
    void getCookiesInfo( ) {

        Response res= given()           //request is sent and once I get a response  the response will be stored in this variable "res"

        .when()
                .get("https://www.google.com/");

        // 1) get single cookie info

        //String cookie_value=res.getCookie("AEC");                      // I am extracting the  cookie information of AEC
        //System.out.println("Value of cookie is ===> " +cookie_value);        // print this value

        // 2) get all cookies info

        Map<String,String> cookie_values =res.getCookies();

        //	System.out.println(cookie_values.keySet());


        for(String k:cookie_values.keySet()) {

            String cookie_value =res.getCookie(k);
            System.out.println(k+ "              " +cookie_value);
        }

    }
}