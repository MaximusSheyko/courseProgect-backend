package com.example.courseprogectbackend.model;

import lombok.*;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_creation")
    private LocalDateTime dateCreation;

    @Column(name = "message")
    @Field
    private String message;

    @ManyToOne()
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    @ContainedIn
    private Item item;
}

