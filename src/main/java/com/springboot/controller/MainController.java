package com.springboot.controller;

import com.springboot.domain.Message;
import com.springboot.repos.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessagesRepo messagesRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

//    @GetMapping("/greeting2")
//    public String greeting(@RequestParam(
//            name = "name",
//            required = false,
//            defaultValue = "Leaf") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting2";
//    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messagesRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messagesRepo.save(message);

        Iterable<Message> messages = messagesRepo.findAll();

//        model.put("messages", messages);
//        return "main";
//        2 предыдущие строки можно заменить на 1:
        return "redirect:/";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messagesRepo.findByTag(filter);
        } else {
            messages = messagesRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}
