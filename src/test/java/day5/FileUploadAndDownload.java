package day5;

import org.testng.Assert;
import org.testng.annotations.Test;


import  io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

public class FileUploadAndDownload {

    //@Test
    void singleFileUpload()

    {
        File myfile= new File("/Users/amit/Downloads/Virtual Q.pdf");  //Declaring the file path using `File` class

        given()

                .multiPart("file",myfile)           // Using `multipart` method and refer file path variable in Given() to upload file.
                .contentType("multipart/form-data")         //Declaring input type of file in Given()

        .when()
                .post("http://localhost:8080/uploadFile")

        .then()
                .statusCode(200)
                .body("fileName", equalTo("Test1.txt"))
                .log().all();
    }

    //@Test
    void multipleFileUpload()


    // Declaring the multiple file paths using `File` class
    {
        File myfile= new File("/Users/amit/Downloads/Virtual Q.pdf");
        File myfile2= new File("/Users/amit/Downloads/Virtual Q2.pdf");

        given()

                .multiPart("files",myfile)
                .multiPart("files",myfile2)
                .contentType("multipart/form-data")

                .when()
                .post("http://localhost:8080/uploadFile")

                .then()
                .statusCode(200)
                //Validate the file names present in the anonymous array
                .body("[0].fileName", equalTo("Test1.txt"))     //Declaring input type of multiple files in Given()
                .body("[1].fileName", equalTo("Test1.txt"))
                .log().all();
    }

    @Test
    void fileDownload()
    {

        given()

                .when()
                //Declaring downloaded file URL in GET request
                .get("http://localhost:8080/downloadFile/test1.txt")

                .then()
                .statusCode(200)
                .log().all();
    }
}