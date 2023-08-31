package com.scorsaro.thecoffesoap.controller;

import com.scorsaro.coffee.GetCoffeeRequest;
import com.scorsaro.coffee.GetCoffeeResponse;
import com.scorsaro.thecoffesoap.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;


@Endpoint
@RequiredArgsConstructor
public class CoffeeEndpoint {

    private final CoffeeService coffeeService;

    @PayloadRoot(namespace = "http://scorsaro.com/coffee", localPart = "GetCoffeeRequest")
    @ResponsePayload
    public GetCoffeeResponse processCoffeeRequest(@RequestPayload GetCoffeeRequest request) throws DatatypeConfigurationException {
        var response = new GetCoffeeResponse();
        var coffee = coffeeService.create(request);
        response.setCoffee(coffee);
        return response;
    }
}
