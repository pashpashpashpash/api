package org.example.testcase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

import static org.example.util.PropertiesUtil.getInstance;

public class TestBase {

    String token;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = getInstance().getProperty("baseUrl");
        token = getInstance().getProperty("token");
    }
}
