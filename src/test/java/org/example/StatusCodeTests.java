package org.example;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Log4j2
public class StatusCodeTests extends BaseTest {

    @AfterMethod(description = "Log symbols")
    public void logDivision(){
        log.info("----------------------***TEST FINISHED***----------------------");
    }

    @Test(description = "Check Status Code: GET List Users")
    public void getUsers(){
        log.info("Checking Status Code for List Users request");
        Response response = requests.getUsers(2);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Check Status Code: GET Single User")
    public void getSingleUser(){
        log.info("Checking Status Code for Single User request");
        Response response = requests.getSingleUser(2);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Check Status Code: GET Single User Not Found")
    public void getSingleUserNotFound(){
        log.info("Checking Status Code for non-existing Single User request");
        Response response = requests.getSingleUser(23);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_NOT_FOUND);
    }

    @Test(description = "Check Status Code: GET Resources")
    public void getResources(){
        log.info("Checking Status Code for all Resources request");
        Response response = requests.getResources();
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Check Status Code: GET Single Resource")
    public void getSingleResources(){
        log.info("Checking Status Code for single Resource request");
        Response response = requests.getSingleResource(2);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Check Status Code: GET Single Resource not found")
    public void getResourcesNotFound(){
        log.info("Checking Status Code for non-existing Resource");
        Response response = requests.getSingleResource(23);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_NOT_FOUND);
    }

    @Test(description = "Check Status Code: POST For Create User")
    public void postCreateUser(){
        log.info("Checking Status Code for Create User request");
        Response response = requests.postCreateUser(user);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_CREATED);
    }

    @Test(description = "Check Status Code: PUT For Update User")
    public void putUpdateUser(){
        log.info("Checking Status Code for Update User (PUT) request");
        Response response = requests.putUpdateUser(putUser);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Check Status Code: PATCH For Update User")
    public void patchUpdateUser(){
        log.info("Checking Status Code for Update User (PATCH) request");
        Response response = requests.patchUpdateUser(patchUser);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Check Status Code: DELETE For Delete User")
    public void deleteUser(){
        log.info("Checking Status Code for DELETE user");
        Response response = requests.deleteUser(2);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_NO_CONTENT);
    }

    @Test(description = "Check Status Code: POST for Register Successful")
    public void registerSuccess(){
        log.info("Checking Status Code for Register (successful)");
        Response response = requests.registerUser(regUser);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Check Status Code: POST for Register Fail")
    public void registerFail(){
        log.info("Checking Status Code for Register (failed)");
        Response response = requests.registerUser(badUser);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Check Status Code: POST for Login Success")
    public void loginSuccess(){
        log.info("Checking Status Code for Login (successful)");
        Response response = requests.loginUser(regUser);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Check Status Code: POST for Login Fail")
    public void loginFail(){
        log.info("Checking Status Code for Register (failed)");
        Response response = requests.loginUser(badUser);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
    }

    @Test(description = "Check Status Code: GET List Users with Delay")
    public void getUsersDelay(){
        log.info("Checking Status Code for List Users request");
        Response response = requests.getUsersDelay(2,3);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

}
