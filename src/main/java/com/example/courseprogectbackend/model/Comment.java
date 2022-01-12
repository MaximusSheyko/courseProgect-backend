package com.example.courseprogectbackend.model;

import lombok.*;

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
    private String message;

    @ManyToOne()
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;
}

