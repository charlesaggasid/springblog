package com.codeup.springblog.controller;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private final BookRepository bookDao;

    public BookController(BookRepository bookDao) {
        this.bookDao = bookDao;
    }
        @GetMapping("/books")
        public String showBooks (Model model) {
            model.addAttribute("allBooks",bookDao.findAll());
            return "books";
        }


}
