package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {


    // Add javafaker dependencies in pom.xml
    @Test
    void testGenerateDummyData() {

        Faker faker= new  Faker();

        // Create data using FAKER class

        String fullname=faker.name().fullName();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();


        String username=faker.name().username();
        String password=faker.internet().password();

        String phoneno=faker.phoneNumber().cellPhone();
        String emailadd= faker.internet().safeEmailAddress();

        System.out.println(fullname);
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(username);
        System.out.println(password);
        System.out.println(phoneno);
        System.out.println(emailadd);
    }

}