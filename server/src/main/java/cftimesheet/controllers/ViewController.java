package cftimesheet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

    @RequestMapping({"", "/"})
    public String getCandyFactoryTimeSheetApplicationViews() {
        return "index";
    }

}
