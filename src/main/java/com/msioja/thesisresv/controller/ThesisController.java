package com.msioja.thesisresv.controller;

import com.msioja.thesisresv.dto.AssignDto;
import com.msioja.thesisresv.model.Thesis;
import com.msioja.thesisresv.repository.ThesisRepository;
import com.msioja.thesisresv.repository.UserRepository;
import com.msioja.thesisresv.security.PrincipalService;
import com.msioja.thesisresv.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ThesisController {

    private final ThesisService thesisService;
    private final ThesisRepository thesisRepository;
    private final UserRepository userRepository;

    @Autowired
    public ThesisController(ThesisRepository thesisRepository, UserRepository userRepository, ThesisService thesisService) {
        this.thesisRepository = thesisRepository;
        this.userRepository = userRepository;
        this.thesisService = thesisService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllTheses(Model model) {
        List<Thesis> allTheses = thesisRepository.findAllOrderByOwner();
        model.addAttribute("theses", allTheses);
        model.addAttribute("type", "All ");
        model.addAttribute("assignDto", new AssignDto());
        model.addAttribute("unsubscribe", new AssignDto());
        model.addAttribute("loggedInUser", userRepository
                .findByUsername(PrincipalService.getCurrentlyLoggedInUsername()));
        return "home";
    }

    @RequestMapping(value = "/unreserved", method = RequestMethod.GET)
    public String getUnreservedTheses(Model model) {
        List<Thesis> unreservedTheses = thesisRepository.findByAssignedUserIsNullOrderByOwner();
        model.addAttribute("theses", unreservedTheses);
        model.addAttribute("type", "Unreserved ");
        model.addAttribute("assignDto", new AssignDto());
        model.addAttribute("unsubscribe", new AssignDto());
        model.addAttribute("loggedInUser", userRepository
                .findByUsername(PrincipalService.getCurrentlyLoggedInUsername()));
        return "home";
    }

    @RequestMapping(value = "/reserved", method = RequestMethod.GET)
    public String getReservedTheses(Model model) {
        List<Thesis> reservedTheses = thesisRepository.findByAssignedUserIsNotNullOrderByOwner();
        model.addAttribute("theses", reservedTheses);
        model.addAttribute("type", "Reserved ");
        model.addAttribute("assignDto", new AssignDto());
        model.addAttribute("unsubscribe", new AssignDto());
        model.addAttribute("loggedInUser", userRepository
                .findByUsername(PrincipalService.getCurrentlyLoggedInUsername()));
        return "home";
    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
    public String unsubscribeThesis(@ModelAttribute(value = "unsubscribe") AssignDto assignDto) {
        thesisService.unsubscribe(assignDto);
        String returnType = assignDto.getTypeOfResultedTheses().toLowerCase().trim();
        return returnType.equals("all") ? "redirect:/" : "redirect:/" + returnType;
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public String assignThesis(@ModelAttribute(value = "assignDto") AssignDto assignDto) {
        thesisService.assign(assignDto);
        String returnType = assignDto.getTypeOfResultedTheses().toLowerCase().trim();
        return returnType.equals("all") ? "redirect:/" : "redirect:/" + returnType;
    }

    @RequestMapping(value = "/add-thesis", method = RequestMethod.GET)
    public String getAddThesisView(Model model) {
        model.addAttribute("thesisForm", new Thesis());
        return "add-thesis";
    }

    @RequestMapping(value = "/add-thesis", method = RequestMethod.POST)
    public String addThesis(@ModelAttribute(value = "thesisForm") @Valid Thesis thesis, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-thesis";
        }
        thesisService.add(thesis);
        return "redirect:/";
    }
}
