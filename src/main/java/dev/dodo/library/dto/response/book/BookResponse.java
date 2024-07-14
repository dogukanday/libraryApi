package dev.dodo.library.dto.response.book;

import dev.dodo.library.entities.Borrowing;
import dev.dodo.library.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private int id;

    private String bookName;

    private int authorId;

    private int year;

    private int stock;

    private int publisherId;

}
