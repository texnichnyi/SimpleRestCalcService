package com.epam.calc;


import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CalcRestTestPositive extends CalcRestConstants {


    private static String operationName;
    @Test(dataProvider = "values")
    public void additionTest(double firstValue, double secondValue){
        operationName = "addition";
        assertEquals(given().
                when().
                    get(requestFirstValue+firstValue+requestSecondValue+secondValue+requestOperationName+operationName).
                then().
                contentType(ContentType.TEXT).
                extract().response().asString(),new DecimalFormat("#0.0000").format(firstValue + secondValue));
    }

    @Test(dataProvider = "values")
    public void subtractionTest(double firstValue, double secondValue){
        operationName = "subtraction";
        assertEquals(given().
                when().
                get(requestFirstValue+firstValue+requestSecondValue+secondValue+requestOperationName+operationName).
                then().
                contentType(ContentType.TEXT).
                extract().response().asString(),new DecimalFormat("#0.0000").format(firstValue - secondValue));
    }

    @Test(dataProvider = "values")
    public void divisionTest(double firstValue, double secondValue){
        operationName = "division";
        assertEquals(given().
                when().
                get(requestFirstValue+firstValue+requestSecondValue+secondValue+requestOperationName+operationName).
                then().
                contentType(ContentType.TEXT).
                extract().response().asString(),new DecimalFormat("#0.0000").format(firstValue / secondValue));
    }

    @Test(dataProvider = "values")
    public void multiplicationTest(double firstValue, double secondValue){
        operationName = "multiplication";
        assertEquals(given().
                when().
                get(requestFirstValue+firstValue+requestSecondValue+secondValue+requestOperationName+operationName).
                then().
                contentType(ContentType.TEXT).
                extract().response().asString(),new DecimalFormat("#0.0000").format(firstValue * secondValue));
    }

    @Test(dataProvider = "values")
    public void percentTest(double firstValue, double secondValue){
        operationName = "percent";
        assertEquals(given().
                when().
                get(requestFirstValue+firstValue+requestSecondValue+secondValue+requestOperationName+operationName).
                then().
                contentType(ContentType.TEXT).
                extract().response().asString(),new DecimalFormat("#0.0000").format((firstValue * secondValue)/100));
    }

    @DataProvider(name = "values")

    public static Object[][] values() {

        return new Object[][] { { 4.231, 5.12 }, { 0.32, 3 }, {4,2}, {5,24}};

    }

}
