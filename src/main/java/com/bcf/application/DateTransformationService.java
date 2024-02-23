package com.bcf.application;

import com.bcf.domain.DateTransformation;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTransformationService {

    private DateTransformation dateTransformation;

    public DateTransformationService(String sourcePattern, String destinationPattern) {
        this.dateTransformation = new DateTransformation(sourcePattern, destinationPattern, Locale.US);
    }

    public String transformContent(String content) {
        Pattern pattern = Pattern.compile("(\\d{1,2}/\\d{1,2}/\\d{2,4})");
        Matcher matcher = pattern.matcher(content);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String originalDate = matcher.group(1);
            String formattedDate = transformDate(originalDate);
            matcher.appendReplacement(result, formattedDate);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    private String transformDate(String originalDate) {
        try {
            return dateTransformation.transform(originalDate);
        } catch (ParseException e) {
            System.err.println("Failed to parse a date: " + originalDate);
            return "UNKNOWN_DATE";
        }
    }
}
