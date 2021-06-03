package com.restful.restfulbooker.test;

import com.restful.restfulbooker.common.Groups;
import com.restful.restfulbooker.pojo.Bookings;
import com.restful.restfulbooker.pojo.Example;
import com.restful.restfulbooker.service.Booking;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class TestBooking {

    @Test(groups = {Groups.All, Groups.Regression})
    public void TestBooking(){
        SoftAssert softAssert = new SoftAssert();
        Booking booking = new Booking();
        Map<String, Object> payload = booking.createPayLoad();
        Response bookingResponse = booking.bookRoomAndGetResponse(payload);
        String firstname = bookingResponse.jsonPath().get("booking.firstname");
        String lastname = bookingResponse.jsonPath().get("booking.lastname");
        int totalprice = bookingResponse.jsonPath().get("booking.totalprice");
        boolean depositpaid = bookingResponse.jsonPath().get("booking.depositpaid");
        String additionalneeds = bookingResponse.jsonPath().get("booking.additionalneeds");
        softAssert.assertEquals(firstname, payload.get("firstname"));
        softAssert.assertEquals(lastname, payload.get("lastname"));
        softAssert.assertEquals(totalprice, payload.get("totalprice"));
        softAssert.assertEquals(depositpaid, payload.get("depositpaid"));
        softAssert.assertEquals(additionalneeds, payload.get("additionalneeds"));
        softAssert.assertAll();
    }

    @Test(groups = {Groups.All, Groups.Regression, Groups.Sanity, Groups.Smoke})
    public void TestBookingPoJO(){
        SoftAssert softAssert = new SoftAssert();
        Booking booking = new Booking();
        Bookings payload = booking.bookingPayload();
        Response bookingResponse = booking.bookRoomAndGetResponse(payload);
        Example res = bookingResponse.getBody().as(Example.class, ObjectMapperType.GSON);
        softAssert.assertEquals(res.getBooking().getFirstname(), payload.getFirstname());
        softAssert.assertEquals(res.getBooking().getLastname(), payload.getLastname());
        softAssert.assertEquals(res.getBooking().getTotalprice(), payload.getTotalprice());
        softAssert.assertEquals(res.getBooking().getDepositpaid(), payload.getDepositpaid());
        softAssert.assertEquals(res.getBooking().getAdditionalneeds(), payload.getAdditionalneeds());
        softAssert.assertAll();
    }

    @Test(groups = {Groups.All, Groups.Smoke, Groups.Sanity})
    public void TestBookingPoJO1(){
        SoftAssert softAssert = new SoftAssert();
        Booking booking = new Booking();
        Bookings payload = booking.bookingPayload();
        Response bookingResponse = booking.bookRoomAndGetResponse(payload);
        Example res = bookingResponse.getBody().as(Example.class, ObjectMapperType.GSON);

        Bookings bks = booking.getBookingById(res.getBookingid(), HttpStatus.SC_OK).as(Bookings.class, ObjectMapperType.GSON);

        softAssert.assertEquals(res.getBooking().getFirstname(), bks.getFirstname());
        softAssert.assertEquals(res.getBooking().getLastname(), bks.getLastname());
        softAssert.assertEquals(res.getBooking().getTotalprice(), bks.getTotalprice());
        softAssert.assertEquals(res.getBooking().getDepositpaid(), bks.getDepositpaid());
        softAssert.assertEquals(res.getBooking().getAdditionalneeds(), bks.getAdditionalneeds());
        softAssert.assertAll();
    }
}
