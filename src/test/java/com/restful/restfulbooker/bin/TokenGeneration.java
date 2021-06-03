package com.restful.restfulbooker.bin;

import com.restful.restfulbooker.common.endPoints;
import com.restful.restfulbooker.utils.rest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class TokenGeneration {

    private static TokenGeneration tokenGeneration;

    private static Response response;

    private TokenGeneration(){
        RequestSpecification requestSpecification = new rest().getRequestSpecification();
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("username", configurationManager.getSettings().getUserName());
        payload.put("password", configurationManager.getSettings().getPassword());
        requestSpecification.body(payload);
        response = new rest().postAndGetResponse(requestSpecification, endPoints.auth, HttpStatus.SC_OK);
    }

    public static String getToken(){
        if(response == null){ tokenGeneration = new TokenGeneration(); }
        return response.jsonPath().get("token");
    }
}
