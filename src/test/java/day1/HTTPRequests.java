package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;  // we have to add something called Static because these are the special packages
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


/*

    given()

        content type, set cookies, add auth,add param, set headers info etc...

    when()

        get,post,put,delete

    then()

        validate state code, extract response, extract headers cookies & response body

*/


public class HTTPRequests {

    int id;   //is a global  variable we created


    @Test(priority=1)
    void getUsers() {      // get multiple users

        given()

        .when()
                .get("https://reqres.in/api/users?page=2")

        .then()
                .statusCode(200)                // validate status code
                .body("page",equalTo(2))    //we need to check whichever page ID we have passed in the response
                .log().all();                      // this will display the entire response in the console window


    }

    @Test(priority=2)
    void createUser()
    {
        HashMap data= new HashMap();    // Hash map actually we don't prefer this one in the real projects because if I use hash map  we have to hard code the data
        data.put("name", "Amit");
        data.put("job", "Tester");

        id=given()                      //  before given section we have to create a variable
                .contentType("application/json")
                .body(data)

        .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
        // once you send this post request I don't  want to do any validations here but I want to
        //capture the response in a variable, so I put  semicolon here.
        //how we can send back the response  in the form of Json format?
        // so here we have to use one additional method in the when section. .jsonPath()


        //.then()
        //.statusCode(201)
        //.log().all();

    }

    @Test(priority=3, dependsOnMethods=("createUser"))
    void updateUser()

    {
        HashMap data= new HashMap();
        data.put("name", "Anu");
        data.put("job", "Instagram");

        given()
                .contentType("application/json")
                .body(data)

        .when()
                .post("https://reqres.in/api/users/"+id)            //but here we have to pass ID of the user

        .then()
                .statusCode(201)
                .log().all();

    }

    @Test(priority=4)
    void deleteUser()
    {
        given()

        .when()
                .delete("https://reqres.in/api/users/"+id) // so whatever ID we used for creation and updation, the same ID I am using here for deletion

        .then()
                .statusCode(204)
                .log().all();

    }

}