package day6;

import org.testng.Assert;
import org.testng.annotations.Test;


import  io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;


/*
 * json-->jsonschema converter
 * https://jsonformatter.org/json-to-jsonschema
 */

                                        //Copy that schema into a file and save it in the resources folder of the same project.
public class JsonSchemaValidation {

    @Test
    void jsonschemaValidationTest()
    {

        given()

        .when()
                .get("http://localhost:3000/store")

        .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storejsonschema.json"));
    }
}