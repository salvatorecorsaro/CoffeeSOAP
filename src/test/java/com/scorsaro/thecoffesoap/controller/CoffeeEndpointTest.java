package com.scorsaro.thecoffesoap.controller;

import com.scorsaro.coffee.Coffee;
import com.scorsaro.coffee.GetCoffeeRequest;
import com.scorsaro.thecoffesoap.service.CoffeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;


@WebServiceServerTest(CoffeeEndpoint.class)
public class CoffeeEndpointTest {

    @Autowired
    private MockWebServiceClient mockClient;

    @MockBean
    private CoffeeService coffeeService;

    @InjectMocks
    private CoffeeEndpoint coffeeEndpoint;

    @Test
    public void givenValidRequest_whenProcessCoffeeRequest_thenReturnsValidResponse() throws DatatypeConfigurationException {
        GetCoffeeRequest request = new GetCoffeeRequest();
        request.setId(1);

        Coffee coffee = new Coffee();
        coffee.setId(1);
        coffee.setName("Espresso");
        coffee.setDescription("Strong coffee");

        XMLGregorianCalendar timestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar("2023-08-31T10:15:30+01:00");
        coffee.setTimestamp(timestamp);

        when(coffeeService.create(any())).thenReturn(coffee);

        var requestPayload = new StringSource("<GetCoffeeRequest xmlns=\"http://scorsaro.com/coffee\"><id>1</id></GetCoffeeRequest>");
        var expectedResponsePayload = new StringSource("<GetCoffeeResponse xmlns=\"http://scorsaro.com/coffee\"><Coffee><id>1</id><name>Espresso</name><description>Strong coffee</description><timestamp>2023-08-31T10:15:30+01:00</timestamp></Coffee></GetCoffeeResponse>");

        mockClient
            .sendRequest(withPayload(requestPayload))
            .andExpect(payload(expectedResponsePayload));
    }
}
