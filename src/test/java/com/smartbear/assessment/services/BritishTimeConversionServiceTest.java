package com.smartbear.assessment.services;

import com.smartbear.assessment.exceptions.CustomException;
import com.smartbear.assessment.exceptions.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BritishTimeConversionServiceTest {

    @InjectMocks
    private BritishTimeConversionService britishTimeConversionService;

    @Test
    public void incorrectTimeStringLengthTest() {
        final String input1 = "abc:def";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input1));
        try {
            britishTimeConversionService.getBritishTime(input1);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.INCORRECT_TIME_STRING_LENGTH.getMessage(), e.getErrorCode().getMessage());
        }


        final String input2 = "12";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input2));
        try {
            britishTimeConversionService.getBritishTime(input2);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.INCORRECT_TIME_STRING_LENGTH.getMessage(), e.getErrorCode().getMessage());
        }
    }

    @Test
    public void incorrectTimeFormatTest() {
        final String input1 = "1:1:0";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input1));
        try {
            britishTimeConversionService.getBritishTime(input1);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.INCORRECT_TIME_FORMAT.getMessage(), e.getErrorCode().getMessage());
        }


        final String input2 = "1101";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input2));
        try {
            britishTimeConversionService.getBritishTime(input2);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.INCORRECT_TIME_FORMAT.getMessage(), e.getErrorCode().getMessage());
        }
    }

    @Test
    public void invalidHoursMinutesTest() {
        final String input1 = "1b:23";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input1));
        try {
            britishTimeConversionService.getBritishTime(input1);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.INVALID_HOURS_MINUTES.getMessage(), e.getErrorCode().getMessage());
        }

        final String input2 = "11:a3";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input2));
        try {
            britishTimeConversionService.getBritishTime(input2);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.INVALID_HOURS_MINUTES.getMessage(), e.getErrorCode().getMessage());
        }
    }

    @Test
    public void hoursMinutesOutOfRangeTest() {
        final String input1 = "-1:35";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input1));
        try {
            britishTimeConversionService.getBritishTime(input1);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.HOURS_MINUTES_OUT_OF_RANGE.getMessage(), e.getErrorCode().getMessage());
        }

        final String input2 = "1:-35";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input2));
        try {
            britishTimeConversionService.getBritishTime(input2);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.HOURS_MINUTES_OUT_OF_RANGE.getMessage(), e.getErrorCode().getMessage());
        }

        final String input3 = "13:35";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input3));
        try {
            britishTimeConversionService.getBritishTime(input3);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.HOURS_MINUTES_OUT_OF_RANGE.getMessage(), e.getErrorCode().getMessage());
        }

        final String input4 = "8:60";
        Assertions.assertThrows(CustomException.class, () -> britishTimeConversionService.getBritishTime(input4));
        try {
            britishTimeConversionService.getBritishTime(input4);
        } catch (CustomException e) {
            Assertions.assertEquals(ErrorCode.HOURS_MINUTES_OUT_OF_RANGE.getMessage(), e.getErrorCode().getMessage());
        }
    }

    @Test
    public void timeConverterTest() throws CustomException {
        String input = "1:00";
        String expectedOutput = "one o'clock";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "2:05";
        expectedOutput = "five past two";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "3:10";
        expectedOutput = "ten past three";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "4:15";
        expectedOutput = "quarter past four";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "5:20";
        expectedOutput = "twenty past five";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "6:25";
        expectedOutput = "twenty five past six";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "6:32";
        expectedOutput = "six thirty two";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "7:30";
        expectedOutput = "half past seven";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "7:35";
        expectedOutput = "twenty five to eight";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "8:40";
        expectedOutput = "twenty to nine";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "9:45";
        expectedOutput = "quarter to ten";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "10:50";
        expectedOutput = "ten to eleven";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "11:55";
        expectedOutput = "five to twelve";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "00:00";
        expectedOutput = "midnight";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "12:00";
        expectedOutput = "noon";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));

        input = "04:18";
        expectedOutput = "four eighteen";
        Assertions.assertEquals(expectedOutput, britishTimeConversionService.getBritishTime(input));
    }
}
