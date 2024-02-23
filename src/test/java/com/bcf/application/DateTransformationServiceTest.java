package com.bcf.application;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTransformationServiceTest {

    private static DateTransformationService service = new DateTransformationService("MM/dd/yy", "MMMM dd, yyyy");

    @ParameterizedTest
    @MethodSource("provideDatesForTransformation")
    void testTransformContent(String input, String expected) {
        assertEquals(expected, service.transformContent(input));
    }

    private static Stream<Arguments> provideDatesForTransformation() {
        return Stream.of(
                Arguments.of(
                        "Today is 01/01/00.",
                        "Today is January 01, 2000."
                ),
                Arguments.of(
                        "Event date: 12/31/99.",
                        "Event date: December 31, 1999."
                ),
                Arguments.of(
                        "Meeting on 2/8/2024.",
                        "Meeting on February 08, 2024."
                ), // Testing single-digit month and day
                Arguments.of(
                        "Birthday: 7/4/76.",
                        "Birthday: July 04, 1976."
                ), // Testing two-digit year
                Arguments.of(
                        "Anniversary: 09/09/2022.",
                        "Anniversary: September 09, 2022."
                ), // Testing leading zeros
                Arguments.of(
                        "New Year: 1/1/2023.",
                        "New Year: January 01, 2023."
                ), // Testing mixed single digit and four-digit year
                Arguments.of(
                        "End of the year: 12/31/00.",
                        "End of the year: December 31, 2000."
                ), // Edge case of year '00
                Arguments.of(
                        "Leap year day: 2/29/00.",
                        "Leap year day: February 29, 2000."
                ), // Testing leap year
                Arguments.of(
                        "First day of Y2K: 01/01/2000.",
                        "First day of Y2K: January 01, 2000."
                ) // Y2K date
        );
    }
}
