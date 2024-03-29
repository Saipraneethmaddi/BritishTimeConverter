package com.smartbear.assessment.services;

import com.smartbear.assessment.exceptions.CustomException;
import com.smartbear.assessment.exceptions.ErrorCode;
import org.springframework.stereotype.Service;

/**
 * Service that has all the business logic to convert 12-hour format time to verbal format
 */
@Service
public class BritishTimeConversionService {

    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"twenty", "thirty", "forty", "fifty"};

    public String getBritishTime(String timeString) throws CustomException {
        timeString = timeString.trim();


        //If length of the string is not between 3 and 5, we throw an exception.
        // Generally time formats can be 7:7, 7:07, 07:7, 07:07.
        if(timeString.length()<3 || timeString.length()>5) throw new CustomException(ErrorCode.INCORRECT_TIME_STRING_LENGTH);


        //If proper time format is not followed, we throw an exception. General time format is "hh:mm"
        String[] timeSplit = timeString.split(":");
        if(timeSplit.length!=2) throw new CustomException(ErrorCode.INCORRECT_TIME_FORMAT);


        int hours, minutes;
        try {
            hours = Integer.parseInt(timeSplit[0]);
            minutes = Integer.parseInt(timeSplit[1]);
        } catch (NumberFormatException e) {
            //If the values of hours or minutes are not whole numbers, we throw an exception.
            throw new CustomException(ErrorCode.INVALID_HOURS_MINUTES);
        }


        //If the values of hours or minutes are not in range, we throw and exception.
        //Valid range for hours is between 0 and 12. Valid range for minutes is between 0 and 59.
        if(hours>12 || hours<0 || minutes>59 || minutes<0) throw new CustomException(ErrorCode.HOURS_MINUTES_OUT_OF_RANGE);


        //Delineating the special cases of 00:00, 12:00 and xx:00
        if(minutes==0) {
            if(hours==0) return "midnight";
            else if(hours==12) return "noon";
            else return numberToStringConverter(hours) + " o'clock";
        }

        //If the minutes isn't divisible by 5, all the times follow same format.
        if(minutes%5!=0) return numberToStringConverter(hours) + " " + numberToStringConverter(minutes);
        else {

            //firstString will be the value of minutes. It can be quarter, half, exact number or (60-exact number).
            //middleString will be decided based on whether minutes are greater than 30 or not. If greater than 30 then the value will be "to", otherwise "past".
            //lastString will be the value of hours. Tt will be decided based on whether minutes are greater than 30 or not. If greater than 30 then the value will be next number, otherwise the same number.
            String firstString, middleString, lastString;

            if(minutes>30) {
                firstString = minutes==45 ? "quarter" : numberToStringConverter(60-minutes);
                middleString = "to";
                lastString = numberToStringConverter((hours+1)%12).replace(ONES[0], ONES[12]);
            } else {
                firstString = minutes==30 ? "half" : (minutes==15 ? "quarter" : numberToStringConverter(minutes));
                middleString = "past";
                lastString = numberToStringConverter(hours);
            }

            return firstString + " " + middleString + " " + lastString;
        }
    }

    private String numberToStringConverter(int number) {
        if(number<20) return ONES[number];
        else return number%10==0 ? TENS[(number/10)-2] : TENS[(number/10)-2]+" "+ONES[number%10];
    }

}
