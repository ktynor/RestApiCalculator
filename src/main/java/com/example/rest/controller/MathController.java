package com.example.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (isNumeric(numberOne) || isNumeric(numberTwo)) {
            throw new Exception();
        }
        Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
        return sum;
    }

    private Double convertToDouble(String number) {
        // TODO implement method

        return 1D;
    }

    private boolean isNumeric(String numberOne) {
        // TODO implement method
        return false;
    }
}
