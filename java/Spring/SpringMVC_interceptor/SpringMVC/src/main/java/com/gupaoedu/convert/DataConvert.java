package com.gupaoedu.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConvert implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        finally {
        }
    }
}
