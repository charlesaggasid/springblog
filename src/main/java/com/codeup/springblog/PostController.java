package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String viewIndexPage(){
        return "Here are all the posts.";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewIndividualPost(@PathVariable long id){
        return "This is an individual blog post.";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "Get the form for creating a post.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String insert(){
        return "Post the created blog post.";
    }

}
