package com.epam.calc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

@RestController
public class CalcRestController {

    private Object result;
    @RequestMapping("/calcRest")
    public Object app(@RequestParam(value="firstValue") double firstValue,
                   @RequestParam(value="secondValue") double secondValue,
                   @RequestParam(value="operationName") String operationName) {
        return getResult(firstValue,secondValue,operationName);
    }


    private Object getResult(double firsValue, double secondValue, String operationName){
       switch (operationName){
           case "addition": result = new DecimalFormat("#0.0000").format(firsValue + secondValue);
               break;
           case "subtraction": result = new DecimalFormat("#0.0000").format(firsValue - secondValue);
               break;
           case "division": result = new DecimalFormat("#0.0000").format(firsValue / secondValue);
               break;
           case "multiplication": result = new DecimalFormat("#0.0000").format(firsValue * secondValue);
               break;
           case "percent": result = new DecimalFormat("#0.0000").format((firsValue * secondValue)/100);
               break;
           default: result = "Please enter a valid operation: addition, subtraction, division, multiplication or percent";
       }
       if(operationName.equals("division")&&secondValue==0){
           result = "Cannot / 0, please enter different value";
       }
            return result;
    }
}
