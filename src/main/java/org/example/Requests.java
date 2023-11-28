package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.example.models.JobUser;
import org.example.models.RegisterUser;

import static io.restassured.RestAssured.given;

@Log4j2
public class Requests {

    Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

    @Step("Perform GET Users request")
    public Response getUsers(int page){
        log.info("Performing GET Users request");
        return given().contentType("application/json")
                .queryParam("page",page).when().get("/api/users");
    }

    @Step("Perform GET Single User request")
    public Response getSingleUser(int userId){
        log.info("Performing GET Single User request: userID = " + userId);
        return given().contentType("application/json")
                .when().get("/api/users/" + userId);
    }

    @Step("Perform GET Resources request")
    public Response getResources(){
        log.info("Preforming GET Resources request");
        return given().contentType("application/json")
                .when().get("/api/unknown");
    }

    @Step("Perform GET Single Resource request")
    public Response getSingleResource(int resId){
        log.info("Performing GET Single Resource: resID = " + resId);
        return given().contentType("application/json")
                .when().get("/api/unknown/" + resId);
    }

    @Step("Perform POST Create User request")
    public Response postCreateUser(JobUser user){
        log.info("Performing POST Create User request: user = " + user.getName());
        return given().contentType("application/json")
                .body(gson.toJson(user))
                .when().post("/api/users");

    }

    @Step("Perform PUT Update User request")
    public Response putUpdateUser(JobUser user){
        log.info("Performing PUT Update User request: user = " + user.getName());
        return given().contentType("application/json")
                .body(gson.toJson(user))
                .when().put("/api/users/2");

    }

    @Step("Perform PATCH Update User request")
    public Response patchUpdateUser(JobUser user){
        log.info("Performing PATCH Update User request: user = " + user.getName());
        return given().contentType("application/json")
                .body(gson.toJson(user))
                .when().patch("/api/users/2");

    }

    @Step("Perform DELETE User request")
    public Response deleteUser(int userId){
        log.info("Performing DELETE User request");
        return given().contentType("application/json")
                .when().delete("/api/users/" + userId);

    }

    @Step("Perform POST Register User request")
    public Response registerUser(RegisterUser user){
        log.info("Performing PUT Register User request: user = " + user.getEmail());
        return given().contentType("application/json")
                .body(gson.toJson(user))
                .when().post("/api/register");

    }

    @Step("Perform POST Login User request")
    public Response loginUser(RegisterUser user){
        log.info("Performing PUT Login User request: user = " + user.getEmail());
        return given().contentType("application/json")
                .body(gson.toJson(user))
                .when().post("/api/login");
    }

    @Step("Perform GET Users request with Delay")
    public Response getUsersDelay(int page, int delay){
        log.info("Performing GET Users request with delay");
        return given().contentType("application/json")
                .queryParam("page",page)
                .queryParam("delay", delay)
                .when().get("/api/users");
    }
}
