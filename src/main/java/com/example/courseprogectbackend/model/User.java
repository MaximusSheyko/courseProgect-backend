package com.example.courseprogectbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Transient
    @OneToMany(mappedBy = "user")
    private List<Like> likeList;

    @Transient
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @Transient
    @OneToMany(mappedBy = "user")
    private List<Collection> collections;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role"
            , joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
