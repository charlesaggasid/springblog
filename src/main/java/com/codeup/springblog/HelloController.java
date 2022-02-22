package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // Defines that our class is a controller.
            // is like extend HTTPServlet.
public class HelloController {

//GetMapping no need semicolon


    // 1
    @GetMapping("/hello/{name}") //name is path variable
    @ResponseBody
    public String hello(@PathVariable String name) { //need @Path to use path var
        return "Hello, " + name + " from Spring";
    }

    // 2
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    //Request Mapping
    @RequestMapping( path = "/increment/{number}", method = RequestMethod.GET ) //need to define methods
    @ResponseBody
    public String increment(@PathVariable int number) { //establish method
        return number + " plus one is " + (number + 1) + "!";
    }
}
