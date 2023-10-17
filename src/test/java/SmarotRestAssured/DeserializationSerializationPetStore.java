package SmarotRestAssured;

import SmarotHelper.TestDataGenerator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import SmarotModels.Pet;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeserializationSerializationPetStore {

    //   Serialization

    private long petId;           // меня надо вторая глобальная переменная
    private Pet pet;         //я буду его везде использовать

    @BeforeMethod
    public void createPet(){                                // я генерирую это животное и отправляя его
        pet = TestDataGenerator.generateRandomPet();
        Response response =
        given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet")
                .body(pet)
                .header("accept","application/json")
                .header("Content-Type", "application/json")
        .when()
                .post()
        .then()
                .statusCode(200)
                .extract()
                .response();
        petId = response.jsonPath().getLong("id");
    }

    /////    Deserialization

    @Test
    public void getPetById(){
        Response response =
        given()
                .baseUri("https://petstore.swagger.io/v2")
//                .basePath("/pet/100")
                .basePath("/pet/"+petId)                //теперь в тесте get я это животное хочу получить
                .header("accept","application/json")
                .header("Content-Type", "application/json")
        .when()
                .get()
        .then()
                .statusCode(200)
                .extract()
                .response();
        JsonPath jsonPath = response.jsonPath();
        Pet petResponse = jsonPath.getObject("$",Pet.class);   //нам надо вытащить кошку
        //смотрите что происходит. с левой стороны Pet это значит
        //что responce будет класса Pet.
        // не какой-то там левой строчкой сtрингом.
        // это будет самый настоящий объект java.
        // эта операция называется операцией дисиреализации
        // здесь произошло очень большое чудо
        // мы превратили string в объект java

        // эта операция позволяет нам полученную из
        //интернета информацию
        //магическим образом конвертировать в объекты джавы

// мне надо Assert что то животное которое я создал Pet это животное которое вернулась response

        Assert.assertEquals(pet.getName(),petResponse.getName());
        Assert.assertEquals(pet.getCategory().getName(),petResponse.getCategory().getName());
        Assert.assertEquals(pet.getCategory().getId(),petResponse.getCategory().getId());
        Assert.assertEquals(pet.getPhotoUrls()[0],petResponse.getPhotoUrls()[0]);

    }
}
