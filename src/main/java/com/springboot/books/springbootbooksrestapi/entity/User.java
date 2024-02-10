package com.springboot.books.springbootbooksrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
//Entity class representing a user in the system.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    // Set of roles assigned to the user.
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // "referencedColumnName" is the foreign key ( the primary key of User's entity)
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // "referencedColumnName" is the foreign key ( the primary key of Role's entity)
    )
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }
}
