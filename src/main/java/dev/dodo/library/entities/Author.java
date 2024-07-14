package dev.dodo.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_countru")
    private String authorCountry;

    @Column(name = "author_birthdate")
    private String authorBirthdate;

    @Column(name = "author_books")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> authorBooks;



}
