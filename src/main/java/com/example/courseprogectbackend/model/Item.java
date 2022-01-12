package com.example.courseprogectbackend.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tags_items"
            , joinColumns = @JoinColumn(name = "item_id")
            , inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "item")
    private List<Like> likeList;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToOne()
    @JoinColumn(name = "collection_id")
    private Collection collection;
}
