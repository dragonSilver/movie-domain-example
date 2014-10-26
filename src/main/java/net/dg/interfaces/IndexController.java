package net.dg.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "Welcome to moive-domain-example";
    }
}
