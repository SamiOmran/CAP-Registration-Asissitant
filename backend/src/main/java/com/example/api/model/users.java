package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class users {
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

    private String fullName;
}
