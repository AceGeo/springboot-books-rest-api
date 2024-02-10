package com.springboot.books.springbootbooksrestapi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="books")
//Entity class representing a book in the system.
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable=false)
    private String title;

    @Column(name="author",nullable=false)
    private String author;

    @Column(name="language",nullable=false)
    private String language;

    @Column(name="pages",nullable=false)
    private String pages;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="release_date",nullable=false)
    private LocalDate releaseDate;

    @Column(name="description",nullable=false)
    private String description;

    // Set of reviews associated with the book.
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    // Category to which the book belongs.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}