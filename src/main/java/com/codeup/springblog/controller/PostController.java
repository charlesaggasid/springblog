package com.codeup.springblog.controller;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository userDao;

    public PostController(PostRepository postsDao,
                          UserRepository userDao){
        this.postsDao = postsDao;
        this.userDao = userDao;
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
    public String viewIndividualPost(@PathVariable long id, Model model) {
        Post individualPost = postsDao.getById(id);
        model.addAttribute("postId", id);
        model.addAttribute("individualPost",individualPost);
        return "posts/show";
    }

    @GetMapping( "/posts/create")
    public String viewFormForCreatePost(Model model){
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createNewPost(@ModelAttribute Post post) {
        User user = userDao.getById(1L);
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping ("posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.getById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitEdit(@ModelAttribute Post post) {
    Post postToEdit = postsDao.getById(post.getId());
    postToEdit.setTitle(post.getTitle());
    postToEdit.setBody(post.getBody());
    postsDao.save(postToEdit);
    return "redirect:/posts";
    }

    @PostMapping ("/posts/delete/{id}")
    public String deletePost(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }
}
