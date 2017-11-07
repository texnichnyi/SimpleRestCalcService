package com.epam.calc;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CalcRestTestNegative extends CalcRestConstants {

    @Test(dataProvider = "incorrectValues")
    public void incorrectFirstAndSecondValues(Object firstValue, Object secondValue){
        given().
                when().
                get(requestFirstValue+firstValue+requestSecondValue+secondValue+requestOperationName+"addition").
                then().
                statusCode(400);
    }

    @Test
    public void divisionOnZero(){
        assertEquals(given().
                when().
                get(requestFirstValue+5+requestSecondValue+0+requestOperationName+"division").
                then().
                contentType(ContentType.TEXT).
                extract().response().asString(),"Cannot / 0, please enter different value");
    }

    @Test (dataProvider = "operationValues")
    public void incorrectOperationName(Object operationName){
        assertEquals(given().
                when().
                get(requestFirstValue+5+requestSecondValue+0+requestOperationName+operationName).
                then().
                contentType(ContentType.TEXT).
                extract().response().asString(),"Please enter a valid operation: addition, subtraction, division, multiplication or percent");
    }



    @DataProvider(name = "operationValues")

    public static Object[][] operationValues() {
        return new Object[][] { { "^&#!%^ffa"}, { "adfdfadfa" }, {"17341"}};

    }

    @DataProvider(name = "incorrectValues")

    public static Object[][] values() {
        int[] a = new int[]{3,4,5,6};
        return new Object[][] { { "^&#!%^ffa", a }, { a, "adfdfadfa" }, {"dfadfa","adffd"}, {"QEQE","3#!R#1"}};

    }
}
