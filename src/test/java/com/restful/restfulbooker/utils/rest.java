package com.restful.restfulbooker.utils;

import com.restful.restfulbooker.bin.configurationManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class rest {

    public RequestSpecification getRequestSpecification() {
        return given().contentType(ContentType.JSON).baseUri(configurationManager.getSettings().getBaseUrl()).log().all();
    }

    public RequestSpecification getRequestSpecifications() {
        return given().contentType(ContentType.XML).baseUri(configurationManager.getSettings().getBaseUrl()).log().all();
    }

    public Response getResponse(RequestSpecification requestSpecification, String Endpoint, int statusCode) {
        Response response;
        response = requestSpecification.get(Endpoint);
        response.then().log().all();
        boolean success = checkStatusCode(response, statusCode);
        int count = 0;
        if (!success) {
            while (success) {
                response = requestSpecification.get(Endpoint);
                response.then().log().all();
                success = checkStatusCode(response, statusCode);
                if (count == 3 || statusCode == statusCode || success) {
                    break;
                }
                count++;
            }
            if (!success) {
                System.out.println("Expected Success Https Response not found: " + response.getStatusCode());
                return null;
            }
        }
        return response;
    }

    public Response postAndGetResponse(RequestSpecification requestSpecification, String Endpoint, int statusCode) {
        Response response;
        response = requestSpecification.post(Endpoint);
        response.then().log().all();
        boolean success = checkStatusCode(response, statusCode);
        int count = 0;
        if (!success) {
            while (success) {
                response = requestSpecification.post(Endpoint);
                response.then().log().all();
                success = checkStatusCode(response, statusCode);
                if (count == 3 || statusCode == statusCode || success) {
                    break;
                }
                count++;
            }
            if (!success) {
                System.out.println("Expected Success Https Response not found: " + response.getStatusCode());
                return null;
            }
        }
        return response;
    }

    public Response putAndGetResponse(RequestSpecification requestSpecification, String Endpoint, int statusCode) {
        Response response;
        response = requestSpecification.put(Endpoint);
        response.then().log().all();
        boolean success = checkStatusCode(response, statusCode);
        int count = 0;
        if (!success) {
            while (success) {
                response = requestSpecification.put(Endpoint);
                response.then().log().all();
                success = checkStatusCode(response, statusCode);
                if (count == 3 || statusCode == statusCode || success) {
                    break;
                }
                count++;
            }
            if (!success) {
                System.out.println("Expected Success Https Response not found: " + response.getStatusCode());
                return null;
            }
        }
        return response;
    }

    public Response deleteAndGetResponse(RequestSpecification requestSpecification, String Endpoint, int statusCode) {
        Response response;
        response = requestSpecification.delete(Endpoint);
        response.then().log().all();
        boolean success = checkStatusCode(response, statusCode);
        int count = 0;
        if (!success) {
            while (success) {
                response = requestSpecification.delete(Endpoint);
                response.then().log().all();
                success = checkStatusCode(response, statusCode);
                if (count == 3 || statusCode == statusCode || success) {
                    break;
                }
                count++;
            }
            if (!success) {
                System.out.println("Expected Success Https Response not found: " + response.getStatusCode());
                return null;
            }
        }
        return response;
    }

    public boolean checkStatusCode(Response response, int statusCode) {
        return response.getStatusCode() == statusCode ? true : false;
    }
}
