package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private Collection<User> users;
}
