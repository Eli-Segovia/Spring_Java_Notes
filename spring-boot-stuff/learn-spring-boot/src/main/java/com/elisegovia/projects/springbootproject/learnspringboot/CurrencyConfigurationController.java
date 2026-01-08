package com.elisegovia.projects.springbootproject.learnspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/*
*
* This is a class just to quickly test the configurationComponent
*
*
*/
@RestController
public class CurrencyConfigurationController {

    // This just gets populated automatically with the values obtained...
    @Autowired
    private CurrencyServiceConfiguration configuration;


    // this just knows to return a POJO as a JSON object
    @RequestMapping("/currency")
    public CurrencyServiceConfiguration retrieveCurrency() {
        return configuration;
    }

}
