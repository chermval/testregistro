package com.example.test.testregistro.api.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class TestResource {

	@RequestMapping(value="/test", method=RequestMethod.GET)
    String test() {
        return "Hello World!";
    }

}
