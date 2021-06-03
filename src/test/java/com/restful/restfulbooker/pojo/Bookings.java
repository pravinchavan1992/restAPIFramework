package com.restful.restfulbooker.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bookings {
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("totalprice")
    @Expose
    private Integer totalprice;
    @SerializedName("depositpaid")
    @Expose
    private Boolean depositpaid;
    @SerializedName("bookingdates")
    @Expose
    private BookingDates bookingdates;
    @SerializedName("additionalneeds")
    @Expose
    private String additionalneeds;

}

