package com.dbms.spark.util;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
@ConfigurationPropertiesBinding
public final class TimeConverter implements Converter<String, Time> {

    @Override
    public Time convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        if (source.length() == 5) {
            source += ":00";
        }
        return Time.valueOf(source);
    }

}