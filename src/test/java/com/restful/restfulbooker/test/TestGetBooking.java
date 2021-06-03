package com.restful.restfulbooker.test;

import com.restful.restfulbooker.common.Groups;
import com.restful.restfulbooker.service.Booking;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestGetBooking {

    @Test(groups = {Groups.All, Groups.Regression, Groups.Sanity})
    public void TestBookingDetails(){
        Booking booking = new Booking();
        List<Integer> allBooking = booking.getBooking().jsonPath().getList("bookingid");
        allBooking.stream().forEach(id->{
            Response bookingByID = booking.getBookingById(id, HttpStatus.SC_OK);
            String fname = bookingByID.jsonPath().get("firstname");
            String lname = bookingByID.jsonPath().get("lastname");
            int totalprice = bookingByID.jsonPath().get("totalprice");
            boolean depositpaid = bookingByID.jsonPath().get("depositpaid");
            String additionalneeds = bookingByID.jsonPath().get("additionalneeds");
            Map<String, Date> bookingdates = bookingByID.jsonPath().getMap("bookingdates");
        });
    }
}
