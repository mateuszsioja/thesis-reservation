package com.msioja.thesisresv.service;

import com.msioja.thesisresv.model.Role;
import com.msioja.thesisresv.model.Thesis;
import com.msioja.thesisresv.model.User;
import com.msioja.thesisresv.repository.ThesisRepository;
import com.msioja.thesisresv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class InitDbService {

    private final UserRepository userRepository;
    private final ThesisRepository thesisRepository;

    @Autowired
    public InitDbService(UserRepository userRepository, ThesisRepository thesisRepository) {
        this.userRepository = userRepository;
        this.thesisRepository = thesisRepository;
    }

    @PostConstruct
    public void init() {
        Thesis thesis1 = new Thesis();
        thesis1.setSubject("Aplikacja hotelowa na bazie ASP.NET Core MVC");
        thesis1.setSummary("Możliwości zastosowania dockera, Yeoman i Visual Studio Code na bazie Linuksa.");
        thesisRepository.save(thesis1);

        Thesis thesis2 = new Thesis();
        thesis2.setSubject("Zastosowanie sieci Petriego do modelowania systemów informatycznych");
        thesis2.setSummary("Konstrukcja i analiza wydajnościowa klastrowych systemów internetowych Sieci kolejkowe, " +
                "sieci Petriego, kolejkowe sieci Petriego.");
        thesisRepository.save(thesis2);

        User specialUser = new User();
        specialUser.setUsername("special");
        specialUser.setPassword("$2a$10$GG3dci1WmoXM.ZIR91lvKedeevGU2hnalS5MFJmaKWgqj0ogTy6rK");
        specialUser.setFirstName("John");
        specialUser.setLastName("Super");
        specialUser.setRole(Role.ROLE_ADMIN);
        specialUser.setCreatedTheses(Arrays.asList(thesis1, thesis2));
        userRepository.save(specialUser);

        thesis1.setOwner(specialUser);
        thesis2.setOwner(specialUser);
        thesisRepository.save(thesis1);
        thesisRepository.save(thesis2);

        User regularUser = new User();
        regularUser.setUsername("student");
        regularUser.setPassword("$2a$10$yK5oKFzDDv6b2dB8D3aFGu/O3XCCUL8f4Lo3UG8bQgBIapf16zKkO");
        regularUser.setFirstName("Mathew");
        regularUser.setLastName("Regular");
        regularUser.setRole(Role.ROLE_USER);
        regularUser.setAssignedThesis(thesis1);
        userRepository.save(regularUser);

        thesis1.setAssignedUser(regularUser);
        thesisRepository.save(thesis1);






        Thesis thesis3 = new Thesis();
        thesis3.setSubject("Moduł zarządzania zleceniami dla systemu klasy MES");
        thesis3.setSummary("Temat obejmuje zaprojektowanie i implementację systemu informatycznego do " +
                "zarządzania zleceniami produkcyjnymi, stanowiącego moduł systemu klasy " +
                "MES (Manufacturing Execution System).");
        thesisRepository.save(thesis3);

        Thesis thesis4 = new Thesis();
        thesis4.setSubject("Tworzenie serwisu webowego w modelu relacyjnym i nierelacyjnym");
        thesis4.setSummary("MEAN Stack - Node.js, express, baza relacyjna i mongoDB. Np. biblioteka " +
                "- serwis z materiałami np. dydaktycznymi lub aplikacja hotelowa.");
        thesisRepository.save(thesis4);

        User specialUser2 = new User();
        specialUser2.setUsername("admin");
        specialUser2.setPassword("$2a$10$5Flz1gVWvu1z2ViLGQGs0uwSYHxN7Yoji11CLxY5fUvzDIh5jifMu");
        specialUser2.setFirstName("Max");
        specialUser2.setLastName("Planck");
        specialUser2.setRole(Role.ROLE_ADMIN);
        specialUser2.setCreatedTheses(Arrays.asList(thesis3, thesis4));
        userRepository.save(specialUser2);

        thesis3.setOwner(specialUser2);
        thesis4.setOwner(specialUser2);
        thesisRepository.save(thesis3);
        thesisRepository.save(thesis4);

        User regularUser2 = new User();
        regularUser2.setUsername("student2");
        regularUser2.setPassword("$2a$10$6P3CD00fSWdB/qO56ky8QOMLOpSNWWqhu2b9zbzBZdg3sPc72/Xzu");
        regularUser2.setFirstName("Peter");
        regularUser2.setLastName("Debye");
        regularUser2.setRole(Role.ROLE_USER);
        regularUser2.setAssignedThesis(thesis3);
        userRepository.save(regularUser2);

        thesis3.setAssignedUser(regularUser2);
        thesisRepository.save(thesis3);
    }
}
