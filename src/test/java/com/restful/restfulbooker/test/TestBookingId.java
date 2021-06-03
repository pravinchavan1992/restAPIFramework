package com.restful.restfulbooker.test;

import com.restful.restfulbooker.common.Groups;
import com.restful.restfulbooker.service.Booking;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestBookingId {

    @Test(groups = {Groups.All, Groups.Regression, Groups.Sanity, Groups.Smoke})
    public void TestBookingIDSchema(){
        Booking booking = new Booking();
        booking.getBooking().then().assertThat().body(matchesJsonSchemaInClasspath("bookingIdSchema.json"));
    }

    @Test(groups = {Groups.All, Groups.Regression, Groups.Smoke})
    public void SearchBookingIDByName(){
        Booking booking = new Booking();
        SoftAssert softAssert = new SoftAssert();
        Response bookingDetailsByName = booking.getBookingIDByName();
        List<Integer> bookingForSally = bookingDetailsByName.jsonPath().getList("bookingid");
        List<Integer> allBooking = booking.getBooking().jsonPath().getList("bookingid");
        bookingForSally.parallelStream().forEach(sally -> softAssert.assertTrue(allBooking.contains(sally), sally+" not found in list."));
        softAssert.assertAll();
    }

    @Test(groups = {Groups.All, Groups.Sanity, Groups.Smoke, Groups.Regression})
    public void SearchBookingIDByDates(){
        Booking booking = new Booking();
        SoftAssert softAssert = new SoftAssert();
        Response bookingDetailsByName = booking.getBookingIDByCeckinDates();
        List<Integer> bookingids = bookingDetailsByName.jsonPath().getList("bookingid");
        bookingids.parallelStream().forEach(x -> softAssert.assertTrue(x%1==0, x+" is integer value"));
        softAssert.assertAll();
    }
}
