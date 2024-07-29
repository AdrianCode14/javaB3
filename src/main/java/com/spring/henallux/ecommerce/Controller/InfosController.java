package com.spring.henallux.ecommerce.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/society")
public class InfosController {
    @RequestMapping(method = RequestMethod.GET)
    public String terms() {
        return "integrated:societyDescription";
    }

}
