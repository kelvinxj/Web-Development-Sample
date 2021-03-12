package com.kelvin.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
            dt = fmt.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }
}
