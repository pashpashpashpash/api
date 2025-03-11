package org.example.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.example.model.BookingData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestHelper {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    public static final String CREATE_BOOKING_JSON = "createBooking.json";
    public static final String UPDATE_BOOKING_JSON = "updateBooking.json";


    public static String getJson(String jsonName) {
        String json = "";
        try {
        json = FileUtils.readFileToString(new File(RESOURCES_PATH + jsonName), StandardCharsets.UTF_8);
        } catch (IOException ex) {

        }
        return json;
    }

    public static BookingData getTestBookingData(String jsonName) {
        BookingData testBookingData = new BookingData();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(RESOURCES_PATH + jsonName), BookingData.class);
        } catch (IOException ex) {

        }
        return testBookingData;
    }

}
