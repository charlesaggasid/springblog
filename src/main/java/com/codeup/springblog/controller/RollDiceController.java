package com.codeup.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {
    @GetMapping (path = "/roll-dice")
    public String userResponse () {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
     public String randDice(@PathVariable int n, Model model) {
        int randNum = (int) (Math.random() * (7-1) + 1);
        System.out.println(randNum);
        model.addAttribute("userNumberPick", n);
        model.addAttribute("randomDiceNum", randNum);

        if(randNum == n) {
            model.addAttribute("correct", "Correct guess!");
        } else {
            model.addAttribute("incorrect", "Incorrect guess!");
        }
        return "roll-dice";
     }
}
