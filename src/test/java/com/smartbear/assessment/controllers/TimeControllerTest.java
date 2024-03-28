package com.smartbear.assessment.controllers;

import com.smartbear.assessment.exceptions.CustomException;
import com.smartbear.assessment.exceptions.ErrorCode;
import com.smartbear.assessment.services.BritishTimeConversionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class TimeControllerTest {

    @Mock
    private BritishTimeConversionService britishTimeConversionService;

    @InjectMocks
    private TimeController timeController;

    @Test
    public void getTimeTest() throws CustomException {
        String input = "10:05";
        String output = "five past ten";
        HttpStatus outputHttpStatus = HttpStatus.OK;

        Mockito.doReturn(output).when(britishTimeConversionService).getBritishTime(input);
        ResponseEntity<?> responseEntity = timeController.convertTime(input);

        Assertions.assertEquals(output, responseEntity.getBody());
        Assertions.assertEquals(outputHttpStatus, responseEntity.getStatusCode());
    }

    @Test
    public void exceptionTest() throws CustomException {
        String input = "7:61";
        CustomException exceptionToBeThrown = new CustomException(ErrorCode.HOURS_MINUTES_OUT_OF_RANGE);
        String output = exceptionToBeThrown.getErrorCode().getMessage();
        HttpStatus outputHttpStatus = exceptionToBeThrown.getErrorCode().getHttpStatus();

        Mockito.doThrow(exceptionToBeThrown).when(britishTimeConversionService).getBritishTime(input);
        ResponseEntity<?> responseEntity = timeController.convertTime(input);

        Assertions.assertEquals(output, responseEntity.getBody());
        Assertions.assertEquals(outputHttpStatus, responseEntity.getStatusCode());
    }

}
