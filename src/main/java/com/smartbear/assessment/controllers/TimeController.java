package com.smartbear.assessment.controllers;

import com.smartbear.assessment.services.BritishTimeConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

    private final BritishTimeConversionService britishTimeConversionService;
    public TimeController (BritishTimeConversionService britishTimeConversionService) {
        this.britishTimeConversionService = britishTimeConversionService;
    }

    @GetMapping("/convert")
    public ResponseEntity<?> convertTime(@RequestParam String rawTime) {
        return ResponseEntity.ok(britishTimeConversionService.getBritishTime(rawTime));
    }

}
