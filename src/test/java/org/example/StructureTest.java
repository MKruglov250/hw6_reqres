package org.example;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Log4j2
public class StructureTest extends BaseTest {

    public StructureTest() {
    }

    @AfterMethod(description = "Log symbols")
    public void logDivision(){
        log.info("----------------------***TEST FINISHED***----------------------");
    }
    @Test(description = "Check Model: GET List Users request")
    public void getUsers(){
        log.info("Checking Model for List Users request");
        Response response = requests.getUsers(2);
        log.info("Check response matches schema: List Users");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/listUsers.json"));
    }

    @Test(description = "Check Model: GET Single User request")
    public void getSingleUser(){
        log.info("Checking Model for Single User request");
        Response response = requests.getSingleUser(12);
        log.info("Check response matches schema: Single User");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/singleUser.json"));
    }

    @Test(description = "Check Model: GET Resources request")
    public void getResources(){
        log.info("Checking Model for all Resources request");
        Response response = requests.getResources();
        log.info("Check response matches schema: List Resources");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/listResources.json"));
    }

    @Test(description = "Check Model: GET Single Resource request")
    public void getSingleResource(){
        log.info("Checking Model for single Resource request");
        Response response = requests.getSingleResource(2);
        log.info("Check response matches schema: Single Resource");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/singleResource.json"));
    }

    @Test(description = "Check Model: POST For Create User request")
    public void postCreateUser(){
        log.info("Checking Model for Create User request");
        Response response = requests.postCreateUser(user);
        log.info("Check response matches schema: User Created");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/jobsUserCreated.json"));
    }


    @Test(description = "Check Model: PUT For Update User request")
    public void putUpdateUser(){
        log.info("Checking Model for Update User (PUT) request");
        Response response = requests.putUpdateUser(putUser);
        log.info("Check response matches schema: User Updated");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/jobsUserUpdated.json"));
    }

    @Test(description = "Check Model: PATCH For Update User request")
    public void patchUpdateUser(){
        log.info("Checking Model for Update User (PATCH) request");
        Response response = requests.patchUpdateUser(patchUser);
        log.info("Check response matches schema: User Updated");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/jobsUserUpdated.json"));
    }


    @Test(description = "Check Model: POST for Register request")
    public void registerSuccess(){
        log.info("Checking Model for Register (successful)");
        Response response = requests.registerUser(regUser);
        log.info("Check response matches schema: Register new user");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/regUser.json"));
    }


    @Test(description = "Check Model: POST for Login request")
    public void loginSuccess(){
        log.info("Checking Model for Login (successful)");
        Response response = requests.loginUser(regUser);
        log.info("Check response matches schema: Login with existing user");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/logUser.json"));
    }


    @Test(description = "Check Model: GET List Users with Delay request")
    public void getUsersDelay(){
        log.info("Checking Model for List Users request");
        Response response = requests.getUsersDelay(2,3);
        log.info("Check response matches schema: List Users");
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("models/listUsers.json"));
    }

}
