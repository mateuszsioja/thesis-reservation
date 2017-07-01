package com.msioja.thesisresv.controller;

import com.msioja.thesisresv.model.User;
import com.msioja.thesisresv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterView(Model model) {
        model.addAttribute("studentForm", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute(value = "studentForm") @Valid User user, BindingResult bindingResult) {
        if (userService.isUsernameExists(user))
            bindingResult.rejectValue("username", "error.username", "Username exists");
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.addStudent(user);
        return "redirect:/";
    }


    @RequestMapping(value = "/add-promoter", method = RequestMethod.GET)
    public String getAddThesisView(Model model) {
        model.addAttribute("promoterForm", new User());
        return "add-promoter";
    }

    @RequestMapping(value = "/add-promoter", method = RequestMethod.POST)
    public String addPromoter(@ModelAttribute(value = "promoterForm") @Valid User user, BindingResult bindingResult) {
        if (userService.isUsernameExists(user))
            bindingResult.rejectValue("username", "error.username", "Username exists");
        if (bindingResult.hasErrors()) {
            return "add-promoter";
        }
        userService.addPromoter(user);
        return "redirect:/";
    }
}
