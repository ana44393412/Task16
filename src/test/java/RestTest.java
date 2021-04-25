import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.pet.Category;
import models.pet.Info;
import models.pet.Pet;
import models.pet.TagsItem;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestTest {

    private Pet myPet;

    @BeforeEach
    public void init() {
        Category category = new Category();
        category.setId(44);
        category.setName("Кот");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("mollit");
        photoUrls.add("veniam mollit fugiat aliqua consectetur");

        TagsItem tag1 = new TagsItem();
        tag1.setId(55);
        tag1.setName("dolor occaecat anim consectetur");
        TagsItem tag2 = new TagsItem();
        tag2.setId(111);
        tag2.setName("Ut commodo mollit");

        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);

        myPet = new Pet();
        myPet.setId(335566);
        myPet.setName("Tom");
        myPet.setCategory(category);
        myPet.setStatus("sold");
        myPet.setPhotoUrls(photoUrls);
        myPet.setTags(tags);
    }

    @Test
    @Order(1)
    public void post() {
        Pet restAssuredPet = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200)
                .extract()
                .as(Pet.class);
        Assertions.assertEquals(restAssuredPet, myPet);
    }

    @Test
    @Order(2)
    public void get200() {
        Pet restAssuredPet = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("https://petstore.swagger.io/v2/pet/335566")
                .then()
                .statusCode(200)
                .extract()
                .as(Pet.class);
        Assertions.assertEquals(restAssuredPet, myPet);
    }

    @Test
    @Order(3)
    public void get404() {
        Info info = new Info();
        info.setCode(1);
        info.setMessage("Pet not found");
        info.setType("error");

        Info restAssuredInfo = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get("https://petstore.swagger.io/v2/pet/999222333")
                .then()
                .statusCode(404)
                .extract()
                .as(Info.class);
        Assertions.assertEquals(restAssuredInfo, info);
    }

    @Test
    @Order(4)
    public void put() {
        Category categoryPost = new Category();
        categoryPost.setId(41943976);
        categoryPost.setName("dolor");

        List<String> photoUrlsPost = new ArrayList<>();
        photoUrlsPost.add("consequat");
        photoUrlsPost.add("reprehenderit");

        TagsItem tag1Post = new TagsItem();
        tag1Post.setId(1232);
        tag1Post.setName("in fugiat pariatur");
        TagsItem tag2Post = new TagsItem();
        tag2Post.setId(1122);
        tag2Post.setName("non commodo");

        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag1Post);
        tags.add(tag2Post);

        Pet myPetPost = new Pet();
        myPetPost.setId(335566);
        myPetPost.setName("Tom");
        myPetPost.setCategory(categoryPost);
        myPetPost.setStatus("pending");
        myPetPost.setPhotoUrls(photoUrlsPost);
        myPetPost.setTags(tags);

        Pet restAssuredPetPost = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPetPost)
                .put("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200)
                .extract()
                .as(Pet.class);
        Assertions.assertEquals(restAssuredPetPost, myPetPost);
    }

    @Test
    @Order(5)
    public void delete200() {
        Info info = new Info();
        info.setCode(200);
        info.setMessage("335566");
        info.setType("unknown");

        Info restAssuredInfo = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete("https://petstore.swagger.io/v2/pet/335566")
                .then()
                .statusCode(200)
                .extract()
                .as(Info.class);
        Assertions.assertEquals(restAssuredInfo, info);
    }

    @Test
    @Order(6)
    public void delete404() {
        int statusCode = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete("https://petstore.swagger.io/v2/pet/335566")
                .getStatusCode();
        Assertions.assertEquals(statusCode, 404);
    }

}
