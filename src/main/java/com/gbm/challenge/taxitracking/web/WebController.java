package com.gbm.challenge.taxitracking.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring web controller.
 *
 * @author danizz
 */
@Controller
public class WebController {

    @Value("${webhook.url}")
    private String webhookUrl;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("webhookUrl", webhookUrl);
        return "index";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

}
