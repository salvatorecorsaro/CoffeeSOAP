package com.scorsaro.thecoffesoap.service;

import com.scorsaro.coffee.Coffee;
import com.scorsaro.coffee.GetCoffeeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoffeeService {

    private final Faker faker;

    public Coffee create(GetCoffeeRequest request) throws DatatypeConfigurationException {
        var coffee = new Coffee();
        coffee.setId(request.getId());
        coffee.setName("Coffee " + faker.artist().name());
        coffee.setDescription(faker.lorem().sentence());
        var zdt = ZonedDateTime.now();
        var timestamp = zonedDateTimeToXMLGregorianCalendar(zdt);
        coffee.setTimestamp(timestamp);
        log.info("Coffee created: {}", coffee);
        return coffee;
    }

    protected XMLGregorianCalendar zonedDateTimeToXMLGregorianCalendar(ZonedDateTime zonedDateTime) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(zonedDateTime));
    }
}
