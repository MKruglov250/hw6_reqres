package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.example.models.JobUser;
import org.example.models.RegisterUser;

@Log4j2
public class Requests extends BaseApi{

    Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

    @Step("Perform GET Users request")
    public Response getUsers(int page, int statusCode){
        log.info("Performing GET Users request");
        requestSpecification.queryParam("page",page);
        return get("/api/users", statusCode);
    }

    @Step("Perform GET Single User request")
    public Response getSingleUser(int userId, int statusCode){
        log.info("Performing GET Single User request: userID = " + userId);
        return get("/api/users/" + userId, statusCode);
    }

    @Step("Perform GET Resources request")
    public Response getResources(int statusCode){
        log.info("Preforming GET Resources request");
        return get("/api/unknown", statusCode);
    }

    @Step("Perform GET Single Resource request")
    public Response getSingleResource(int resId, int statusCode){
        log.info("Performing GET Single Resource: resID = " + resId);
        return get("/api/unknown/" + resId, statusCode);
    }

    @Step("Perform POST Create User request")
    public Response postCreateUser(JobUser user, int statusCode){
        log.info("Performing POST Create User request: user = " + user.getName());
        return post("/api/users", statusCode, gson.toJson(user));
    }

    @Step("Perform PUT Update User request")
    public Response putUpdateUser(JobUser user, int statusCode){
        log.info("Performing PUT Update User request: user = " + user.getName());
        return put("/api/users/2", statusCode, gson.toJson(user));
    }

    @Step("Perform PATCH Update User request")
    public Response patchUpdateUser(JobUser user, int statusCode){
        log.info("Performing PATCH Update User request: user = " + user.getName());
        return patch("/api/users/2", statusCode, gson.toJson(user));
    }

    @Step("Perform DELETE User request")
    public Response deleteUser(int userId){
        log.info("Performing DELETE User request");
        return delete("/api/users/" + userId, HttpStatus.SC_NO_CONTENT);
    }

    @Step("Perform POST Register User request")
    public Response registerUser(RegisterUser user, int statusCode){
        log.info("Performing PUT Register User request: user = " + user.getEmail());
        return post("/api/register", statusCode, gson.toJson(user));

    }

    @Step("Perform POST Login User request")
    public Response loginUser(RegisterUser user, int statusCode){
        log.info("Performing PUT Login User request: user = " + user.getEmail());
        return post("/api/login", statusCode, gson.toJson(user));
    }

    @Step("Perform GET Users request with Delay")
    public Response getUsersDelay(int page, int delay, int statusCode){
        log.info("Performing GET Users request with delay");
        requestSpecification
                .queryParam("page",page)
                .queryParam("delay", delay);
        return get("/api/users", statusCode);
    }
}
