package test;

import endpoints.UserEndPoints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

//import api.endpoints.UserEndPoints;
//import api.payload.User;
import io.restassured.response.Response;
import payload.User;

public class UserTests {

    Faker faker;
    User userPayload;

    public Logger logger; // for logs

    // In BeforeClass, I will  generate the data by using Faker Library
    //that partly data  I will pass to the pojo class so that the
    //data will be ready and that data we will pass along with the post request

    @BeforeClass
    public void setup()
    {
        faker=new Faker();
        userPayload=new User();                 // whatever data we prepared by using this Faker the same data we have to pass to this pojo class

    userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger= LogManager.getLogger(this.getClass());

        logger.debug("debugging.....");

    }

    @Test(priority=1)
    public void testPostUser()
    {
        logger.info("********** Creating user  ***************");
        Response response= UserEndPoints.createUser(userPayload);  // call the end point
        response.then().log().all();                                // get the response

        Assert.assertEquals(response.getStatusCode(),200);  // validation

        logger.info("**********User is creatged  ***************");

    }

    @Test(priority=2)
    public void testGetUserByName()
    {
        logger.info("********** Reading User Info ***************");

        // Call a method from "UserEndPoints" class and pass other method in parameter in it using "This" keyword to use same username
        Response response=UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("**********User info  is displayed ***************");

    }

    @Test(priority=3)
    public void testUpdateUserByName()
    {
        logger.info("********** Updating User ***************");

        //update data using payload. Whichever the data to update we need to copy those Userpayload code.
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response=UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("********** User updated ***************");

        //Checking data after update
        Response responseAfterupdate=UserEndPoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterupdate.getStatusCode(),200);

    }

    @Test(priority=4)
    public void testDeleteUserByName()
    {
        logger.info("**********   Deleting User  ***************");

        Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("********** User deleted ***************");
    }


}