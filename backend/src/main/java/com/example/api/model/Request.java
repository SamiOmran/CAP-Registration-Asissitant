package com.example.api.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    @NonNull
    private String type;

    @Column
    @NonNull
    private String courseName;

    @Column
    @NonNull
    private String courseId;

    @Column
    @NonNull
    private long sectionNumber;

    @Column
    @NonNull
    private String reason;

    @Column
    @NonNull
    private long studentUniversityNumber;

    @ManyToMany(mappedBy = "requests")
    private Collection<User> user;
}
