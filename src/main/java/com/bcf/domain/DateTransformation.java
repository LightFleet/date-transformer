package com.bcf.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTransformation {

    private SimpleDateFormat sourceFormat;
    private SimpleDateFormat destinationFormat;

    public DateTransformation(String sourcePattern, String destinationPatter, Locale locale) {
        this.sourceFormat = new SimpleDateFormat(sourcePattern, locale);
        this.destinationFormat = new SimpleDateFormat(destinationPatter, locale);
    }

    public String transform(String sourceDate) throws ParseException {
        Date date = sourceFormat.parse(sourceDate);
        return destinationFormat.format(date);
    }
}