package org.example.testcase.steps.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.model.BookingData;
import org.example.model.BookingPartial;

public class ApiSteps {

    public static Response getAllBookings() {
        return RestAssured.given().filter(new AllureRestAssured()).get("/booking");
    }

    public static Response getBookingById(int id) {
        return RestAssured.given().filter(new AllureRestAssured()).get("/booking/" + id);
    }

    public static Response createBooking(BookingData booking) {
        return RestAssured.given().filter(new AllureRestAssured())
                .contentType(ContentType.JSON).body(booking).post("/booking");
    }

    public static Response updateBooking(BookingData booking, int id, String token) {
        return RestAssured.given().filter(new AllureRestAssured()).header("Cookie", "token=" + token)
                .contentType(ContentType.JSON).body(booking).put("/booking/" + id);
    }

    public static Response partialUpdateBooking(BookingPartial booking, int id, String token) {
        return RestAssured.given().filter(new AllureRestAssured()).header("Cookie", "token=" + token)
                .contentType(ContentType.JSON).body(booking).patch("/booking/" + id);
    }

    public static Response deleteBooking(int id, String token) {
        return RestAssured.given().filter(new AllureRestAssured()).header("Cookie", "token=" + token)
                .contentType(ContentType.JSON).delete("/booking/" + id);
    }

}
