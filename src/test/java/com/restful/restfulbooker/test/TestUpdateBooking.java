package com.restful.restfulbooker.test;

import com.restful.restfulbooker.bin.TokenGeneration;
import com.restful.restfulbooker.bin.configurationManager;
import com.restful.restfulbooker.common.Groups;
import com.restful.restfulbooker.pojo.Bookings;
import com.restful.restfulbooker.pojo.Example;
import com.restful.restfulbooker.service.Booking;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class TestUpdateBooking {

    @Test(groups = {Groups.All, Groups.Regression, Groups.Sanity})
    public void TestUpdateBookingUsingToken(){
        SoftAssert softAssert = new SoftAssert();
        Booking booking = new Booking();
        Bookings payload = booking.bookingPayload();
        Response bookingResponse = booking.bookRoomAndGetResponse(payload);
        Example res = bookingResponse.getBody().as(Example.class, ObjectMapperType.GSON);

        String token  = TokenGeneration.getToken();
        Map<String, Object> header = new HashMap<>();
        header.put("Accept", ContentType.JSON);
        header.put("Cookie", "token="+token);

        payload.setFirstname("Testing Update Booking");
        Bookings updatedDetails = booking.updateBookingAndGetResponse(payload, header, res.getBookingid()).as(Bookings.class, ObjectMapperType.GSON);

        softAssert.assertEquals(updatedDetails.getFirstname(), payload.getFirstname());

        softAssert.assertAll();

    }

    @Test(groups = {Groups.All, Groups.Regression, Groups.Sanity})
    public void TestUpdateBookingUsingBasicAuth(){
        SoftAssert softAssert = new SoftAssert();
        String cred = "Basic " + Base64.encodeBase64String((configurationManager.getSettings().getUserName() + ":" + configurationManager.getSettings().getPassword()).getBytes());
        Booking booking = new Booking();
        Bookings payload = booking.bookingPayload();
        Response bookingResponse = booking.bookRoomAndGetResponse(payload);
        Example res = bookingResponse.getBody().as(Example.class, ObjectMapperType.GSON);

        Map<String, Object> header = new HashMap<>();
        header.put("Accept", ContentType.JSON);
        header.put("Authorization", cred);

        payload.setFirstname("Testing Update Booking");
        Bookings updatedDetails = booking.updateBookingAndGetResponse(payload, header, res.getBookingid()).as(Bookings.class, ObjectMapperType.GSON);

        softAssert.assertEquals(updatedDetails.getFirstname(), payload.getFirstname());

        softAssert.assertAll();

    }
}
