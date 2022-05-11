package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @JsonProperty
    private long id;

    @Column(unique = true)
    @JsonProperty
    private long universityNumber;

    @Column
    @JsonProperty
    @NonNull
    private String fname;

    @Column
    @JsonProperty
    @NonNull
    private String lname;

    @Transient
    private String fullName;

    @Column(unique = true)
    @JsonProperty
    @NonNull
    private String email;

    @Column()
    @JsonProperty
    @NonNull
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
