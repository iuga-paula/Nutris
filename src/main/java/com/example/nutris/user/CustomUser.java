package com.example.nutris.user;

import com.example.nutris.diet.Diet;
import com.example.nutris.food.Food;
import com.example.nutris.physicalActivity.PhysicalActivity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "custom_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class CustomUser{
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String firstName;


    @NotBlank
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private UserRole role = UserRole.NORMAL_USER;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<PhysicalActivity> activities;

    @OneToMany(mappedBy = "user")
    private Set<Diet> diets;

    public CustomUser() {

    }
    public CustomUser(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return email;
    }

    public void changeRole(UserRole role){
        this.role = role;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
