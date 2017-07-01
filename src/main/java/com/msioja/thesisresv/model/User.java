package com.msioja.thesisresv.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "person")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @NotNull
    @Size(min = 3, message = "Username must be at least 3 characters long")
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = 3, message = "Password must be at least 3 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Size(min = 3, message = "First Name must be at least 3 characters long")
    private String firstName;

    @NotNull
    @Size(min = 3, message = "Last Name must be at least 3 characters long")
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private List<Thesis> createdTheses = new ArrayList<>();

    @OneToOne(mappedBy = "assignedUser")
    private Thesis assignedThesis;
}
