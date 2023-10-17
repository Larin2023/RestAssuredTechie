package test;

import endpoints.UserEndPoints;
import org.testng.Assert;
import org.testng.annotations.Test;

//import api.endpoints.UserEndPoints;
//import api.payload.User;
//import api.utilities.DataProviders;
import io.restassured.response.Response;
import payload.User;
import utilities.DataProviders;

public class DDTests {

// // we have to specify which data  provider is giving the data to this test method "Data" // we have to specify where exactly the data  provider method is coming from dataProviderClass=DataProviders.class
    @Test(priority=1, dataProvider="Data", dataProviderClass= DataProviders.class )  //  we have to create multiple parameters should be the same order
    public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
    {
        //Create object for POJO class
        User userPayload=new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response= UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }

// DELETE USER
    @Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
    public void testDeleteUserByName(String userName)
    {
        Response response=UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);

    }



}