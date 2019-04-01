package com.gbm.challenge.taxitracking.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring web controller.
 *
 * @author danizz
 */
@Controller
public class WebController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

}
