package org.example.testcase;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.example.model.Booking;
import org.example.model.BookingData;
import org.example.model.BookingPartial;
import org.testng.Assert;
import org.testng.annotations.Test;


import static org.example.testcase.steps.api.ApiSteps.*;

public class BookingTest extends TestBase {

    private Integer bookingId;

    @Test(priority = 1)
    public void whenCreateBooking_thenBookingCreated() {
        BookingData bookingToCreate = TestHelper.getTestBookingData(TestHelper.CREATE_BOOKING_JSON);

        Response response = createBooking(bookingToCreate);

        Booking createdBookingResponse = response.getBody().as(Booking.class);
        bookingId = createdBookingResponse.getBookingid();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(bookingToCreate, createdBookingResponse.getBooking(), "Bookings should be equal");

        BookingData createdBooking = getBookingById(bookingId).getBody().as(BookingData.class);

        Assert.assertEquals(bookingToCreate, createdBooking, "Bookings should be equal");

    }

    @Test(priority = 2)
    public void whenUpdateBooking_thenBookingUpdated() {
        BookingData bookingDataToUpdate = TestHelper.getTestBookingData(TestHelper.UPDATE_BOOKING_JSON);

        Response response = updateBooking(bookingDataToUpdate, bookingId, token);

        BookingData updatedBooking = response.getBody().as(BookingData.class);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(bookingDataToUpdate, updatedBooking, "Bookings should be equal");

        updatedBooking = getBookingById(bookingId).getBody().as(BookingData.class);

        Assert.assertEquals(bookingDataToUpdate, updatedBooking, "Bookings should be equal");

    }

    @Test(priority = 3)
    public void whenPartialUpdateBooking_thenBookingUpdated() {
        String newFirstName = RandomStringUtils.random(10, true, true);
        BookingPartial bookingPartialData = new BookingPartial(newFirstName);

        Response response = partialUpdateBooking(bookingPartialData, bookingId, token);

        BookingData updatedBooking = response.getBody().as(BookingData.class);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(newFirstName, updatedBooking.getFirstname(), "Updated fields should be equal");

        updatedBooking = getBookingById(bookingId).getBody().as(BookingData.class);

        Assert.assertEquals(newFirstName, updatedBooking.getFirstname(), "Updated fields should be equal");

    }

    @Test(priority = 4)
    public void whenDeleteBooking_thenBookingDeleted() {
        Response response = deleteBooking(bookingId, token);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);

        response = getBookingById(bookingId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

}
