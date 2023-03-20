package ru.stellarburgers.api.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.stellarburgers.api.AuthorizationData;
import ru.stellarburgers.api.Client;
import ru.stellarburgers.api.User;

import static io.restassured.RestAssured.given;
import static ru.stellarburgers.api.Constants.*;

public class UserClient extends Client {

    @Step("Create new user in system")
    public ValidatableResponse createUser(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post(CREATE_NEW_USER_URL)
                .then();
    }

    @Step("Login user in system with email and password")
    public ValidatableResponse loginUser(AuthorizationData authorizationData) {
        return given().log().all()
                .spec(getSpec())
                .body(authorizationData)
                .when()
                .post(LOGIN_USER_URL)
                .then();
    }

    @Step("Refresh user data by token")
    public ValidatableResponse refreshUserDataByToken(String accessToken, User user) {
        return given().log().all()
                .spec(getSpec())
                .auth().oauth2(accessToken)
                .body(user)
                .patch(USER_DATA_URL)
                .then();
    }

    @Step("Refresh user data Without token")
    public ValidatableResponse refreshUserDataWithoutToken(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .patch(USER_DATA_URL)
                .then();
    }

    @Step("Delete user by token")
    public ValidatableResponse deleteUser(String accessToken) {
        return given().log().all()
                .spec(getSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_DATA_URL)
                .then();
    }
}
