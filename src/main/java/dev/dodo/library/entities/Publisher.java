package dev.dodo.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publisher")
public class Publisher {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "publisher_name")
        private String publisherName;

        @Column(name = "publisher_year")
        private String publisherYear;

        @Column(name = "publisher_adress")
        private String publisherAdress;

        @Column(name = "publisher_books")
        @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
        private List<Book> publisherBooks;


}
