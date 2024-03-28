package com.smartbear.assessment.services;

import com.smartbear.assessment.exceptions.CustomException;
import com.smartbear.assessment.exceptions.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class BritishTimeConversionService {

    private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] TENS = {"twenty", "thirty", "forty", "fifty"};

    public String getBritishTime(String timeString) throws CustomException {
        timeString = timeString.trim();
        if(timeString.length()>5) throw new CustomException(ErrorCode.INCORRECT_TIME_STRING_LENGTH);

        String[] timeSplit = timeString.split(":");
        if(timeSplit.length!=2) throw new CustomException(ErrorCode.INCORRECT_TIME_FORMAT);

        int hours, minutes;
        try {
            hours = Integer.parseInt(timeSplit[0]);
            minutes = Integer.parseInt(timeSplit[1]);
        } catch (NumberFormatException e) {
            throw new CustomException(ErrorCode.INVALID_HOURS_MINUTES);
        }

        if(hours>12 || hours<0 || minutes>59 || minutes<0) throw new CustomException(ErrorCode.HOURS_MINUTES_OUT_OF_RANGE);

        if(minutes==0) {
            if(hours==0) return "midnight";
            else if(hours==12) return "noon";
            else return numberToStringConverter(hours) + " o'clock";
        }

        if(minutes%5!=0) return numberToStringConverter(hours) + " " + numberToStringConverter(minutes);
        else {
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
