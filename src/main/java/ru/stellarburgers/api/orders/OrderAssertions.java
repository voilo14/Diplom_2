package ru.stellarburgers.api.orders;

import io.restassured.response.ValidatableResponse;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.stellarburgers.api.Constants.ERROR_MESSAGE_OF_AUTHORISATION;
import static ru.stellarburgers.api.Constants.ERROR_MESSAGE_OF_MISSING_INGREDIENTS;

public class OrderAssertions {

    public void creatingOrderSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("order.number", notNullValue());
    }

    public void creatingOrderUnsuccessfullyWithoutIngredients(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo(ERROR_MESSAGE_OF_MISSING_INGREDIENTS));
    }

    public void creatingOrderUnsuccessfullyWithWrongIngredients(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    public void getUserOrdersSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

    public void getUserOrdersSuccessfullyWithOrder(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("orders", notNullValue());
    }

    public void getUserOrdersUnsuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo(ERROR_MESSAGE_OF_AUTHORISATION));
    }
}
