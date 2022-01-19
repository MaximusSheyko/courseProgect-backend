package com.example.courseprogectbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "username")
@ToString(of = "username")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    @Column(name = "name")
    private String name;


    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    @ContainedIn
    private List<Item> items;
}
