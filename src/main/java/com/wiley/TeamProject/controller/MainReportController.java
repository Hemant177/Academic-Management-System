package com.wiley.TeamProject.controller;//package com.wiley.TeamProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainReportController {

    @GetMapping("/reports")
    public String home()
    {
        return "reportsIndex";
    }
}
