package day2;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;              // import packages manually

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

/*
 * Different ways to create Post request Body
 * 1) Using Hashmap
 * 2) Using Org.json
 * 3) POJO class
 * 4) Using external JSON data
 * */

public class DiffrentWaysToCreatePostRequestBody {

    //  * 1) Using Hashmap

    //@Test(priority=1)
    void testPostusingHashMap() {

        HashMap data = new HashMap();           // 1)) create object

        data.put("name", "Scott");              // 2)) now  we can store the data in the form of key and a value base
        data.put("location", "France");
        data.put("phone", "123456");

        String courseArr[]= {"C","C++"};            // 3)) we have to create Java array to store 2 "courses"
        data.put("courses", courseArr);             // 4)) add this courseArr into the hash map "data" as  a single value


        given()
                .contentType("application/json")
                .body(data)

        .when()
                .post("http://localhost:3000/students")

        .then()         //  5)) usually we don't need to compare each and every field  most of the time just verify one or two fields   that is enough
                .statusCode(201)
                .body("name", equalTo("Scott"))             // validate fields:
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]",equalTo("C"))            // validate array:
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")       // validate header
                .log().all();


    }


    // * 2) Using Org.json

    //@Test(priority=1)
    void testPostusingOrgjson() {

        // if you add "JSONObject" dependency then we will get this class JSONObject.
        JSONObject data = new JSONObject();     // we have to create Json object  variable
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        String coursesArr[]= {"C","C++"};
        data.put("courses", coursesArr);            // we have added this array to the  courses variable

        given()
                .contentType("application/json")
                .body(data.toString())               // !!!!!  we cannot directly pass the data inside the body so what we have to do is we have to  convert this into string format and then we will able to send

        .when()
                .post("http://localhost:3000/students")

        .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }

    // 3) Post request body using POJO class  (THE MOST POPULAR WAY)

    // we have to create one POJO class "public class Pojo_Postrequest" and from that  we have to capture the data

    //@Test(priority=1)
    void testPostusingPOJOClass() {


        Pojo_Postrequest data = new Pojo_Postrequest ();            // create an  object of pojo class

        data.setName("Scott");              // use .set method to set the data into the variables
        data.setLocation("France");
        data.setPhone("123456");

        String courseArr[]= {"C","C++"};
        data.setCourses(courseArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }

            // 4) Using External JSON File

    //I will create one file "body.json" and this file contains the data

    @Test(priority=1)
    void testPostusingExternalJsonfile() throws FileNotFoundException {

        // first of all we have to open the file
        File f= new File("/Users/amit/eclipse-workspace/RestAssuredTraining/body.json");

        FileReader fr= new FileReader(f);       // read the data from  the file

        JSONTokener jt =new JSONTokener(fr);        // we have to split that file into different tokens

        JSONObject data = new JSONObject(jt);       //we have to extract the data in the Json  format



        given()
                .contentType("application/json")
                .body(data.toString())                  // we have to convert to string format

        .when()
                .post("http://localhost:3000/students")

        .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }



    // 6)) deleting student record
    @Test(priority=2)
    void testDelete()
    {
        given()

        .when()
                .delete("http://localhost:3000/students/4")

        .then()
                .statusCode(200);
    }
}