package com.smartbear.assessment.controllers;

import com.smartbear.assessment.exceptions.CustomException;
import com.smartbear.assessment.services.BritishTimeConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to receive API request with endpoint prefix of '/time' and provide appropriate responses
 */
@RestController
@RequestMapping("/time")
public class TimeController {

    private final BritishTimeConversionService britishTimeConversionService;
    public TimeController (BritishTimeConversionService britishTimeConversionService) {
        this.britishTimeConversionService = britishTimeConversionService;
    }

    @GetMapping("/convert")
    public ResponseEntity<?> convertTime(@RequestParam String rawTime) throws CustomException {
        return ResponseEntity.ok(britishTimeConversionService.getBritishTime(rawTime));
    }

}
