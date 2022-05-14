package com.example.api.model;

import com.example.api.model.validators.ValidEmail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.Size;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @JsonProperty
    private long id;

    @JsonProperty
    @Column(unique = true)
    @NonNull()
    private long universityNumber;

    @JsonProperty
    @Column(name = "first_name")
    @NonNull
    private String fname;

    @JsonProperty
    @Column(name = "last_name")
    @NonNull
    private String lname;

    @Transient
    private String fullName;

    @JsonProperty
    @Column(unique = true)
    @NonNull()
    @Size(min = 8, max = 20)
    @ValidEmail()
    private String email;

    @JsonProperty
    @Column()
    @NonNull()
    private String password;

    @ManyToOne
    @JsonBackReference
    private Role role;

    public User(long universityNumber, String fname, String lname, String fullName, String email, String password, Role role) {
        this.universityNumber = universityNumber;
        this.fname = fname;
        this.lname = lname;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
