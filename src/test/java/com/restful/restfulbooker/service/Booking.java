package com.restful.restfulbooker.service;

import com.github.javafaker.Faker;
import com.restful.restfulbooker.common.endPoints;
import com.restful.restfulbooker.pojo.BookingDates;
import com.restful.restfulbooker.pojo.Bookings;
import com.restful.restfulbooker.utils.rest;
import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import javax.swing.text.AbstractDocument;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Booking {

    RequestSpecification requestSpecification;

    public Booking(){
        requestSpecification = new rest().getRequestSpecification();
    }

    public Response getBooking(){
        return new rest().getResponse(requestSpecification, endPoints.booking, HttpStatus.SC_OK);
    }

    public Response getBookingIDByName(){
        requestSpecification = requestSpecification.queryParam("firstname","Sally").queryParam("lastname","Brown");
        return new rest().getResponse(requestSpecification, endPoints.booking, HttpStatus.SC_OK);
    }

    public Response getBookingIDByCeckinDates(){
        requestSpecification = requestSpecification.queryParam("checkin","2013-02-23").queryParam("checkout","2019-10-23");
        return new rest().getResponse(requestSpecification, endPoints.booking, HttpStatus.SC_OK);
    }

    public Response getBookingById(int id, int statuscode){
        requestSpecification.pathParam("id", id);
        return new rest().getResponse(requestSpecification, endPoints.bookingById, statuscode);
    }
    public Response bookRoomAndGetResponse(Object payload){
        requestSpecification =  requestSpecification.header("Accept", "application/json").body(payload);
        return new rest().postAndGetResponse(requestSpecification, endPoints.booking, HttpStatus.SC_OK);
    }

    public Response updateBookingAndGetResponse(Object payload, Map<String, Object> header, int id){
        requestSpecification =  requestSpecification.config(RestAssuredConfig.config()
                .headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("Accept", "Content-Type")))
                .headers(header).pathParam("id", id).body(payload);
        return new rest().putAndGetResponse(requestSpecification, endPoints.bookingById, HttpStatus.SC_OK);
    }
    public Response deleteBookingAndGetResponse(Object payload, Map<String, Object> header, int id){
        requestSpecification =  requestSpecification.headers(header).pathParam("id", id).body(payload);
        return new rest().deleteAndGetResponse(requestSpecification, endPoints.bookingById, HttpStatus.SC_CREATED);
    }

    public Response getBookingxml(int id){
        RequestSpecification reqSpecification = new rest().getRequestSpecifications().pathParam("id", id);
        return new rest().getResponse(reqSpecification, endPoints.bookingById, HttpStatus.SC_OK);
    }
    public Map<String, Object> createPayLoad(){
        Faker faker = new Faker();
        Map<String, Object> dates = new LinkedHashMap<>();
        dates.put("checkin", "2018-01-01");
        dates.put("checkout", "2019-01-01");

        Map<String, Object> jsonObject = new LinkedHashMap<>();
        jsonObject.put("firstname", faker.name().firstName());
        jsonObject.put("lastname", faker.name().lastName());
        jsonObject.put("totalprice", Math.abs((100) + new Random().nextInt(100)));
        jsonObject.put("depositpaid", new Random().nextBoolean());
        jsonObject.put("bookingdates", dates);
        jsonObject.put("additionalneeds",faker.harryPotter().quote());

        return jsonObject;
    }

    public Bookings bookingPayload(){
        Faker faker = new Faker();
        BookingDates dates = new BookingDates();
        dates.setCheckin("2018-01-01");
        dates.setCheckout("2019-01-01");

        Bookings booking = new Bookings();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(Math.abs((100) + new Random().nextInt(100)));
        booking.setDepositpaid(new Random().nextBoolean());
        booking.setBookingdates(dates);
        booking.setAdditionalneeds(faker.harryPotter().quote());

        return booking;
    }
}
