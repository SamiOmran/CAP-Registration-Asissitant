package com.example.api.model;

import com.example.api.model.validators.ValidEmail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
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

    public String getFullName() {
        return fname + " " + lname;
    }


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

    @NonNull
    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_requests",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id")
    )
    private Set<Request> requests = new HashSet<>();

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void removeRequest(Request request) { requests.remove(request); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return ""+universityNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
