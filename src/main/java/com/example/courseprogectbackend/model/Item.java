package com.example.courseprogectbackend.model;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

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
@Indexed
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    @Column(name = "name")
    private String name;

    @Field
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
    @IndexedEmbedded
    private Set<Tag> tags;

    @OneToMany(mappedBy = "item")
    private List<Like> likeList;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    @IndexedEmbedded
    private List<Comment> comments;

    @ManyToOne()
    @JoinColumn(name = "collection_id")
    private Collection collection;
}
