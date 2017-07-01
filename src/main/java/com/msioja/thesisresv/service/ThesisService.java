package com.msioja.thesisresv.service;

import com.msioja.thesisresv.dto.AssignDto;
import com.msioja.thesisresv.model.Thesis;
import com.msioja.thesisresv.model.User;
import com.msioja.thesisresv.repository.ThesisRepository;
import com.msioja.thesisresv.repository.UserRepository;
import com.msioja.thesisresv.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisService {
    private final ThesisRepository thesisRepository;
    private final UserRepository userRepository;

    @Autowired
    public ThesisService(ThesisRepository thesisRepository, UserRepository userRepository) {
        this.thesisRepository = thesisRepository;
        this.userRepository = userRepository;
    }

    public void unsubscribe(AssignDto assignDto) {
        User user = userRepository.findOne(assignDto.getStudentId());
        Thesis thesis = thesisRepository.findOne(assignDto.getThesisId());
        user.setAssignedThesis(null);
        thesis.setAssignedUser(null);
        userRepository.save(user);
        thesisRepository.save(thesis);
    }

    public void assign(AssignDto assignDto) {
        User user = userRepository.findOne(assignDto.getStudentId());
        Thesis thesis = thesisRepository.findOne(assignDto.getThesisId());
        user.setAssignedThesis(thesis);
        thesis.setAssignedUser(user);
        userRepository.save(user);
        thesisRepository.save(thesis);
    }

    public void add(Thesis thesis) {
        User user = userRepository.findByUsername(PrincipalService.getCurrentlyLoggedInUsername());
        thesis.setOwner(user);
        List<Thesis> createdTheses = user.getCreatedTheses();
        createdTheses.add(thesis);
        user.setCreatedTheses(createdTheses);
        userRepository.save(user);
        thesisRepository.save(thesis);
    }
}
