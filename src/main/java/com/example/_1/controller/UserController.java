package com.example._1.controller;

import com.example._1.model.User;
import com.example._1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //Полный список юзеров
    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
    //Гет на создание юзера
    @GetMapping("/users/add")
    public String addUser(@ModelAttribute("user") User user) {
        return "addUser";
    }
    //Пост на создание
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addUser";
        }
        userService.save(user);
        return "redirect:/users";
    }
//    //Поиск по id юзера
//    @GetMapping("/user")
//    public String findOne(Model model, @RequestParam Long id) {
//        userService.findById(id);
//    }
    //Гет на редактирование юзера
    @GetMapping("/user/edit")
    public String edit(Model model, @RequestParam Long id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }
    //Пост на редактирование юзера
    @PostMapping("/user/edit")
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.save(user);
        return "redirect:/users";
    }
    //Удаление юзера
    @GetMapping("/user/delete")
    public String delete(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
