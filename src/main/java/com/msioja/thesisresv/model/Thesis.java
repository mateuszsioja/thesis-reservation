package com.msioja.thesisresv.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @NotNull
    @Size(min = 3, message = "Subject must be at least 3 characters long")
    private String subject;

    @NotNull
    @Size(min = 3, message = "Summary must be at least 3 characters long")
    private String summary;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToOne
    private User assignedUser;
}
