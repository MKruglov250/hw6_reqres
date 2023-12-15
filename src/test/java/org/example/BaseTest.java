package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import org.example.models.JobUser;
import org.example.models.RegisterUser;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    Gson gson;
    Requests requests = new Requests();
    JobUser user = new JobUser("morpheus", "leader", "", "");
    JobUser putUser = new JobUser("peter", "hero", "", "");
    JobUser patchUser = new JobUser("harry", "wizard", "", "");
    RegisterUser regUser = new RegisterUser("eve.holt@reqres.in","12345");
    RegisterUser badUser = new RegisterUser("mark@mail.com","");

    @AfterMethod
    public void restoreSpecification(){
        requests.restoreSpecification();
    }

    public BaseTest() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        RestAssured.baseURI="https://reqres.in/";
    }
}