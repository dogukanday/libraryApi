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
@Table(name = "category")
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "category_name")
        private String categoryName;

        @Column(name = "category_description")
        private String categoryDescription;


        @ManyToMany(mappedBy = "category")
        private List<Book> categoryBooks;


}
