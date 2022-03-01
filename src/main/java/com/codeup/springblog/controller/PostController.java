package com.codeup.springblog.controller;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    public PostController(PostRepository postsDao){
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String viewPostIndexPage(Model model){
//Inside the method that shows all the posts, create a new array list and add two post objects to it, then pass that list to the view.
//        List<Post> posts = new ArrayList<>();
//        Post post1 = new Post(1,"First post in my blog.", "Learning Spring Boot Java Framework is amazing!");
//        Post post2 = new Post(2,"Backache", "Back starts to hurt now. Need to stretch more.");
//        posts.add(post1);
//        posts.add(post2);
//        model.addAttribute("indexPosts", posts);
          List<Post> allPosts = postsDao.findAll();
          model.addAttribute("allPosts", allPosts);
        return "/posts/index";
    }

    @GetMapping ("/posts/{id}")
    public String viewIndividualPost(@PathVariable int id, Model model){
        Post indivPost = new Post(id, "First Individual Post", "Hope this shows up. My IntelliJ is running so slow.");
        model.addAttribute("individualPost",indivPost);
        return "posts/show";
    }

    @GetMapping( "/posts/create")
    public String viewFormForCreatePost(){
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createNewPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post newPost = new Post (title, body);
        postsDao.save(newPost);
        return "redirect:/posts";
    }

    @GetMapping ("posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postsDao.getById(id);
        model.addAttribute("postToEdit", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitEdit(@RequestParam(name = "title") String title,
                            @RequestParam(name = "body") String body,
                            @PathVariable long id) {
    Post postToEdit = postsDao.getById(id);
    postToEdit.setTitle(title);
    postToEdit.setBody(body);
    postsDao.save(postToEdit);
    return "redirect:/posts";
    }

    @PostMapping ("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, Model model){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

}
