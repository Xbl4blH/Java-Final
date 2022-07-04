package com.example.finaljava.CFV;

import com.example.finaljava.models.Firm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FirmConverter implements Converter<String, Firm> {

    @Override
    public Firm convert(String source) {
        String[] data = source.split(",");
        return new Firm(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]));
    }
}
