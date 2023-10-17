package SmarotHelper;

import com.github.javafaker.Faker;
import SmarotModels.Category;
import SmarotModels.Pet;
import SmarotModels.Status;
import SmarotModels.Tag;

import java.util.Random;

public class TestDataGenerator {


    /// она возвращает рандомна животное мы сделали объект

    public static Pet generateRandomPet(){
        Faker faker = new Faker();
        Random random = new Random();
        Category category = new Category(random.nextInt(),faker.funnyName().name());
        String petName = faker.animal().name();
        String[]photoUrls = {faker.internet().url()};
        Tag tag = new Tag(random.nextInt(), faker.book().title());
        Tag[]tags = {tag};
        return new Pet(Status.available,petName,photoUrls,category,tags);
    }
}